package io.pivotal.pal.data.rentaltruck.reservation.handler.savereservation;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventPublisher;
import io.pivotal.pal.data.rentaltruck.reservation.entity.ReservationByConfirmationNumber;
import io.pivotal.pal.data.rentaltruck.reservation.event.ReservationInitializedEvent;
import io.pivotal.pal.data.rentaltruck.reservation.event.ReservationRequestedEvent;
import io.pivotal.pal.data.rentaltruck.reservation.repository.ReservationByConfirmationNumberRepository;
import org.springframework.stereotype.Component;

/**
 * This handler subscribes to {@link ReservationRequestedEvent} and inserts record(s)
 * into the data store to de-dup potential re-delivered messages and to track the state
 * of the overall flow.
 */
@Component
public class SaveReservationEventHandler implements AsyncEventHandler<ReservationRequestedEvent> {

    private final AsyncEventPublisher<ReservationInitializedEvent> eventPublisher;
    private final ReservationByConfirmationNumberRepository repository;

    public SaveReservationEventHandler(AsyncEventPublisher<ReservationInitializedEvent> eventPublisher,
                                       ReservationByConfirmationNumberRepository repository) {
        this.eventPublisher = eventPublisher;
        this.repository = repository;
    }

    @Override
    public void onEvent(ReservationRequestedEvent data) {

        // guard clause: if record already exists (to de-dup message delivery)
        if (repository.exists(data.getConfirmationNumber())) {
            return;
        }

        // insert record with status=initialized
        ReservationByConfirmationNumber reservation = new ReservationByConfirmationNumber(
                data.getConfirmationNumber(),
                "INITIALIZED",
                data.getTruckType(),
                "stubbed-metro-area",
                data.getPickupStoreId(),
                data.getPickupDate(),
                data.getDropoffStoreId(),
                data.getDropoffDate(),
                data.getCustomerName(),
                "stubbed-credit-card-number"
        );
        repository.save(reservation);

        // emit reservation initialized event
        ReservationInitializedEvent event = new ReservationInitializedEvent(
                data.getConfirmationNumber(),
                data.getPickupStoreId(),
                data.getPickupDate(),
                data.getDropoffStoreId(),
                data.getDropoffDate(),
                data.getTruckType(),
                data.getCustomerName(),
                "stubbed-credit-card-number"
        );
        eventPublisher.publish(event);
    }
}
