package io.pivotal.pal.data.rentaltruck.reservation.command.dropofftruck;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DropoffTruckCommandController {

    @PostMapping("/rentals/dropoffs")
    public ResponseEntity<Void> dropoffTruck(@RequestBody DropoffTruckCommandDto commandDto) {

        // update the rental record
        // if different from reservation, recalculate trucks on hand

        return ResponseEntity.ok().build();
    }
}
