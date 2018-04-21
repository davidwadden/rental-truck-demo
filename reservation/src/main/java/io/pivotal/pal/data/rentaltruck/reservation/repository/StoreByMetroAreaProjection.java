package io.pivotal.pal.data.rentaltruck.reservation.repository;

import org.springframework.beans.factory.annotation.Value;

public interface StoreByMetroAreaProjection {

    @Value("#{target.getKey().getStoreId()}")
    String getStoreId();

    String getHoursOfOperation();

    String getDescription();

}
