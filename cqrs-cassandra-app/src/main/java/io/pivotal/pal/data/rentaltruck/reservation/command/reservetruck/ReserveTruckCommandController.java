package io.pivotal.pal.data.rentaltruck.reservation.command.reservetruck;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ReserveTruckCommandController {

    private final ReserveTruckCommandService service;

    public ReserveTruckCommandController(ReserveTruckCommandService service) {
        this.service = service;
    }

    @PostMapping("/reservations")
    public ResponseEntity<Void> reserveTruck(@RequestBody ReserveTruckCommandDto commandDto,
                                                  UriComponentsBuilder uriComponentsBuilder) {

        String confirmationNumber = service.reserveTruck(commandDto);

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("confirmationNumber", confirmationNumber);

        URI locationUri = uriComponentsBuilder
                .path("/reservations/{confirmationNumber}")
                .buildAndExpand(uriVariables)
                .toUri();

        return ResponseEntity.accepted()
                .location(locationUri)
                .build();
    }
}
