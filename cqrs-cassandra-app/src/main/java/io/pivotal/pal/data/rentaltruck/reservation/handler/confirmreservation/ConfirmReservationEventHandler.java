package io.pivotal.pal.data.rentaltruck.reservation.handler.confirmreservation;

import io.pivotal.pal.data.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltruck.reservation.entity.ReservationByConfirmationNumber;
import io.pivotal.pal.data.rentaltruck.event.ReservationValidatedEvent;
import io.pivotal.pal.data.rentaltruck.reservation.repository.ReservationByConfirmationNumberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * This handler subscribes to {@link ReservationValidatedEvent} and updates the Cassandra
 * table(s) to {@code Confirmed} status.
 * <p/>
 *
 * table definition: reservations by store and truck type and date
 * - multi-day reservation is multiple rows
 * - strong consistency with other res tables
 */
@Component
public class ConfirmReservationEventHandler implements AsyncEventHandler<ReservationValidatedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ConfirmReservationEventHandler.class);

    private final ReservationByConfirmationNumberRepository repository;

    public ConfirmReservationEventHandler(ReservationByConfirmationNumberRepository repository) {
        this.repository = repository;
    }

    @Override
    public void onEvent(ReservationValidatedEvent event) {
        logger.info("reservationValidated: {}:", event);

        // fetch existing reservation
        ReservationByConfirmationNumber reservation = repository.findByConfirmationNumber(event.getConfirmationNumber());

        // check that reservation is in expected state (to de-dup event)
        if (!reservation.getStatus().equalsIgnoreCase("VALIDATED")) {
            logger.warn("reservation {} is wrong status: {}, expected 'validated'",
                    reservation.getConfirmationNumber(),
                    reservation.getStatus());
            return;
        }

        // update record to status=confirmed
        //   anything else to be updated once we get to this state?  timestamps?
        reservation.setStatus("CONFIRMED");
        repository.save(reservation);

        // error handling notes:

        // infrastructure:  retry for all recoverable errors (i.e., infrastructure)

        // do we re-check whether the store has truck availability (race conditions?)
        // what if the count is 0 now for that store-truckype-date tuple?
        // 1) reject the reservation now
        // 2) emit event to deadhead a truck to this store
        // 3) might retry it eventually (if there's a race condition)
    }
}
