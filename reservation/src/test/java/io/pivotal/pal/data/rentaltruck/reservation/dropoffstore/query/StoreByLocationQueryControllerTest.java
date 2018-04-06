package io.pivotal.pal.data.rentaltruck.reservation.dropoffstore.query;

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
public class StoreByLocationQueryControllerTest {

    @Mock
    private StoreByLocationQueryRepository mockRepository;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        StoreByLocationQueryController controller = new StoreByLocationQueryController(mockRepository);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void listStoresByLocation() throws Exception {
        StoreByLocationProjection storeProjection = makeStoreByLocationProjection();
        doReturn(singletonList(storeProjection))
                .when(mockRepository)
                .findAllProjectedByLocation(any(), any());

        mockMvc
                .perform(
                        get("/stores-by-location")
                                .param("city", "some-city")
                                .param("state", "some-state")
                                .accept(MediaType.APPLICATION_JSON_UTF8)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        verify(mockRepository).findAllProjectedByLocation("some-city", "some-state");
    }

    private static StoreByLocationProjection makeStoreByLocationProjection() {
        return new StoreByLocationProjection() {
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
