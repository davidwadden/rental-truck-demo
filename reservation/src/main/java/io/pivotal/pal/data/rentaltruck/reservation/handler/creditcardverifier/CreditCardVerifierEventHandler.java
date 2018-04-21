package io.pivotal.pal.data.rentaltruck.reservation.handler.creditcardverifier;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventPublisher;
import io.pivotal.pal.data.rentaltruck.reservation.entity.ReservationByConfirmationNumber;
import io.pivotal.pal.data.rentaltruck.reservation.event.CreditCardFailedEvent;
import io.pivotal.pal.data.rentaltruck.reservation.event.CreditCardVerifiedEvent;
import io.pivotal.pal.data.rentaltruck.reservation.event.ReservationInitializedEvent;
import io.pivotal.pal.data.rentaltruck.reservation.repository.ReservationByConfirmationNumberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

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
    private final ReservationByConfirmationNumberRepository repository;
    private final CreditCardService creditCardService;

    public CreditCardVerifierEventHandler(AsyncEventPublisher<CreditCardVerifiedEvent> creditCardVerifiedEventPublisher,
                                          AsyncEventPublisher<CreditCardFailedEvent> creditCardFailedEventPublisher,
                                          ReservationByConfirmationNumberRepository repository,
                                          CreditCardService creditCardService) {
        this.creditCardVerifiedEventPublisher = creditCardVerifiedEventPublisher;
        this.creditCardFailedEventPublisher = creditCardFailedEventPublisher;
        this.repository = repository;
        this.creditCardService = creditCardService;
    }

    @Override
    public void onEvent(ReservationInitializedEvent data) {
        logger.info("reservationRequested event: {}:", data);

        String confirmationNumber = data.getConfirmationNumber();

        // de-dup by updating record to status=processing (abort if already processing status)
        ReservationByConfirmationNumber reservation = repository.findByConfirmationNumber(confirmationNumber);
        Assert.notNull(reservation, "Reservation '" + confirmationNumber + "' not found");

        // verify credit card rest template to 3rd party API
        if (reservation.getStatus().equals("PROCESSING")) {
            logger.warn("already processing reservation {}", reservation.getConfirmationNumber());
            return;
        }

        reservation.setStatus("PROCESSING");
        repository.save(reservation);

        // TODO: discuss how this relies on data being passed through DB rather than events
        if (creditCardService.validateCreditCard(reservation.getCreditCardNumber(), 0.0 /*TODO: real value*/)) {
            // if success, emit credit card verified message downstream
            creditCardVerifiedEventPublisher.publish(new CreditCardVerifiedEvent());
        } else {
            // if failed, emit credit card failed event
            creditCardFailedEventPublisher.publish(new CreditCardFailedEvent());
        }
    }
}
