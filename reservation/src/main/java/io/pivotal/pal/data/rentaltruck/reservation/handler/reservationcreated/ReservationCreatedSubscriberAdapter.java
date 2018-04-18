package io.pivotal.pal.data.rentaltruck.reservation.handler.reservationcreated;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventSubscriberAdapter;
import io.pivotal.pal.data.rentaltruck.reservation.event.ReservationCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class ReservationCreatedSubscriberAdapter extends AsyncEventSubscriberAdapter<ReservationCreatedEvent> {

    public ReservationCreatedSubscriberAdapter(AsyncEventHandler<ReservationCreatedEvent> subscriber) {
        super("reservationCreated", subscriber);
    }
}
