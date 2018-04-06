package io.pivotal.pal.data.rentaltruck.reservation.query;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.Repository;

import java.time.LocalDate;
import java.util.Collection;

public interface TruckTypeByLocationAndDateQueryRepository extends Repository<TruckTypeByLocationAndDate, TruckTypeByLocationAndDateKey> {


    @Query("SELECT * FROM truck_types_by_location_and_date WHERE pickup_city = ?0 AND pickup_state = ?1 AND pickup_date = ?2")
    Collection<TruckTypeByLocationAndDate> findAllByKey(String pickupCity,
                                                        String pickupState,
                                                        LocalDate pickupDate);

    @Query("SELECT * FROM truck_types_by_location_and_date WHERE pickup_city = ?0 AND pickup_state = ?1 AND pickup_date = ?2")
    Collection<TruckTypeByLocationAndDateProjection> findAllProjectedByKey(String pickupCity,
                                                                           String pickupState,
                                                                           LocalDate pickupDate);

}
