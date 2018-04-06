package io.pivotal.pal.data.rentaltruck.reservation.dropoffstore.query;

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
public class StoreByLocationQueryRepositoryTest {

    @Autowired
    private StoreByLocationQueryRepository repository;

    @Autowired
    private CassandraOperations cassandraOperations;

    @Test
    public void findAllProjectedByLocation() {
        StoreByLocationKey key =
                new StoreByLocationKey("some-dropoff-city", "some-dropoff-state", "some-store-id");
        StoreByLocation storeByLocation = new StoreByLocation(key, "some-address", "some-description");

        cassandraOperations.insert(storeByLocation);

        Collection<StoreByLocationProjection> storesProjection =
                repository.findAllProjectedByLocation("some-dropoff-city", "some-dropoff-state");

        assertThat(storesProjection).hasSize(1);
        assertThat(storesProjection)
                .extracting(StoreByLocationProjection::getStoreId)
                .containsExactly("some-store-id");
        assertThat(storesProjection)
                .extracting(StoreByLocationProjection::getAddress)
                .containsExactly("some-address");
        assertThat(storesProjection)
                .extracting(StoreByLocationProjection::getDescription)
                .containsExactly("some-description");
    }

}
