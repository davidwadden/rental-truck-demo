package io.pivotal.pal.data.rentaltruck.reservation.command.reservetruck;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventPublisher;
import io.pivotal.pal.data.rentaltruck.event.ReservationRequestedEvent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.inOrder;

@RunWith(MockitoJUnitRunner.class)
public class ReserveTruckCommandServiceTest {

    @Mock
    private AsyncEventPublisher<ReservationRequestedEvent> mockPublisher;
    @Mock
    private ConfirmationNumberFactory mockFactory;

    private ReserveTruckCommandService service;

    @Before
    public void setUp() {
        service = new ReserveTruckCommandService(mockPublisher, mockFactory);
    }

    @Test
    public void handleCommand() {
        String expectedConfirmationNumber = "12345";
        doReturn(expectedConfirmationNumber)
                .when(mockFactory)
                .make();

        ReserveTruckCommandDto commandDto = new ReserveTruckCommandDto(
                "some-truck-type",
                "some-metro-area",
                "some-pickup-store-id",
                "2018-01-01",
                "some-dropoff-store-id",
                "2018-02-01",
                "some-customer-name",
                "some-credit-card-number");

        String confirmationNumber = service.reserveTruck(commandDto);

        assertThat(confirmationNumber).isEqualTo(expectedConfirmationNumber);

        InOrder inOrder = inOrder(mockPublisher, mockFactory);
        inOrder.verify(mockFactory).make();

        ReservationRequestedEvent expectedEvent = new ReservationRequestedEvent(
                expectedConfirmationNumber,
                "some-truck-type",
                "some-metro-area",
                "some-pickup-store-id",
                LocalDate.of(2018, 01, 01),
                "some-dropoff-store-id",
                LocalDate.of(2018, 02, 01),
                "some-customer-name",
                "some-credit-card-number"
        );
        inOrder.verify(mockPublisher).publish(expectedEvent);
    }
}
