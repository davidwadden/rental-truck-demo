package io.pivotal.pal.data.rentaltruck.event;

import java.util.Objects;

public class CreditCardFailedEvent {

    private final String confirmationNumber;
    private final String failureReason;

    public CreditCardFailedEvent(String confirmationNumber, String failureReason) {
        this.confirmationNumber = confirmationNumber;
        this.failureReason = failureReason;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public String getFailureReason() {
        return failureReason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCardFailedEvent that = (CreditCardFailedEvent) o;
        return Objects.equals(confirmationNumber, that.confirmationNumber) &&
                Objects.equals(failureReason, that.failureReason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(confirmationNumber, failureReason);
    }

    @Override
    public String toString() {
        return "CreditCardFailedEvent{" +
                "confirmationNumber='" + confirmationNumber + '\'' +
                ", failureReason='" + failureReason + '\'' +
                '}';
    }
}
