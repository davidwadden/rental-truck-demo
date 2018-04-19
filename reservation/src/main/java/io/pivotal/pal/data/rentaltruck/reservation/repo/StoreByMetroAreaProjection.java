package io.pivotal.pal.data.rentaltruck.reservation.repo;

import org.springframework.beans.factory.annotation.Value;

interface StoreByMetroAreaProjection {

    @Value("#{target.getKey().getStoreId()}")
    String getStoreId();

    String getHoursOfOperation();

    String getDescription();

}
