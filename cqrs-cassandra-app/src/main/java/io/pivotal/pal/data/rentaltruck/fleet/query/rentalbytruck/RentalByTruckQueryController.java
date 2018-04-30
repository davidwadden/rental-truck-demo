package io.pivotal.pal.data.rentaltruck.fleet.query.rentalbytruck;

import io.pivotal.pal.data.rentaltruck.fleet.entity.RentalByTruck;
import io.pivotal.pal.data.rentaltruck.fleet.repository.RentalByTruckRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class RentalByTruckQueryController {

    private final RentalByTruckRepository repository;

    public RentalByTruckQueryController(RentalByTruckRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/rentals-by-truck")
    public Collection<RentalByTruck> findRentalsByTruck(@RequestParam String truckId) {
        return repository.findAllByTruckId(truckId);
    }
}
