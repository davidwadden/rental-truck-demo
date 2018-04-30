package io.pivotal.pal.data.rentaltruck.config;

import io.pivotal.pal.data.framework.event.AsyncEventPublisher;
import io.pivotal.pal.data.framework.event.DefaultAsyncEventPublisher;
import io.pivotal.pal.data.rentaltruck.event.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EventPublisherConfig {

    @Bean
    public AsyncEventPublisher<CreditCardFailedEvent> creditCardFailedEventPublisher() {
        return new DefaultAsyncEventPublisher<>(EventType.CREDIT_CARD_FAILED.getEventName());
    }

    @Bean
    public AsyncEventPublisher<CreditCardVerifiedEvent> creditCardVerifiedEventPublisher() {
        return new DefaultAsyncEventPublisher<>(EventType.CREDIT_CARD_VERIFIED.getEventName());
    }

    @Bean
    public AsyncEventPublisher<ReservationInitializedEvent> reservationInitializedEventPublisher() {
        return new DefaultAsyncEventPublisher<>(EventType.RESERVATION_INITIALIZED.getEventName());
    }

    @Bean
    public AsyncEventPublisher<ReservationRequestedEvent> reservationRequestedEventPublisher() {
        return new DefaultAsyncEventPublisher<>(EventType.RESERVATION_REQUESTED.getEventName());
    }

    @Bean
    public AsyncEventPublisher<ReservationValidatedEvent> reservationValidatedEventPublisher() {
        return new DefaultAsyncEventPublisher<>(EventType.RESERVATION_VALIDATED.getEventName());
    }

    @Bean
    public AsyncEventPublisher<TruckAvailableEvent> truckAvailableEventPublisher() {
        return new DefaultAsyncEventPublisher<>(EventType.TRUCK_AVAILABLE.getEventName());
    }

    @Bean
    public AsyncEventPublisher<TruckNotAvailableEvent> truckNotAvailableEventPublisher() {
        return new DefaultAsyncEventPublisher<>(EventType.TRUCK_NOT_AVAILABLE.getEventName());
    }
}
