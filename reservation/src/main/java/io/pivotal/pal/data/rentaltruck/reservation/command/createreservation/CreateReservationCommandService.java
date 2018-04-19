package io.pivotal.pal.data.rentaltruck.reservation.command.createreservation;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventPublisher;
import io.pivotal.pal.data.rentaltruck.reservation.event.ReservationRequestedEvent;
import org.springframework.stereotype.Service;

@Service
public class CreateReservationCommandService {

    private final AsyncEventPublisher<ReservationRequestedEvent> eventPublisher;

    public CreateReservationCommandService(AsyncEventPublisher<ReservationRequestedEvent> eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void handleCommand(CreateReservationCommandDto commandDto) {
        // TODO: generate confirmation number and return to caller, append to event

        ReservationRequestedEvent event = new ReservationRequestedEvent(
                commandDto.getConfirmationNumber(),
                commandDto.getPickupCity(),
                commandDto.getPickupState(),
                commandDto.getPickupDate(),
                commandDto.getDropoffCity(),
                commandDto.getDropoffState(),
                commandDto.getDropoffDate(),
                commandDto.getTruckType(),
                commandDto.getCustomerName()
        );

        // FIXME: emit reservationRequestedEvent
        eventPublisher.publish(event);
    }

}
