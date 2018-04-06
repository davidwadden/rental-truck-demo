package io.pivotal.pal.data.rentaltruck.reservation.pickupstore.query;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StoreByLocationAndDateAndTruckTypeQueryRepositoryTest {

    @Autowired
    private StoreByLocationAndDateAndTruckTypeQueryRepository repository;

    @Autowired
    private CassandraOperations cassandraOperations;

    @After
    public void tearDown() {
        cassandraOperations.deleteAll(StoreByLocationAndDateAndTruckType.class);
    }

    @Test
    public void findAllProjectedByLocationAndDateAndTruckType() {
        StoreByLocationAndDateAndTruckTypeKey key = new StoreByLocationAndDateAndTruckTypeKey(
                "some-city",
                "some-state",
                LocalDate.of(2018, 03, 01),
                "some-truck-type",
                "some-store-id"
        );
        StoreByLocationAndDateAndTruckType storeByLocationAndDateAndTruckType = new
                StoreByLocationAndDateAndTruckType(key, "some-address", "some-description");

        cassandraOperations.insert(storeByLocationAndDateAndTruckType);

        Collection<StoreByLocationAndDateAndTruckTypeProjection> stores = repository.findAllProjectedByLocationAndDateAndTruckType(
                "some-city",
                "some-state",
                LocalDate.of(2018, 03, 01),
                "some-truck-type"
        );

        assertThat(stores)
                .hasSize(1)
                .extracting(StoreByLocationAndDateAndTruckTypeProjection::getStoreId)
                .containsExactly("some-store-id");
    }
}
