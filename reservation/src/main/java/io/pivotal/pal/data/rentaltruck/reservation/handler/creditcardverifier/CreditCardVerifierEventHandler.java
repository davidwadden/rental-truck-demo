package io.pivotal.pal.data.rentaltruck.reservation.handler.creditcardverifier;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltruck.reservation.event.ReservationRequestedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CreditCardVerifierEventHandler implements AsyncEventHandler<ReservationRequestedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(CreditCardVerifierEventHandler.class);

    // dependency inject event publisher to credit card verified event publisher
    // dependency inject event publisher to credit card failed event publisher

    @Override
    public void onEvent(ReservationRequestedEvent data) {
        logger.info("reservationRequested event: {}:", data);

        // verify credit card   rest template to 3rd party API

        // emit credit card verified message downstream

        // if failed, emit credit card failed event
    }
}
