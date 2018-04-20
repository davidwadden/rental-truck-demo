package io.pivotal.pal.data.rentaltruck.reservation.query.trucksonhand;

import io.pivotal.pal.data.rentaltruck.reservation.entity.TrucksOnHandByStoreAndTruckType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collection;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@RestController
public class TrucksOnHandByStoreAndTruckTypeController {

    private final TrucksOnHandByStoreAndTruckTypeRepository repository;

    public TrucksOnHandByStoreAndTruckTypeController(TrucksOnHandByStoreAndTruckTypeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/trucks-on-hand-by-store-and-truck-type-and-date")
    public Collection<TrucksOnHandByStoreAndTruckType> findAllByStoreAndTruckTypeAndDate(@RequestParam String storeId,
                                                                                         @RequestParam String truckType,
                                                                                         @RequestParam @DateTimeFormat(iso = DATE) LocalDate reserveDate) {

        return repository.findAllByStoreAndTruckTypeAndDate(storeId, truckType, reserveDate);
    }

    @GetMapping("/trucks-on-hand-by-store-and-truck-type-and-date-range")
    public Collection<TrucksOnHandByStoreAndTruckType> findAllByStoreAndTruckTypeAndDateRange(@RequestParam String storeId,
                                                                                              @RequestParam String truckType,
                                                                                              @RequestParam @DateTimeFormat(iso = DATE) LocalDate reserveStartDate,
                                                                                              @RequestParam @DateTimeFormat(iso = DATE) LocalDate reserveEndDate) {

        return repository.findAllByStoreAndTruckTypeAndDateRange(storeId, truckType, reserveStartDate, reserveEndDate);
    }
}
