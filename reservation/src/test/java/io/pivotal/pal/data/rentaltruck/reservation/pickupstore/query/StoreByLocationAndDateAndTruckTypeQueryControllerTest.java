package io.pivotal.pal.data.rentaltruck.reservation.pickupstore.query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static java.util.Collections.singletonList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class StoreByLocationAndDateAndTruckTypeQueryControllerTest {

    @Mock
    private StoreByLocationAndDateAndTruckTypeQueryRepository mockRepository;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        StoreByLocationAndDateAndTruckTypeQueryController controller =
                new StoreByLocationAndDateAndTruckTypeQueryController(mockRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void findStoresByLocationAndDateAndTruckType() throws Exception {
        StoreByLocationAndDateAndTruckTypeProjection storeProjection = makeStoreByLocationAndDateAndTruckTypeProjection();
        doReturn(singletonList(storeProjection))
                .when(mockRepository)
                .findAllProjectedByLocationAndDateAndTruckType(any(), any(), any(), any());

        mockMvc
                .perform(
                        get("/stores")
                                .param("city", "some-city")
                                .param("state", "some-state")
                                .param("pickupDate", "2018-03-01")
                                .param("truckType", "some-truck-type")
                                .accept(MediaType.APPLICATION_JSON_UTF8)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].storeId").value("some-store-id"))
                .andExpect(jsonPath("$[0].address").value("some-address"))
                .andExpect(jsonPath("$[0].description").value("some-description"))
                .andReturn();

        verify(mockRepository).findAllProjectedByLocationAndDateAndTruckType(
                "some-city",
                "some-state",
                LocalDate.of(2018, 03, 01),
                "some-truck-type"
        );
    }

    private static StoreByLocationAndDateAndTruckTypeProjection makeStoreByLocationAndDateAndTruckTypeProjection() {
        return new StoreByLocationAndDateAndTruckTypeProjection() {

            @Override
            public String getStoreId() {
                return "some-store-id";
            }

            @Override
            public String getAddress() {
                return "some-address";
            }

            @Override
            public String getDescription() {
                return "some-description";
            }
        };
    }

}
