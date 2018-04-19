package io.pivotal.pal.data.rentaltruck.reservation.handler.truckavailability;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventPublisher;
import io.pivotal.pal.data.rentaltruck.reservation.event.CreditCardVerifiedEvent;
import io.pivotal.pal.data.rentaltruck.reservation.event.ReservationValidatedEvent;
import org.springframework.stereotype.Component;

/**
 * This handler subscribes to {@link CreditCardVerifiedEvent} and checks against Cassandra
 * whether the store has the desired truck type available.
 */
@Component
public class TruckAvailabilityEventHandler implements AsyncEventHandler<CreditCardVerifiedEvent> {

    private final AsyncEventPublisher<ReservationValidatedEvent> eventPublisher;

    public TruckAvailabilityEventHandler(AsyncEventPublisher<ReservationValidatedEvent> eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void onEvent(CreditCardVerifiedEvent data) {
        // de-dup by updating record to status=validating (abort if already processing status)

        // query reservations for a given store / truck type over the desired date span to
        //   see whether there is at least one truck available to be reserved

        // if truck available, emit message to reservation validated topic
        // if zero trucks available, emit message to reservation validation failed topic (doesn't exist yet)

        // exceptional case:
        //   - retry if infrastructure problem  (Can't do too much, maybe 3X)
        //   - else, send to manual dead letter queue
        //   - what if data size exceeds column length ?  non-recoverable error
    }
}
