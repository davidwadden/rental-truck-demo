package io.pivotal.pal.data.rentaltruck.fleet.repository;

import io.pivotal.pal.data.rentaltruck.fleet.entity.RentalByTruck;
import io.pivotal.pal.data.rentaltruck.fleet.entity.RentalByTruckKey;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface RentalByTruckRepository extends CrudRepository<RentalByTruck, RentalByTruckKey> {

    @Query("SELECT * FROM rentals_by_truck WHERE truck_id = ?0")
    Collection<RentalByTruck> findAllByTruckId(String truckId);

}
