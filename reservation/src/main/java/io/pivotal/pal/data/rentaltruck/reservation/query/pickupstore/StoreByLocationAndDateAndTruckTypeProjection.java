package io.pivotal.pal.data.rentaltruck.reservation.query.pickupstore;

import org.springframework.beans.factory.annotation.Value;

interface StoreByLocationAndDateAndTruckTypeProjection {

    @Value("#{target.getKey().getStoreId()}")
    String getStoreId();

    String getAddress();

    String getDescription();

}
