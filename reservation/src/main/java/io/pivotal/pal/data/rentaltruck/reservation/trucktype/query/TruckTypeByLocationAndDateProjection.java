package io.pivotal.pal.data.rentaltruck.reservation.trucktype.query;

import org.springframework.beans.factory.annotation.Value;

interface TruckTypeByLocationAndDateProjection {

    @Value("#{target.getKey().getTruckType()}")
    String getTruckType();

    String getTruckMake();

    String getTruckModel();

}
