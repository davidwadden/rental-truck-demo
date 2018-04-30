package io.pivotal.pal.data.rentaltruck.reservation.command.pickuptruck;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class PickupTruckCommandController {

    @PostMapping("/rentals")
    public ResponseEntity<Void> pickupTruck(@RequestBody PickupTruckCommandDto commandDto) {

        // call back to service
        // emit events as to do the following:
        // - charge the credit card (suitable for posting)
        //   - change credit card check on reserveTruck to authorization
        // - convert a reservation into a rental
        // - if we assume rental matches reservation, trucks on hand doesn't change
        // - what if renter asks to change dropoff date or location?

        URI locationUri = URI.create("/rentals/stubbed-rental-id");
        return ResponseEntity
                .created(locationUri)
                .build();
    }
}
