package io.pivotal.pal.data.rentaltruck.reservation.command.createreservation;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventPublisher;
import io.pivotal.pal.data.rentaltruck.reservation.event.ReservationCreatedEvent;
import org.springframework.stereotype.Service;

@Service
public class CreateReservationCommandService {

    private final AsyncEventPublisher<ReservationCreatedEvent> eventPublisher;

    public CreateReservationCommandService(AsyncEventPublisher<ReservationCreatedEvent> eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void handleCommand(CreateReservationCommandDto commandDto) {
        ReservationCreatedEvent event = new ReservationCreatedEvent(
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

        eventPublisher.publish(event);
    }

}
