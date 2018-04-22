package io.pivotal.pal.data.rentaltruck.fleet.command.retiretruck;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RetireTruckCommandController {

    @PostMapping("/trucks/retirements")
    public ResponseEntity<Void> retireTruck(@RequestBody RetireTruckCommandDto commandDto) {
        return ResponseEntity.unprocessableEntity().build();
    }

}
