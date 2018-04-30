package io.pivotal.pal.data.rentaltruck.fleet.query.truckbystore;

import io.pivotal.pal.data.rentaltruck.fleet.entity.TruckByStore;
import io.pivotal.pal.data.rentaltruck.fleet.repository.TruckByStoreRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class TruckByStoreQueryController {

    private final TruckByStoreRepository repository;

    public TruckByStoreQueryController(TruckByStoreRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/trucks")
    public Collection<TruckByStore> findTrucksByStore(@RequestParam String storeId) {
        return repository.findAllByStoreId(storeId);
    }
}
