package io.pivotal.pal.data.rentaltruck.reservation.repository;

import io.pivotal.pal.data.rentaltruck.reservation.entity.StoreByMetroArea;
import io.pivotal.pal.data.rentaltruck.reservation.entity.StoreByMetroAreaKey;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreByMetroAreaRepositoryTest {

    @Autowired
    private StoreByMetroAreaRepository repository;

    @Autowired
    private CassandraOperations cassandraOperations;

    @Test
    public void findAllProjectedByLocation() {
        StoreByMetroAreaKey key =
                new StoreByMetroAreaKey("some-metro-area", "some-store-id");
        StoreByMetroArea storeByMetroArea =
                new StoreByMetroArea(key, "some-hours-of-operation", "some-description");

        cassandraOperations.insert(storeByMetroArea);

        Collection<StoreByMetroAreaProjection> storesProjection =
                repository.findAllProjectedByLocation("some-metro-area");

        assertThat(storesProjection).hasSize(1);
        assertThat(storesProjection)
                .extracting(StoreByMetroAreaProjection::getStoreId)
                .containsExactly("some-store-id");
        assertThat(storesProjection)
                .extracting(StoreByMetroAreaProjection::getHoursOfOperation)
                .containsExactly("some-hours-of-operation");
        assertThat(storesProjection)
                .extracting(StoreByMetroAreaProjection::getDescription)
                .containsExactly("some-description");
    }
}
