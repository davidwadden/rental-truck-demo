package io.pivotal.pal.data.rentaltruck.reservation.query.naivetrucktype;

import org.junit.After;
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
public class TruckTypeQueryRepositoryTest {

    @Autowired
    private TruckTypeQueryRepository repository;

    @Autowired
    private CassandraOperations cassandraOperations;

    @After
    public void tearDown() {
        cassandraOperations.deleteAll(TruckType.class);
    }

    @Test
    public void findAll() {
        TruckType truckType1 = new TruckType("some-truck-type-1", "some-truck-make-1", "some-truck-model-1");
        TruckType truckType2 = new TruckType("some-truck-type-2", "some-truck-make-2", "some-truck-model-2");

        cassandraOperations.insert(truckType1);
        cassandraOperations.insert(truckType2);

        Iterable<TruckType> truckTypes = repository.findAll();
        assertThat(truckTypes)
                .usingFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(truckType1, truckType2);
    }

    @Test
    public void findAllProjectedBy() {
        TruckType truckType1 = new TruckType("some-truck-type-1", "some-truck-make-1", "some-truck-model-1");
        TruckType truckType2 = new TruckType("some-truck-type-2", "some-truck-make-2", "some-truck-model-2");

        cassandraOperations.insert(truckType1);
        cassandraOperations.insert(truckType2);

        Collection<TruckTypeProjection> truckTypes = repository.findAllProjectedBy();
        assertThat(truckTypes)
                .extracting(TruckTypeProjection::getTruckType)
                .containsExactlyInAnyOrder("some-truck-type-1", "some-truck-type-2");
    }
}
