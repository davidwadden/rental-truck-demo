package io.pivotal.pal.data.rentaltruck.reservation.query;

import org.springframework.beans.factory.annotation.Value;

interface TruckTypeByLocationAndDateProjection {

    @Value("#{target.getKey().getTruckType()}")
    String truckType();

}
