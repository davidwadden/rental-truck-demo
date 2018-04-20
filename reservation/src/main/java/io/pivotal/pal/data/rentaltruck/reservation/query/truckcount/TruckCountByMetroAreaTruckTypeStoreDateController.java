package io.pivotal.pal.data.rentaltruck.reservation.query.truckcount;

import io.pivotal.pal.data.rentaltruck.reservation.entity.TruckCountByMetroAreaTruckTypeStoreDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collection;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@RestController
public class TruckCountByMetroAreaTruckTypeStoreDateController {

    private final TruckCountByMetroAreaTruckTypeStoreDateRepository repository;

    public TruckCountByMetroAreaTruckTypeStoreDateController(TruckCountByMetroAreaTruckTypeStoreDateRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/truck-count-by-metro-area-truck-type-store-date")
    public Collection<TruckCountByMetroAreaTruckTypeStoreDate> findAllByMetroAreaAndTruckTypeAndStoreIdAndDate(@RequestParam String metroArea,
                                                                                                               @RequestParam String truckType,
                                                                                                               @RequestParam String storeId,
                                                                                                               @RequestParam @DateTimeFormat(iso = DATE) LocalDate reserveDate) {

        return repository.findAllByMetroAreaAndTruckTypeAndStoreIdAndDate(metroArea, truckType, storeId, reserveDate);
    }

    @GetMapping("/truck-count-by-metro-area-truck-type-store-date-range")
    public Collection<TruckCountByMetroAreaTruckTypeStoreDate> findAllByMetroAreaAndTruckTypeAndStoreIdAndDateRange(@RequestParam String metroArea,
                                                                                                                    @RequestParam String truckType,
                                                                                                                    @RequestParam String storeId,
                                                                                                                    @RequestParam @DateTimeFormat(iso = DATE) LocalDate reserveStartDate,
                                                                                                                    @RequestParam @DateTimeFormat(iso = DATE) LocalDate reserveEndDate) {

        return repository.findAllByMetroAreaAndTruckTypeAndStoreIdAndDateRange(metroArea, truckType, storeId, reserveStartDate, reserveEndDate);
    }
}
