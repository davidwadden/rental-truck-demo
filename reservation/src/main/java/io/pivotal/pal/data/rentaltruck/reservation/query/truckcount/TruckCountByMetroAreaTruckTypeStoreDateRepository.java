package io.pivotal.pal.data.rentaltruck.reservation.query.truckcount;

import io.pivotal.pal.data.rentaltruck.reservation.entity.TruckCountByMetroAreaTruckTypeStoreDate;
import io.pivotal.pal.data.rentaltruck.reservation.entity.TruckCountByMetroAreaTruckTypeStoreDateKey;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.Repository;

import java.time.LocalDate;
import java.util.Collection;

public interface TruckCountByMetroAreaTruckTypeStoreDateRepository
        extends Repository<TruckCountByMetroAreaTruckTypeStoreDate, TruckCountByMetroAreaTruckTypeStoreDateKey> {

    @Query("SELECT * FROM truck_count_by_metro_area_truck_type_store_date WHERE metro_area = ?0 AND truck_type = ?1 AND store_id = ?2 AND reserve_date = ?3")
    Collection<TruckCountByMetroAreaTruckTypeStoreDate> findAllByMetroAreaAndTruckTypeAndStoreIdAndDate(String metroArea,
                                                                                                               String truckType,
                                                                                                               String storeId,
                                                                                                               LocalDate reserveDate);

    @Query("SELECT * FROM truck_count_by_metro_area_truck_type_store_date WHERE metro_area = ?0 AND truck_type = ?1 AND store_id = ?2 AND reserve_date >= ?3 AND reserve_date <= ?4")
    Collection<TruckCountByMetroAreaTruckTypeStoreDate> findAllByMetroAreaAndTruckTypeAndStoreIdAndDateRange(String metroArea,
                                                                                                             String truckType,
                                                                                                             String storeId,
                                                                                                             LocalDate reserveStartDate,
                                                                                                             LocalDate reserveEndDate);

}
