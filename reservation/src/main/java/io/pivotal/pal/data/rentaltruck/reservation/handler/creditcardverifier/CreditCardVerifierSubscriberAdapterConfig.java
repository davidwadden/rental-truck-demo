package io.pivotal.pal.data.rentaltruck.reservation.handler.creditcardverifier;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventSubscriberAdapter;
import io.pivotal.pal.data.rentaltruck.reservation.event.ReservationRequestedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CreditCardVerifierSubscriberAdapterConfig {

    @Bean
    public AsyncEventSubscriberAdapter<ReservationRequestedEvent> reservationCreatedSubscriberAdapter(AsyncEventHandler<ReservationRequestedEvent> subscriber) {
        return new AsyncEventSubscriberAdapter<>("reservationCreated", subscriber);
    }
}
