package io.pivotal.pal.data.rentaltruck.reservation.trucktype.query;

import org.springframework.data.repository.Repository;

import java.util.Collection;

public interface TruckTypeQueryRepository extends Repository<TruckType, String> {

    Collection<TruckType> findAll();

    Collection<TruckTypeProjection> findAllProjectedBy();

}
