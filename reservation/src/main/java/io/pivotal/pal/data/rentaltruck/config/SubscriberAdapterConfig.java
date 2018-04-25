package io.pivotal.pal.data.rentaltruck.config;

import io.pivotal.pal.data.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.framework.event.AsyncEventSubscriberAdapter;
import io.pivotal.pal.data.rentaltruck.event.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SubscriberAdapterConfig {

    @Bean
    public AsyncEventSubscriberAdapter<ReservationRequestedEvent> saveReservationSubscriberAdapter(AsyncEventHandler<ReservationRequestedEvent> subscriber) {
        return new AsyncEventSubscriberAdapter<>(EventType.RESERVATION_REQUESTED.getEventName(), subscriber);
    }

    @Bean
    public AsyncEventSubscriberAdapter<ReservationInitializedEvent> creditCardVerifierSubscriberAdapter(AsyncEventHandler<ReservationInitializedEvent> subscriber) {
        return new AsyncEventSubscriberAdapter<>(EventType.RESERVATION_INITIALIZED.getEventName(), subscriber);
    }

    @Bean
    public AsyncEventSubscriberAdapter<CreditCardVerifiedEvent> truckAvailabilitySubscriberAdapter(AsyncEventHandler<CreditCardVerifiedEvent> subscriber) {
        return new AsyncEventSubscriberAdapter<>(EventType.CREDIT_CARD_VERIFIED.getEventName(), subscriber);
    }

    @Bean
    public AsyncEventSubscriberAdapter<ReservationValidatedEvent> confirmReservationSubscriberAdapter(AsyncEventHandler<ReservationValidatedEvent> subscriber) {
        return new AsyncEventSubscriberAdapter<>(EventType.RESERVATION_VALIDATED.getEventName(), subscriber);
    }
}
