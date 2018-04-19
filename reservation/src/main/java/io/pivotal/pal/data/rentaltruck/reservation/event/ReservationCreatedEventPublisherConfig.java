package io.pivotal.pal.data.rentaltruck.reservation.event;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventPublisher;
import io.pivotal.pal.data.rentaltruck.framework.event.DefaultAsyncEventPublisher;
import io.pivotal.pal.data.rentaltruck.reservation.event.ReservationCreatedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReservationCreatedEventPublisherConfig {

    @Bean
    public AsyncEventPublisher<ReservationCreatedEvent> reservationCreatedEventPublisher() {
        return new DefaultAsyncEventPublisher<>("reservationCreated");
    }
}
