package io.pivotal.pal.data.rentaltruck.reservation.handler.reservationcreated;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventSubscriberAdapter;
import io.pivotal.pal.data.rentaltruck.reservation.event.ReservationCreatedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReservationCreatedSubscriberAdapterConfig {

    @Bean
    public AsyncEventSubscriberAdapter<ReservationCreatedEvent> reservationCreatedSubscriberAdapter(AsyncEventHandler<ReservationCreatedEvent> subscriber) {
        return new AsyncEventSubscriberAdapter<>("reservationCreated", subscriber);
    }
}
