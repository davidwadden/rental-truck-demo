package io.pivotal.pal.data.rentaltruck.reservation.handler.savereservation;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventPublisher;
import io.pivotal.pal.data.rentaltruck.reservation.event.ReservationInitializedEvent;
import io.pivotal.pal.data.rentaltruck.reservation.event.ReservationRequestedEvent;
import org.springframework.stereotype.Component;

/**
 * This handler subscribes to {@link ReservationRequestedEvent} and inserts record(s)
 * into the data store to de-dup potential re-delivered messages and to track the state
 * of the overall flow.
 */
@Component
public class SaveReservationEventHandler implements AsyncEventHandler<ReservationRequestedEvent> {

    private final AsyncEventPublisher<ReservationInitializedEvent> eventPublisher;

    public SaveReservationEventHandler(AsyncEventPublisher<ReservationInitializedEvent> eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void onEvent(ReservationRequestedEvent data) {
        // upsert records into reservation table(s)
        // - set status=initialized
        // check whether upsert was an insert or an update
        // if update, no-op
        // if insert, emit reservation initialized event
    }
}
