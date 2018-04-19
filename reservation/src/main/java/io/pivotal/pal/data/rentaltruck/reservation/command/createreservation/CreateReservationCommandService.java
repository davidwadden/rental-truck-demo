package io.pivotal.pal.data.rentaltruck.reservation.command.createreservation;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventPublisher;
import io.pivotal.pal.data.rentaltruck.reservation.event.ReservationRequestedEvent;
import org.springframework.stereotype.Service;

@Service
public class CreateReservationCommandService {

    private final AsyncEventPublisher<ReservationRequestedEvent> eventPublisher;
    private final ConfirmationNumberFactory factory;

    public CreateReservationCommandService(AsyncEventPublisher<ReservationRequestedEvent> eventPublisher,
                                           ConfirmationNumberFactory factory) {
        this.eventPublisher = eventPublisher;
        this.factory = factory;
    }

    public String handleCommand(CreateReservationCommandDto commandDto) {

        String confirmationNumber = factory.make();

        ReservationRequestedEvent event = new ReservationRequestedEvent(
                confirmationNumber,
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

        return confirmationNumber;
    }
}
