package io.pivotal.pal.data.rentaltruck.reservation.dropoffstore.query;

import org.springframework.beans.factory.annotation.Value;

interface StoreByLocationProjection {

    @Value("#{target.getKey().getStoreId()}")
    String getStoreId();

    String getAddress();

    String getDescription();

}
