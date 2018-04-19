package io.pivotal.pal.data.rentaltruck.reservation.repo;

import io.pivotal.pal.data.rentaltruck.reservation.entity.StoreByMetroArea;
import io.pivotal.pal.data.rentaltruck.reservation.entity.StoreByMetroAreaKey;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Collection;

public interface StoreByMetroAreaRepository extends Repository<StoreByMetroArea, StoreByMetroAreaKey> {

    @Query("SELECT * FROM stores_by_metro_area WHERE metro_area = ?0")
    Collection<StoreByMetroAreaProjection> findAllProjectedByLocation(String metroArea);

}
