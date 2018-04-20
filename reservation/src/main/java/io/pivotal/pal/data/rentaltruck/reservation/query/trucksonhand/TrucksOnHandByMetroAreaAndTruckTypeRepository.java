package io.pivotal.pal.data.rentaltruck.reservation.query.trucksonhand;

import io.pivotal.pal.data.rentaltruck.reservation.entity.TrucksOnHandByMetroAreaAndTruckType;
import io.pivotal.pal.data.rentaltruck.reservation.entity.TrucksOnHandByMetroAreaAndTruckTypeKey;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.Repository;

import java.time.LocalDate;
import java.util.Collection;

public interface TrucksOnHandByMetroAreaAndTruckTypeRepository
        extends Repository<TrucksOnHandByMetroAreaAndTruckType, TrucksOnHandByMetroAreaAndTruckTypeKey> {

    @Query("SELECT * FROM trucks_on_hand_by_metro_area_and_truck_type WHERE metro_area = ?0 AND truck_type = ?1")
    Collection<TrucksOnHandByMetroAreaAndTruckType> findAllByMetroAreaAndTruckType(String metroArea, String truckType);

    @Query("SELECT * FROM trucks_on_hand_by_metro_area_and_truck_type WHERE metro_area = ?0 AND truck_type = ?1 AND reserve_date = ?2")
    Collection<TrucksOnHandByMetroAreaAndTruckType> findAllByMetroAreaAndTruckTypeAndDate(String metroArea,
                                                                                          String truckType,
                                                                                          LocalDate reserveDate);

    @Query("SELECT * FROM trucks_on_hand_by_metro_area_and_truck_type WHERE metro_area = ?0 AND truck_type = ?1 AND reserve_date >= ?2 AND reserve_date <= ?3")
    Collection<TrucksOnHandByMetroAreaAndTruckType> findAllByMetroAreaAndTruckTypeAndDateRange(String metroArea,
                                                                                               String truckType,
                                                                                               LocalDate reserveStartDate,
                                                                                               LocalDate reserveEndDate);
}
