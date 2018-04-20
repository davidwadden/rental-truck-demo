package io.pivotal.pal.data.rentaltruck.reservation.query.truckcount;

import io.pivotal.pal.data.rentaltruck.reservation.entity.TruckCountByStoreTruckTypeDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collection;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@RestController
public class TruckCountByStoreTruckTypeDateController {

    private final TruckCountByStoreTruckTypeDateRepository repository;

    public TruckCountByStoreTruckTypeDateController(TruckCountByStoreTruckTypeDateRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/truck-count-by-store-truck-type-date")
    public Collection<TruckCountByStoreTruckTypeDate> findAllByStoreAndTruckTypeAndDate(@RequestParam String storeId,
                                                                                        @RequestParam String truckType,
                                                                                        @RequestParam @DateTimeFormat(iso = DATE) LocalDate reserveDate) {

        return repository.findAllByStoreAndTruckTypeAndDate(storeId, truckType, reserveDate);
    }

    @GetMapping("/truck-count-by-store-truck-type-date-range")
    public Collection<TruckCountByStoreTruckTypeDate> findAllByStoreAndTruckTypeAndDateRange(@RequestParam String storeId,
                                                                                             @RequestParam String truckType,
                                                                                             @RequestParam @DateTimeFormat(iso = DATE) LocalDate reserveStartDate,
                                                                                             @RequestParam @DateTimeFormat(iso = DATE) LocalDate reserveEndDate) {

        return repository.findAllByStoreAndTruckTypeAndDateRange(storeId, truckType, reserveStartDate, reserveEndDate);
    }
}
