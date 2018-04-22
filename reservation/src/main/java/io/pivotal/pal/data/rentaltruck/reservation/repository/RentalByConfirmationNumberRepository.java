package io.pivotal.pal.data.rentaltruck.reservation.repository;

import io.pivotal.pal.data.rentaltruck.reservation.entity.RentalByConfirmationNumber;
import org.springframework.data.repository.CrudRepository;

public interface RentalByConfirmationNumberRepository extends CrudRepository<RentalByConfirmationNumber, String> {
}
