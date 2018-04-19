package io.pivotal.pal.data.rentaltruck.reservation.command.createreservation;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ConfirmationNumberFactory {

    private final AtomicInteger atomicInteger = new AtomicInteger();

    public String make() {
        Integer nextNumber = atomicInteger.incrementAndGet();
        return nextNumber.toString();
    }

}
