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

        // de-dup by updating record to status=confirmed (abort if already processing status)
        //   anything else to be updated once we get to this state?  timestamps?

        // do we re-check whether the store has truck availability (race conditions?)

        // TODO: out of date below
        // infrastructure:  retry for all recoverable errors (i.e., infrastructure)
        // - counters are not idempotent

        // if available count is already 0, do we:
        // 1) reject the reservation now (maybe we should have checked this earlier)
        // 2) emit event to refill da trucks
        // 3) might retry it eventually (if there's a race condition)
    }
}

// TODO: remove global notes from this file

// command handler emits reservation requested
// truck capacity check handler creates reservation records (de-dup) in table with pending status
// sends reservation initalized event (change)
// credit card verified handler checks with 3rd party api and emits credit card verified (update status!)
//   > or maybe credit card failed
// validate truck capacity emits truck capacity validated event (update status!)
// confirm reservation handler updates reservation records with confirmed status


// reservations by store and truck type and date
// - multi-day reservation is multiple rows
// strong consistency with other res tables
