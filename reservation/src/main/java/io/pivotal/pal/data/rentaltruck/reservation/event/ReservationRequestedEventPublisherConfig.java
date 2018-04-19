package io.pivotal.pal.data.rentaltruck.reservation.event;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventPublisher;
import io.pivotal.pal.data.rentaltruck.framework.event.DefaultAsyncEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReservationRequestedEventPublisherConfig {

    @Bean
    public AsyncEventPublisher<ReservationRequestedEvent> reservationCreatedEventPublisher() {
        return new DefaultAsyncEventPublisher<>("reservationCreated");
    }
}
