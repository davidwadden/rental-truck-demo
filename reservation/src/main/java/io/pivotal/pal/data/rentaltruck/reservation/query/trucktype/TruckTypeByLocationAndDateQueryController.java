package io.pivotal.pal.data.rentaltruck.reservation.query.trucktype;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Collection;

@RestController
public class TruckTypeByLocationAndDateQueryController {

    private final TruckTypeByLocationAndDateQueryRepository repository;

    public TruckTypeByLocationAndDateQueryController(TruckTypeByLocationAndDateQueryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/truck-types-by-location-and-date")
    public Collection<TruckTypeByLocationAndDateProjection> listTruckTypesByLocationAndDate(@RequestParam String city,
                                                                                            @RequestParam String state,
                                                                                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate pickupDate) {

        return repository.findAllProjectedByKey(city, state, pickupDate);
    }
}
