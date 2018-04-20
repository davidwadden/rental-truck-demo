package io.pivotal.pal.data.rentaltruck.reservation.query.truckcount;

import io.pivotal.pal.data.rentaltruck.reservation.entity.TruckCountByStoreTruckTypeDate;
import io.pivotal.pal.data.rentaltruck.reservation.entity.TruckCountByStoreTruckTypeDateKey;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.Repository;

import java.time.LocalDate;
import java.util.Collection;

public interface TruckCountByStoreTruckTypeDateRepository
        extends Repository<TruckCountByStoreTruckTypeDate, TruckCountByStoreTruckTypeDateKey> {

    @Query("SELECT * FROM truck_count_by_store_truck_type_date WHERE store_id = ?0 AND truck_type = ?1 AND reserve_date = ?2")
    Collection<TruckCountByStoreTruckTypeDate> findAllByStoreAndTruckTypeAndDate(String storeId,
                                                                                 String truckType,
                                                                                 LocalDate reserveDate);

    @Query("SELECT * FROM truck_count_by_store_truck_type_date WHERE store_id = ?0 AND truck_type = ?1 AND reserve_date >= ?2 AND reserve_date <= ?3")
    Collection<TruckCountByStoreTruckTypeDate> findAllByStoreAndTruckTypeAndDateRange(String storeId,
                                                                                      String truckType,
                                                                                      LocalDate reserveStartDate,
                                                                                      LocalDate reserveEndDate);

}
