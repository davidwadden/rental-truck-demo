package io.pivotal.pal.data.rentaltruck.reservation.query.trucksonhand;

import io.pivotal.pal.data.rentaltruck.reservation.entity.TrucksOnHandByStoreAndTruckType;
import io.pivotal.pal.data.rentaltruck.reservation.entity.TrucksOnHandByStoreAndTruckTypeKey;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.Repository;

import java.time.LocalDate;
import java.util.Collection;

public interface TrucksOnHandByStoreAndTruckTypeRepository
        extends Repository<TrucksOnHandByStoreAndTruckType, TrucksOnHandByStoreAndTruckTypeKey> {

    @Query("SELECT * FROM trucks_on_hand_by_store_and_truck_type WHERE store_id = ?0 AND truck_type = ?1 AND reserve_date = ?2")
    Collection<TrucksOnHandByStoreAndTruckType> findAllByStoreAndTruckTypeAndDate(String storeId,
                                                                                  String truckType,
                                                                                  LocalDate reserveDate);

    @Query("SELECT * FROM trucks_on_hand_by_store_and_truck_type WHERE store_id = ?0 AND truck_type = ?1 AND reserve_date >= ?2 AND reserve_date <= ?3")
    Collection<TrucksOnHandByStoreAndTruckType> findAllByStoreAndTruckTypeAndDateRange(String storeId,
                                                                                       String truckType,
                                                                                       LocalDate reserveStartDate,
                                                                                       LocalDate reserveEndDate);

}
