package io.pivotal.pal.data.rentaltruck.reservation.command.createreservation;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventPublisher;
import io.pivotal.pal.data.rentaltruck.reservation.event.ReservationRequestedEvent;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
                commandDto.getTruckType(),
                commandDto.getMetroArea(),
                commandDto.getPickupStoreId(),
                LocalDate.parse(commandDto.getPickupDate()),
                commandDto.getDropoffStoreId(),
                LocalDate.parse(commandDto.getDropoffDate()),
                commandDto.getCustomerName(),
                commandDto.getCreditCardNumber()
        );

        eventPublisher.publish(event);

        return confirmationNumber;
    }
}
