package io.pivotal.pal.data.rentaltruck.reservation.handler.creditcardverifier;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventPublisher;
import io.pivotal.pal.data.rentaltruck.reservation.event.CreditCardFailedEvent;
import io.pivotal.pal.data.rentaltruck.reservation.event.CreditCardVerifiedEvent;
import io.pivotal.pal.data.rentaltruck.reservation.event.ReservationInitializedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * This event handler subscribes to the {@link ReservationInitializedEvent}, upserts the
 * row in Cassandra to {@code Pending} state to de-dup potential re-deliveries from the
 * event system.
 * <p/>
 * After making a REST call to a third party payment gateway, the handler advances the
 * workflow by emitting a message to either the {@code Verified} or {@code Failed} topics.
 */
@Component
public class CreditCardVerifierEventHandler implements AsyncEventHandler<ReservationInitializedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(CreditCardVerifierEventHandler.class);

    private final AsyncEventPublisher<CreditCardVerifiedEvent> creditCardVerifiedEventPublisher;
    private final AsyncEventPublisher<CreditCardFailedEvent> creditCardFailedEventPublisher;

    public CreditCardVerifierEventHandler(AsyncEventPublisher<CreditCardVerifiedEvent> creditCardVerifiedEventPublisher,
                                          AsyncEventPublisher<CreditCardFailedEvent> creditCardFailedEventPublisher) {
        this.creditCardVerifiedEventPublisher = creditCardVerifiedEventPublisher;
        this.creditCardFailedEventPublisher = creditCardFailedEventPublisher;
    }

    @Override
    public void onEvent(ReservationInitializedEvent data) {
        logger.info("reservationRequested event: {}:", data);

        // de-dup by updating record to status=processing (abort if already processing status)

        // verify credit card rest template to 3rd party API
        // if success, emit credit card verified message downstream
        // if failed, emit credit card failed event
    }
}
