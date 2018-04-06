package io.pivotal.pal.data.rentaltruck.reservation.query.dropoffstore;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class StoreByLocationQueryController {

    private final StoreByLocationQueryRepository repository;

    public StoreByLocationQueryController(StoreByLocationQueryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/stores-by-location")
    public Collection<StoreByLocationProjection> listStoresByLocation(@RequestParam String city,
                                                                      @RequestParam String state) {
        return repository.findAllProjectedByLocation(city, state);
    }
}
