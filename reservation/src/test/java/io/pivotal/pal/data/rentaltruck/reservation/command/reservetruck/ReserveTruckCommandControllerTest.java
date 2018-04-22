package io.pivotal.pal.data.rentaltruck.reservation.command.reservetruck;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class ReserveTruckCommandControllerTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Mock
    private ReserveTruckCommandService mockService;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        ReserveTruckCommandController controller = new ReserveTruckCommandController(mockService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void reserveTruck() throws Exception {
        doReturn("some-confirmation-number")
                .when(mockService)
                .reserveTruck(any());

        ReserveTruckCommandDto commandDto = new ReserveTruckCommandDto(
                "some-truck-type",
                "some-metro-area",
                "some-pickup-store-id",
                "some-pickup-date",
                "some-dropoff-store-id",
                "some-dropoff-date",
                "some-customer-name",
                "some-credit-card-number");
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

        verify(mockService).reserveTruck(commandDto);
    }
}
