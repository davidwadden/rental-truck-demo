package io.pivotal.pal.data.rentaltruck.reservation.command.reservetruck;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventPublisher;
import io.pivotal.pal.data.rentaltruck.reservation.event.ReservationRequestedEvent;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReserveTruckCommandService {

    private final AsyncEventPublisher<ReservationRequestedEvent> eventPublisher;
    private final ConfirmationNumberFactory factory;

    public ReserveTruckCommandService(AsyncEventPublisher<ReservationRequestedEvent> eventPublisher,
                                      ConfirmationNumberFactory factory) {
        this.eventPublisher = eventPublisher;
        this.factory = factory;
    }

    public String reserveTruck(ReserveTruckCommandDto commandDto) {

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
