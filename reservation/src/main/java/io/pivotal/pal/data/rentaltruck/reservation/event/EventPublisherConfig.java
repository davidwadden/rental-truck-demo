package io.pivotal.pal.data.rentaltruck.reservation.event;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventPublisher;
import io.pivotal.pal.data.rentaltruck.framework.event.DefaultAsyncEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventPublisherConfig {

    @Bean
    public AsyncEventPublisher<CreditCardFailedEvent> creditCardFailedEventPublisher() {
        return new DefaultAsyncEventPublisher<>("creditCardFailed");
    }

    @Bean
    public AsyncEventPublisher<CreditCardVerifiedEvent> creditCardVerifiedEventPublisher() {
        return new DefaultAsyncEventPublisher<>("creditCardVerified");
    }

    @Bean
    public AsyncEventPublisher<ReservationInitializedEvent> reservationInitializedEventPublisher() {
        return new DefaultAsyncEventPublisher<>("reservationInitialized");
    }

    @Bean
    public AsyncEventPublisher<ReservationRequestedEvent> reservationRequestedEventPublisher() {
        return new DefaultAsyncEventPublisher<>("reservationRequested");
    }

    @Bean
    public AsyncEventPublisher<ReservationValidatedEvent> reservationValidatedEventPublisher() {
        return new DefaultAsyncEventPublisher<>("reservationValidated");
    }
}
