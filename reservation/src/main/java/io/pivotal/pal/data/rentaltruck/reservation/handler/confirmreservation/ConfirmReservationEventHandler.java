package io.pivotal.pal.data.rentaltruck.reservation.handler.confirmreservation;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltruck.reservation.event.ReservationValidatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * This handler subscribes to {@link ReservationValidatedEvent} and updates the Cassandra
 * table(s) to {@code Confirmed} status.
 */
@Component
public class ConfirmReservationEventHandler implements AsyncEventHandler<ReservationValidatedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ConfirmReservationEventHandler.class);

    @Override
    public void onEvent(ReservationValidatedEvent data) {
        logger.info("reservationValidated: {}:", data);

        // desired reservation table structure:
        // reservations by store and truck type and date
        // - multi-day reservation is multiple rows
        // strong consistency with other res tables

        // de-dup by updating record to status=confirmed (abort if already processing status)
        //   anything else to be updated once we get to this state?  timestamps?

        // infrastructure:  retry for all recoverable errors (i.e., infrastructure)

        // do we re-check whether the store has truck availability (race conditions?)
        // what if the count is 0 now for that store-truckype-date tuple?
        // 1) reject the reservation now
        // 2) emit event to deadhead a truck to this store
        // 3) might retry it eventually (if there's a race condition)
    }
}
