package io.pivotal.pal.data.rentaltruck.reservation.handler.reservationconfirmed;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltruck.reservation.event.ReservationConfirmedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ReservationConfirmedEventHandler implements AsyncEventHandler<ReservationConfirmedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ReservationConfirmedEventHandler.class);

    @Override
    public void onEvent(ReservationConfirmedEvent data) {
        logger.info("reservationConfirmed event: {}:", data);

        // infrastructure:  retry for all recoverable errors (i.e., infrastructure)
        // - counters are not idempotent

        // if available count is already 0, do we:
        // 1) reject the reservation now (maybe we should have checked this earlier)
        // 2) emit event to refill da trucks
        // 3) might retry it eventually (if there's a race condition)
    }
}


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
