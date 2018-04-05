package io.pivotal.pal.data.rentaltruck.reservation.query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class TruckTypeQueryControllerTest {

    @Mock
    private TruckTypeQueryRepository mockRepository;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        TruckTypeQueryController controller = new TruckTypeQueryController(mockRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void findAllTruckTypes() throws Exception {
        TruckType truckType1 = new TruckType("some-truck-type-1");
        TruckType truckType2 = new TruckType("some-truck-type-2");
        doReturn(asList(truckType1, truckType2))
                .when(mockRepository)
                .findAll();

        mockMvc
                .perform(
                        get("/truck-types")
                                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        verify(mockRepository).findAll();
    }

    @Test
    public void findTruckTypesByPickupLocationDate() throws Exception {
        TruckType truckType1 = new TruckType("some-truck-type-1");
        TruckType truckType2 = new TruckType("some-truck-type-2");
        doReturn(asList(truckType1, truckType2))
                .when(mockRepository)
                .findAll();

        mockMvc
                .perform(
                        get("/truck-types?city=some-city&state=some-state&pickupDate=2018-03-01")
                                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        verify(mockRepository).findAll();
    }
}
