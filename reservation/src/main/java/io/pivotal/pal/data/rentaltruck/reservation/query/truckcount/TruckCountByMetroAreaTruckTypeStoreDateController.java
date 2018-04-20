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

    @GetMapping("/truck-count-by-metro-area-truck-type")
    public Collection<TruckCountByMetroAreaTruckTypeStoreDate> findAllByMetroAreaAndTruckType(@RequestParam String metroArea,
                                                                                              @RequestParam String truckType) {

        return repository.findAllByMetroAreaAndTruckType(metroArea, truckType);
    }

    @GetMapping("/truck-count-by-metro-area-truck-type-store-date")
    public Collection<TruckCountByMetroAreaTruckTypeStoreDate> findAllByMetroAreaAndTruckTypeAndDate(@RequestParam String metroArea,
                                                                                                     @RequestParam String truckType,
                                                                                                     @RequestParam @DateTimeFormat(iso = DATE) LocalDate reserveDate) {

        return repository.findAllByMetroAreaAndTruckTypeAndDate(metroArea, truckType, reserveDate);
    }

    @GetMapping("/truck-count-by-metro-area-truck-type-store-date-range")
    public Collection<TruckCountByMetroAreaTruckTypeStoreDate> findAllByMetroAreaAndTruckTypeAndDateRange(@RequestParam String metroArea,
                                                                                                          @RequestParam String truckType,
                                                                                                          @RequestParam @DateTimeFormat(iso = DATE) LocalDate reserveStartDate,
                                                                                                          @RequestParam @DateTimeFormat(iso = DATE) LocalDate reserveEndDate) {

        return repository.findAllByMetroAreaAndTruckTypeAndDateRange(metroArea, truckType, reserveStartDate, reserveEndDate);
    }
}
