package io.pivotal.pal.data.rentaltruck.reservation.query;

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
public class TruckTypeByLocationAndDateQueryRepositoryTest {

    @Autowired
    private TruckTypeByLocationAndDateQueryRepository repository;

    @Autowired
    private CassandraOperations cassandraOperations;

    @After
    public void tearDown() {
        cassandraOperations.deleteAll(TruckTypeByLocationAndDate.class);
    }

    @Test
    public void findAllByKey() {
        TruckTypeByLocationAndDateKey key = new TruckTypeByLocationAndDateKey(
                "some-pickup-city",
                "some-pickup-state",
                LocalDate.of(2018, 02, 01),
                "some-truck-type"
        );

        TruckTypeByLocationAndDate truckTypeByLocationAndDate =
                new TruckTypeByLocationAndDate(key, "some-truck-make", "some-truck-model");

        TruckTypeByLocationAndDate savedTruckType = cassandraOperations.insert(truckTypeByLocationAndDate);

        Collection<TruckTypeByLocationAndDate> truckTypesByLocationAndDate = repository.findAllByKey(
                key.getPickupCity(),
                key.getPickupState(),
                key.getPickupDate()
        );

        assertThat(truckTypesByLocationAndDate)
                .hasSize(1)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(savedTruckType);
    }

    @Test
    public void findAllProjectedByKey() {
        TruckTypeByLocationAndDateKey key = new TruckTypeByLocationAndDateKey(
                "some-pickup-city",
                "some-pickup-state",
                LocalDate.of(2018, 02, 01),
                "some-truck-type"
        );

        TruckTypeByLocationAndDate truckTypeByLocationAndDate =
                new TruckTypeByLocationAndDate(key, "some-truck-make", "some-truck-model");

        cassandraOperations.insert(truckTypeByLocationAndDate);

        Collection<TruckTypeByLocationAndDateProjection> truckTypes = repository.findAllProjectedByKey(
                key.getPickupCity(),
                key.getPickupState(),
                key.getPickupDate()
        );

        assertThat(truckTypes)
                .hasSize(1)
                .extracting(TruckTypeByLocationAndDateProjection::getTruckType)
                .containsExactly("some-truck-type");
    }
}
