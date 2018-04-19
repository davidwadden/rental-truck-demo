package io.pivotal.pal.data.rentaltruck.reservation.query.storebymetroarea;

import io.pivotal.pal.data.rentaltruck.reservation.repo.StoreByMetroAreaProjection;
import io.pivotal.pal.data.rentaltruck.reservation.repo.StoreByMetroAreaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class StoreByMetroAreaQueryController {

    private final StoreByMetroAreaRepository repository;

    public StoreByMetroAreaQueryController(StoreByMetroAreaRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/stores-by-metro-area")
    public Collection<StoreByMetroAreaProjection> listStoresByLocation(@RequestParam String metroArea) {
        return repository.findAllProjectedByLocation(metroArea);
    }
}
