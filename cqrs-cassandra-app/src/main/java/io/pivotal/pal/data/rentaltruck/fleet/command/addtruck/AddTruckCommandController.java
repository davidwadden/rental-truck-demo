package io.pivotal.pal.data.rentaltruck.fleet.command.addtruck;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddTruckCommandController {

    @PostMapping("/trucks")
    public ResponseEntity<Void> addTruck(@RequestBody AddTruckCommandDto commandDto) {

        return ResponseEntity.unprocessableEntity().build();
    }
}
