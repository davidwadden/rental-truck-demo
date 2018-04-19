package io.pivotal.pal.data.rentaltruck.reservation.command.createreservation;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventPublisher;
import io.pivotal.pal.data.rentaltruck.reservation.event.ReservationRequestedEvent;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CreateReservationCommandServiceTest {

    @Mock
    private AsyncEventPublisher<ReservationRequestedEvent> mockPublisher;
    @Mock
    private ConfirmationNumberFactory mockFactory;

    private CreateReservationCommandService service;

    @Before
    public void setUp() {
        service = new CreateReservationCommandService(mockPublisher, mockFactory);
    }

    @Test
    public void handleCommand() {
        String expectedConfirmationNumber = "12345";
        doReturn(expectedConfirmationNumber)
                .when(mockFactory)
                .make();

        CreateReservationCommandDto commandDto = new CreateReservationCommandDto(
                "some-pickup-city",
                "some-pickup-state",
                "some-pickup-date",
                "some-dropoff-city",
                "some-dropoff-state",
                "some-dropoff-date",
                "some-truck-type",
                "some-customer-name"
        );

        String confirmationNumber = service.handleCommand(commandDto);

        assertThat(confirmationNumber).isEqualTo(expectedConfirmationNumber);

        InOrder inOrder = inOrder(mockPublisher, mockFactory);
        inOrder.verify(mockFactory).make();

        ReservationRequestedEvent expectedEvent = new ReservationRequestedEvent(
                expectedConfirmationNumber,
                "some-pickup-city",
                "some-pickup-state",
                "some-pickup-date",
                "some-dropoff-city",
                "some-dropoff-state",
                "some-dropoff-date",
                "some-truck-type",
                "some-customer-name"
        );
        inOrder.verify(mockPublisher).publish(expectedEvent);
    }
}
