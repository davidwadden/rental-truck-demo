package io.pivotal.pal.data.rentaltruck.reservation.trucktype.query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class TruckTypeByLocationAndDateQueryControllerTest {

    @Mock
    private TruckTypeByLocationAndDateQueryRepository mockRepository;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        TruckTypeByLocationAndDateQueryController controller = new TruckTypeByLocationAndDateQueryController(mockRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void listTruckTypesByLocationAndDate() throws Exception {
        List<TruckTypeByLocationAndDateProjection> toBeReturned = asList(
                makeTruckTypeByLocationAndDateProjection("0"),
                makeTruckTypeByLocationAndDateProjection("1")
        );
        doReturn(toBeReturned)
                .when(mockRepository)
                .findAllProjectedByKey(any(), any(), any());

        mockMvc
                .perform(
                        get("/truck-types-by-location-and-date")
                                .param("city", "some-city")
                                .param("state", "some-state")
                                .param("pickupDate", "2018-03-01")
                                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].truckType").value("some-truck-type-0"))
                .andExpect(jsonPath("$[0].truckMake").value("some-truck-make-0"))
                .andExpect(jsonPath("$[0].truckModel").value("some-truck-model-0"))
                .andExpect(jsonPath("$[1].truckType").value("some-truck-type-1"))
                .andExpect(jsonPath("$[1].truckMake").value("some-truck-make-1"))
                .andExpect(jsonPath("$[1].truckModel").value("some-truck-model-1"))
                .andReturn();

        verify(mockRepository).findAllProjectedByKey(
                "some-city",
                "some-state",
                LocalDate.of(2018, 03, 01)
        );
    }

    private static TruckTypeByLocationAndDateProjection makeTruckTypeByLocationAndDateProjection(String suffix) {
        return new TruckTypeByLocationAndDateProjection() {
            @Override
            public String getTruckType() {
                return String.format("some-truck-type-%s", suffix);
            }

            @Override
            public String getTruckMake() {
                return String.format("some-truck-make-%s", suffix);
            }

            @Override
            public String getTruckModel() {
                return String.format("some-truck-model-%s", suffix);
            }
        };
    }
}
