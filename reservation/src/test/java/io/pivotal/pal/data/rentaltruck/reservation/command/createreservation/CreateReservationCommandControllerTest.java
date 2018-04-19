package io.pivotal.pal.data.rentaltruck.reservation.command.createreservation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class CreateReservationCommandControllerTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private CreateReservationCommandService mockService;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        CreateReservationCommandController controller = new CreateReservationCommandController(mockService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void createReservation() throws Exception {
        doReturn("some-confirmation-number")
                .when(mockService)
                .handleCommand(any());

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
        byte[] commandDtoBytes = objectMapper.writeValueAsBytes(commandDto);

        mockMvc
                .perform(
                        post("/reservations")
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .content(commandDtoBytes)
                )
                .andExpect(status().isAccepted())
                .andExpect(redirectedUrlPattern("**/reservations/some-confirmation-number"))
                .andReturn();

        verify(mockService).handleCommand(commandDto);
    }
}
