package io.pivotal.pal.data.rentaltruck.reservation.query;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController("/truck-types")
public class TruckTypeQueryController {

    private final TruckTypeQueryRepository repository;

    public TruckTypeQueryController(TruckTypeQueryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Collection<TruckTypeProjection> findAll() {
        return repository.findAllProjectedBy();
    }

}
