package io.pivotal.pal.data.rentaltruck.reservation.query;

import org.springframework.data.repository.Repository;

public interface TruckTypeQueryRepository extends Repository<TruckType, String> {

    Iterable<TruckType> findAll();

}
