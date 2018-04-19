package io.pivotal.pal.data.rentaltruck.reservation.handler.truckavailability;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventPublisher;
import io.pivotal.pal.data.rentaltruck.reservation.entity.ReservationByConfirmationNumber;
import io.pivotal.pal.data.rentaltruck.reservation.event.CreditCardVerifiedEvent;
import io.pivotal.pal.data.rentaltruck.reservation.event.ReservationValidatedEvent;
import io.pivotal.pal.data.rentaltruck.reservation.event.TruckAvailableEvent;
import io.pivotal.pal.data.rentaltruck.reservation.event.TruckNotAvailableEvent;
import io.pivotal.pal.data.rentaltruck.reservation.repo.ReservationByConfirmationNumberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * This handler subscribes to {@link CreditCardVerifiedEvent} and checks against Cassandra
 * whether the store has the desired truckAvailableEventAsyncEventPublisher type available.
 */
@Component
public class TruckAvailabilityEventHandler implements AsyncEventHandler<CreditCardVerifiedEvent> {

    private final AsyncEventPublisher<TruckAvailableEvent> truckAvailableEventAsyncEventPublisher;
    private final AsyncEventPublisher<TruckNotAvailableEvent> truckNotAvailableEventAsyncEventPublisher;
    private final ReservationByConfirmationNumberRepository reservationByConfirmationNumberRepository;

    private static final Logger logger = LoggerFactory.getLogger(TruckAvailabilityEventHandler.class);

    public TruckAvailabilityEventHandler(AsyncEventPublisher<TruckAvailableEvent> truckAvailableEventAsyncEventPublisher,
                                         AsyncEventPublisher<TruckNotAvailableEvent> truckNotAvailableEventAsyncEventPublisher,
                                         ReservationByConfirmationNumberRepository reservationByConfirmationNumberRepository) {
        this.truckAvailableEventAsyncEventPublisher = truckAvailableEventAsyncEventPublisher;
        this.truckNotAvailableEventAsyncEventPublisher = truckNotAvailableEventAsyncEventPublisher;
        this.reservationByConfirmationNumberRepository = reservationByConfirmationNumberRepository;
    }

    @Override
    public void onEvent(CreditCardVerifiedEvent data) {
        String confirmationNumber = data.getConfirmationNumber();

        ReservationByConfirmationNumber reservation = reservationByConfirmationNumberRepository.findByConfirmationNumber(confirmationNumber);
        Assert.notNull(reservation, "Reservation '" + confirmationNumber + "' not found");

        // de-dup by updating record to status=validating (abort if already processing status)
        if (reservation.getStatus().equals("VALIDATING")) {
            logger.warn("already validating reservation {}", reservation.getConfirmationNumber());
        } else {
            reservation.setStatus("VALIDATING");
            reservationByConfirmationNumberRepository.save(reservation);

            // query reservations for a given store / truckAvailableEventAsyncEventPublisher type over the desired date span to
            //   see whether there is at least one truckAvailableEventAsyncEventPublisher available to be reserved
            int availableCount = 0; // TODO

            if (availableCount == 0) {
                truckNotAvailableEventAsyncEventPublisher.publish(new TruckNotAvailableEvent()); // TODO
            } else {
                truckAvailableEventAsyncEventPublisher.publish(new TruckAvailableEvent()); // TODO
            }
        }


        // if truckAvailableEventAsyncEventPublisher available, emit message to reservation validated topic
        // if zero trucks available, emit message to reservation validation failed topic (doesn't exist yet)

        // exceptional case:
        //   - retry if infrastructure problem  (Can't do too much, maybe 3X)
        //   - else, send to manual dead letter queue
        //   - what if data size exceeds column length ?  non-recoverable error
    }
}
