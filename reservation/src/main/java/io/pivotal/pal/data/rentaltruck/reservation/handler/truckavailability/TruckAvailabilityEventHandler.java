package io.pivotal.pal.data.rentaltruck.reservation.handler.truckavailability;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventPublisher;
import io.pivotal.pal.data.rentaltruck.reservation.entity.ReservationByConfirmationNumber;
import io.pivotal.pal.data.rentaltruck.reservation.entity.TrucksOnHandByMetroAreaAndTruckType;
import io.pivotal.pal.data.rentaltruck.reservation.event.CreditCardVerifiedEvent;
import io.pivotal.pal.data.rentaltruck.reservation.event.TruckAvailableEvent;
import io.pivotal.pal.data.rentaltruck.reservation.event.TruckNotAvailableEvent;
import io.pivotal.pal.data.rentaltruck.reservation.query.trucksonhand.TrucksOnHandByMetroAreaAndTruckTypeRepository;
import io.pivotal.pal.data.rentaltruck.reservation.repo.ReservationByConfirmationNumberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * This handler subscribes to {@link CreditCardVerifiedEvent} and checks against Cassandra
 * whether the store has the desired truckAvailableEventPublisher type available.
 */
@Component
public class TruckAvailabilityEventHandler implements AsyncEventHandler<CreditCardVerifiedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(TruckAvailabilityEventHandler.class);

    private final AsyncEventPublisher<TruckAvailableEvent> truckAvailableEventPublisher;
    private final AsyncEventPublisher<TruckNotAvailableEvent> truckNotAvailableEventPublisher;
    private final ReservationByConfirmationNumberRepository reservationRepository;
    private final TrucksOnHandByMetroAreaAndTruckTypeRepository trucksOnHandRepository;

    public TruckAvailabilityEventHandler(AsyncEventPublisher<TruckAvailableEvent> truckAvailableEventPublisher,
                                         AsyncEventPublisher<TruckNotAvailableEvent> truckNotAvailableEventPublisher,
                                         ReservationByConfirmationNumberRepository reservationRepository,
                                         TrucksOnHandByMetroAreaAndTruckTypeRepository trucksOnHandRepository) {
        this.truckAvailableEventPublisher = truckAvailableEventPublisher;
        this.truckNotAvailableEventPublisher = truckNotAvailableEventPublisher;
        this.reservationRepository = reservationRepository;
        this.trucksOnHandRepository = trucksOnHandRepository;
    }

    @Override
    public void onEvent(CreditCardVerifiedEvent data) {
        String confirmationNumber = data.getConfirmationNumber();

        ReservationByConfirmationNumber reservation = reservationRepository.findByConfirmationNumber(confirmationNumber);
        Assert.notNull(reservation, "Reservation '" + confirmationNumber + "' not found");

        // de-dup by updating record to status=validating (abort if already processing status)
        if (reservation.getStatus().equals("VALIDATING")) {
            logger.warn("already validating reservation {}", reservation.getConfirmationNumber());
            return;
        }

        // query reservations for a given store / truckAvailableEventPublisher type over the desired date span to
        //   see whether there is at least one truckAvailableEventPublisher available to be reserved
        Collection<TrucksOnHandByMetroAreaAndTruckType> trucksOnHand =
                trucksOnHandRepository.findAllByMetroAreaAndTruckTypeAndDateRange(
                        reservation.getMetroArea(),
                        reservation.getTruckType(),
                        reservation.getPickupDate(),
                        reservation.getDropoffDate()
                );

        // check whether a given store has availability for the entire date range
        // TODO: need to do this for each store and see if any store can do all days
        final AtomicBoolean hasAvailable = new AtomicBoolean(true);
        trucksOnHand.forEach(c -> {
            if (c.getCountOnHand() == 0) {
                hasAvailable.set(false);
            }
        });

        // if truckAvailableEventPublisher available, emit message to reservation validated topic
        // if zero trucks available, emit message to reservation validation failed topic (doesn't exist yet)
        if (hasAvailable.get()) {
            truckAvailableEventPublisher.publish(new TruckAvailableEvent()); // TODO
        } else {
            truckNotAvailableEventPublisher.publish(new TruckNotAvailableEvent()); // TODO
        }

        reservation.setStatus("VALIDATING");
        reservationRepository.save(reservation);

        // exceptional case:
        //   - retry if infrastructure problem  (Can't do too much, maybe 3X)
        //   - else, send to manual dead letter queue
        //   - what if data size exceeds column length ?  non-recoverable error
    }
}
