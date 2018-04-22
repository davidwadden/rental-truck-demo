package io.pivotal.pal.data.rentaltruck.fleet.repository;

import io.pivotal.pal.data.rentaltruck.fleet.entity.TruckByStore;
import io.pivotal.pal.data.rentaltruck.fleet.entity.TruckByStoreKey;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface TruckByStoreRepository extends CrudRepository<TruckByStore, TruckByStoreKey> {

    @Query("SELECT * FROM trucks_by_store WHERE store_id = ?0")
    Collection<TruckByStore> findAllByStoreId(String storeId);

}
