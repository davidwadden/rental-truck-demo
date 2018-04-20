package io.pivotal.pal.data.rentaltruck.reservation.query.truckcount;

import io.pivotal.pal.data.rentaltruck.reservation.entity.TruckCountByMetroAreaTruckTypeStoreDate;
import io.pivotal.pal.data.rentaltruck.reservation.entity.TruckCountByMetroAreaTruckTypeStoreDateKey;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.Repository;

import java.time.LocalDate;
import java.util.Collection;

public interface TruckCountByMetroAreaTruckTypeStoreDateRepository
        extends Repository<TruckCountByMetroAreaTruckTypeStoreDate, TruckCountByMetroAreaTruckTypeStoreDateKey> {

    @Query("SELECT * FROM truck_count_by_metro_area_truck_type_store_date WHERE metro_area = ?0 AND truck_type = ?1")
    Collection<TruckCountByMetroAreaTruckTypeStoreDate> findAllByMetroAreaAndTruckType(String metroArea, String truckType);

    @Query("SELECT * FROM truck_count_by_metro_area_truck_type_store_date WHERE metro_area = ?0 AND truck_type = ?1 AND reserve_date = ?2")
    Collection<TruckCountByMetroAreaTruckTypeStoreDate> findAllByMetroAreaAndTruckTypeAndDate(String metroArea,
                                                                                              String truckType,
                                                                                              LocalDate reserveDate);

    @Query("SELECT * FROM truck_count_by_metro_area_truck_type_store_date WHERE metro_area = ?0 AND truck_type = ?1 AND reserve_date >= ?2 AND reserve_date <= ?3")
    Collection<TruckCountByMetroAreaTruckTypeStoreDate> findAllByMetroAreaAndTruckTypeAndDateRange(String metroArea,
                                                                                                   String truckType,
                                                                                                   LocalDate reserveStartDate,
                                                                                                   LocalDate reserveEndDate);
}
