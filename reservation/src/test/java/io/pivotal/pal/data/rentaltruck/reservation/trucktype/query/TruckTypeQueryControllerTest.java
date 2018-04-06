package io.pivotal.pal.data.rentaltruck.reservation.trucktype.query;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    public void listTruckTypes() throws Exception {
        TruckTypeProjection truckType1 = makeTruckTypeProjection("0");
        TruckTypeProjection truckType2 = makeTruckTypeProjection("1");

        doReturn(asList(truckType1, truckType2))
                .when(mockRepository)
                .findAllProjectedBy();

        mockMvc
                .perform(
                        get("/truck-types")
                                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].truckType").value("some-truck-type-0"))
                .andExpect(jsonPath("$[0].truckMake").value("some-truck-make-0"))
                .andExpect(jsonPath("$[0].truckModel").value("some-truck-model-0"))
                .andExpect(jsonPath("$[1].truckType").value("some-truck-type-1"))
                .andExpect(jsonPath("$[1].truckMake").value("some-truck-make-1"))
                .andExpect(jsonPath("$[1].truckModel").value("some-truck-model-1"))
                .andReturn();

        verify(mockRepository).findAllProjectedBy();
    }

    private static TruckTypeProjection makeTruckTypeProjection(String suffix) {
        return new TruckTypeProjection() {
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
