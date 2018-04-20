package io.pivotal.pal.data.rentaltruck.reservation.query.trucksonhand;

import io.pivotal.pal.data.rentaltruck.reservation.entity.TrucksOnHandByMetroAreaAndTruckType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collection;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@RestController
public class TrucksOnHandByMetroAreaAndTruckTypeController {

    private final TrucksOnHandByMetroAreaAndTruckTypeRepository repository;

    public TrucksOnHandByMetroAreaAndTruckTypeController(TrucksOnHandByMetroAreaAndTruckTypeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/trucks-on-hand-by-metro-area-and-truck-type")
    public Collection<TrucksOnHandByMetroAreaAndTruckType> findAllByMetroAreaAndTruckType(@RequestParam String metroArea,
                                                                                          @RequestParam String truckType) {

        return repository.findAllByMetroAreaAndTruckType(metroArea, truckType);
    }

    @GetMapping("/trucks-on-hand-by-metro-area-and-truck-type-and-date")
    public Collection<TrucksOnHandByMetroAreaAndTruckType> findAllByMetroAreaAndTruckTypeAndDate(@RequestParam String metroArea,
                                                                                                 @RequestParam String truckType,
                                                                                                 @RequestParam @DateTimeFormat(iso = DATE) LocalDate reserveDate) {

        return repository.findAllByMetroAreaAndTruckTypeAndDate(metroArea, truckType, reserveDate);
    }

    @GetMapping("/trucks-on-hand-by-metro-area-and-truck-type-and-date-range")
    public Collection<TrucksOnHandByMetroAreaAndTruckType> findAllByMetroAreaAndTruckTypeAndDateRange(@RequestParam String metroArea,
                                                                                                      @RequestParam String truckType,
                                                                                                      @RequestParam @DateTimeFormat(iso = DATE) LocalDate reserveStartDate,
                                                                                                      @RequestParam @DateTimeFormat(iso = DATE) LocalDate reserveEndDate) {

        return repository.findAllByMetroAreaAndTruckTypeAndDateRange(metroArea, truckType, reserveStartDate, reserveEndDate);
    }
}
