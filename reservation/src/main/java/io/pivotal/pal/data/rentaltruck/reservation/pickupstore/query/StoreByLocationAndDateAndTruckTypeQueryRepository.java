package io.pivotal.pal.data.rentaltruck.reservation.pickupstore.query;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.Repository;

import java.time.LocalDate;
import java.util.Collection;

public interface StoreByLocationAndDateAndTruckTypeQueryRepository extends Repository<StoreByLocationAndDateAndTruckType, StoreByLocationAndDateAndTruckTypeKey> {

    @Query("SELECT * FROM stores_by_location_and_date_and_truck_type WHERE pickup_city = ?0 AND pickup_state = ?1 AND pickup_date = ?2 AND truck_type = ?3")
    Collection<StoreByLocationAndDateAndTruckTypeProjection> findAllProjectedByLocationAndDateAndTruckType(String pickupCity,
                                                                                                           String pickupState,
                                                                                                           LocalDate pickupDate,
                                                                                                           String truckType);

}
