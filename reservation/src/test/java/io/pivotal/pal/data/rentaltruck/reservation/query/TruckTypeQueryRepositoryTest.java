package io.pivotal.pal.data.rentaltruck.reservation.query;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.test.context.junit4.SpringRunner;

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
        TruckType truckType1 = new TruckType("some-truck-type-1");
        TruckType truckType2 = new TruckType("some-truck-type-2");

        cassandraOperations.insert(truckType1);
        cassandraOperations.insert(truckType2);

        Iterable<TruckType> catalogItems = repository.findAll();
        assertThat(catalogItems)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(truckType1, truckType2);
    }
}
