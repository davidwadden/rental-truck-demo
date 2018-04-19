package io.pivotal.pal.data.rentaltruck.reservation.query.dropoffstore;

import io.pivotal.pal.data.rentaltruck.reservation.entity.StoreByLocation;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Collection;

public interface StoreByLocationQueryRepository extends Repository<StoreByLocation, StoreByLocation> {

    @Query("SELECT * FROM stores_by_location WHERE dropoff_city = ?0 AND dropoff_state = ?1")
    Collection<StoreByLocationProjection> findAllProjectedByLocation(String dropoffCity, String dropoffState);

}
