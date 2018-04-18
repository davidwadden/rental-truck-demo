package io.pivotal.pal.data.rentaltruck.reservation.handler.reservationcreated;

import io.pivotal.pal.data.rentaltruck.framework.event.AsyncEventHandler;
import io.pivotal.pal.data.rentaltruck.reservation.event.ReservationCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ReservationCreatedEventHandler implements AsyncEventHandler<ReservationCreatedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(ReservationCreatedEventHandler.class);

    @Override
    public void onEvent(ReservationCreatedEvent data) {
        logger.info("reservationCreated event: {}:", data);
    }
}
