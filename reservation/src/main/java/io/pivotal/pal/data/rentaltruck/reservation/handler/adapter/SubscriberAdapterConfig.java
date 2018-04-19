package io.pivotal.pal.data.rentaltruck.reservation.handler.adapter;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventSubscriberAdapter;
import io.pivotal.pal.data.rentaltruck.reservation.event.CreditCardVerifiedEvent;
import io.pivotal.pal.data.rentaltruck.reservation.event.ReservationInitializedEvent;
import io.pivotal.pal.data.rentaltruck.reservation.event.ReservationRequestedEvent;
import io.pivotal.pal.data.rentaltruck.reservation.event.ReservationValidatedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SubscriberAdapterConfig {

    @Bean
    public AsyncEventSubscriberAdapter<ReservationRequestedEvent> saveReservationSubscriberAdapter(AsyncEventHandler<ReservationRequestedEvent> subscriber) {
        return new AsyncEventSubscriberAdapter<>("reservationRequested", subscriber);
    }

    @Bean
    public AsyncEventSubscriberAdapter<ReservationInitializedEvent> creditCardVerifierSubscriberAdapter(AsyncEventHandler<ReservationInitializedEvent> subscriber) {
        return new AsyncEventSubscriberAdapter<>("reservationInitialized", subscriber);
    }

    @Bean
    public AsyncEventSubscriberAdapter<CreditCardVerifiedEvent> truckAvailabilitySubscriberAdapter(AsyncEventHandler<CreditCardVerifiedEvent> subscriber) {
        return new AsyncEventSubscriberAdapter<>("creditCardVerified", subscriber);
    }

    @Bean
    public AsyncEventSubscriberAdapter<ReservationValidatedEvent> confirmReservationSubscriberAdapter(AsyncEventHandler<ReservationValidatedEvent> subscriber) {
        return new AsyncEventSubscriberAdapter<>("reservationValidated", subscriber);
    }
}
