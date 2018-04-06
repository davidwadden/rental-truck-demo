package io.pivotal.pal.data.rentaltruck.reservation.trucktype.query;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class TruckTypeQueryController {

    private final TruckTypeQueryRepository repository;

    public TruckTypeQueryController(TruckTypeQueryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/truck-types")
    public Collection<TruckTypeProjection> listTruckTypes() {
        return repository.findAllProjectedBy();
    }

}
