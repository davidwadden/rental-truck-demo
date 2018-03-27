package io.pivotal.pal.data.rentaltruck.reservation;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Sets.newHashSet;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CatalogItemRepositoryTest {

    @Autowired
    private CatalogItemRepository repository;

    @After
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void testRepository() {
        CatalogItem catalogItem = new CatalogItem();
        String id = UUID.randomUUID().toString();
        catalogItem.setId(id);
        catalogItem.setTruckType("some-truck-type");

        CatalogItem savedCatalogItem = repository.save(catalogItem);

        assertThat(savedCatalogItem).isNotNull();
        assertThat(savedCatalogItem.getId()).isNotNull();

        Iterable<CatalogItem> catalogItems = repository.findAll(newHashSet(singletonList(id)));

        assertThat(catalogItems)
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactlyInAnyOrder(catalogItem);

        repository.delete(id);
    }
}
