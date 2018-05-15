package io.pivotal.pal.data.rentaltruck.reservation.handler.truckavailability;

import io.pivotal.pal.data.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.framework.event.AsyncEventPublisher;
import io.pivotal.pal.data.rentaltruck.reservation.entity.ReservationByConfirmationNumber;
import io.pivotal.pal.data.rentaltruck.reservation.entity.TrucksOnHandByStoreAndTruckType;
import io.pivotal.pal.data.rentaltruck.event.CreditCardVerifiedEvent;
import io.pivotal.pal.data.rentaltruck.event.TruckAvailableEvent;
import io.pivotal.pal.data.rentaltruck.event.TruckNotAvailableEvent;
import io.pivotal.pal.data.rentaltruck.reservation.query.trucksonhand.TrucksOnHandByStoreAndTruckTypeRepository;
import io.pivotal.pal.data.rentaltruck.reservation.repository.ReservationByConfirmationNumberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Collection;

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
    private final TrucksOnHandByStoreAndTruckTypeRepository trucksOnHandRepository;

    public TruckAvailabilityEventHandler(AsyncEventPublisher<TruckAvailableEvent> truckAvailableEventPublisher,
                                         AsyncEventPublisher<TruckNotAvailableEvent> truckNotAvailableEventPublisher,
                                         ReservationByConfirmationNumberRepository reservationRepository,
                                         TrucksOnHandByStoreAndTruckTypeRepository trucksOnHandRepository) {
        this.truckAvailableEventPublisher = truckAvailableEventPublisher;
        this.truckNotAvailableEventPublisher = truckNotAvailableEventPublisher;
        this.reservationRepository = reservationRepository;
        this.trucksOnHandRepository = trucksOnHandRepository;
    }

    @Override
    public void onEvent(CreditCardVerifiedEvent event) {
        String confirmationNumber = event.getConfirmationNumber();

        ReservationByConfirmationNumber reservation = reservationRepository.findByConfirmationNumber(confirmationNumber);
        Assert.notNull(reservation, "Reservation '" + confirmationNumber + "' not found");

        // de-dup by updating record to status=validating (abort if already processing status)
        if (reservation.getStatus().equals("VALIDATING")) {
            logger.warn("already validating reservation {}", reservation.getConfirmationNumber());
            return;
        }

        // query reservations for a given store / truckAvailableEventPublisher type over the desired date span to
        //   see whether there is at least one truckAvailableEventPublisher available to be reserved
        Collection<TrucksOnHandByStoreAndTruckType> trucksOnHand =
                trucksOnHandRepository.findAllByStoreAndTruckTypeAndDateRange(
                        reservation.getPickupStoreId(),
                        reservation.getTruckType(),
                        reservation.getPickupDate(),
                        reservation.getDropoffDate()
                );

        // check whether a given store has availability for the entire date range
        // TODO: Handle a missing a record in trucksOnHand table, zero records currently passes
        boolean trucksAvailableOnAllDays = trucksOnHand.stream()
                .allMatch(countByDay -> countByDay.getCountOnHand() > 0);

        // if zero trucks available, emit message to reservation validation failed topic (doesn't exist yet)
        if (!trucksAvailableOnAllDays) {
            truckNotAvailableEventPublisher.publish(new TruckNotAvailableEvent()); // TODO
        }

        // if truckAvailableEventPublisher available, emit message to reservation validated topic
        truckAvailableEventPublisher.publish(new TruckAvailableEvent()); // TODO

        reservation.setStatus("VALIDATING");
        reservationRepository.save(reservation);

        // exceptional case:
        //   - retry if infrastructure problem  (Can't do too much, maybe 3X)
        //   - else, send to manual dead letter queue
        //   - what if data size exceeds column length ?  non-recoverable error
    }
}
