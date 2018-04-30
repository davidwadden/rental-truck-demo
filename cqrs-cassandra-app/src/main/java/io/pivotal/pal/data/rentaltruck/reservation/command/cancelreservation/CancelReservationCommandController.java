package io.pivotal.pal.data.rentaltruck.reservation.command.cancelreservation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CancelReservationCommandController {

    @PostMapping("/reservations/cancellations")
    public ResponseEntity<Void> cancelReservation(@RequestBody CancelReservationCommandDto commandDto) {

        // call to service to emit events to:
        // - change status on reservation to canceled
        // - re-calculate trucks on hand accordingly

        return ResponseEntity.accepted()
                .build();
    }
}
