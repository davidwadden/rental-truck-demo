package io.pivotal.pal.data.rentaltruck.reservation.event;

import java.util.Objects;

public class CreditCardVerifiedEvent {

    private final String confirmationNumber;

    public CreditCardVerifiedEvent(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCardVerifiedEvent that = (CreditCardVerifiedEvent) o;
        return Objects.equals(confirmationNumber, that.confirmationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(confirmationNumber);
    }

    @Override
    public String toString() {
        return "CreditCardVerifiedEvent{" +
                "confirmationNumber='" + confirmationNumber + '\'' +
                '}';
    }
}
