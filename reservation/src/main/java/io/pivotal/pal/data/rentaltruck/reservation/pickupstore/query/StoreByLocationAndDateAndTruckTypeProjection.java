package io.pivotal.pal.data.rentaltruck.reservation.pickupstore.query;

import org.springframework.beans.factory.annotation.Value;

interface StoreByLocationAndDateAndTruckTypeProjection {

    @Value("#{target.getKey().getStoreId()}")
    String getStoreId();

    String getAddress();

    String getDescription();

}
