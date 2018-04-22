package io.pivotal.pal.data.rentaltruck.fleet.repository;

import io.pivotal.pal.data.rentaltruck.fleet.entity.TruckByStore;
import io.pivotal.pal.data.rentaltruck.fleet.entity.TruckByStoreKey;
import org.springframework.data.repository.CrudRepository;

public interface TruckByStoreRepository extends CrudRepository<TruckByStore, TruckByStoreKey> {
}
