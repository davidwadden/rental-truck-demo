package io.pivotal.pal.data.rentaltruck.reservation.query.storebymetroarea;

import io.pivotal.pal.data.rentaltruck.reservation.repo.StoreByMetroAreaProjection;
import io.pivotal.pal.data.rentaltruck.reservation.repo.StoreByMetroAreaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static java.util.Collections.singletonList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class StoreByMetroAreaQueryControllerTest {

    @Mock
    private StoreByMetroAreaRepository mockRepository;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        StoreByMetroAreaQueryController controller = new StoreByMetroAreaQueryController(mockRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void listStoresByLocation() throws Exception {
        StoreByMetroAreaProjection storeProjection = makeStoreByMetroAreaProjection();
        doReturn(singletonList(storeProjection))
                .when(mockRepository)
                .findAllProjectedByLocation(any());

        mockMvc
                .perform(
                        get("/stores-by-metro-area")
                                .param("metroArea", "some-metro-area")
                                .accept(MediaType.APPLICATION_JSON_UTF8)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        verify(mockRepository).findAllProjectedByLocation("some-metro-area");
    }

    private static StoreByMetroAreaProjection makeStoreByMetroAreaProjection() {
        return new StoreByMetroAreaProjection() {
            @Override
            public String getStoreId() {
                return "some-store-id";
            }

            @Override
            public String getHoursOfOperation() {
                return "some-hours-of-operation";
            }

            @Override
            public String getDescription() {
                return "some-description";
            }
        };
    }
}
