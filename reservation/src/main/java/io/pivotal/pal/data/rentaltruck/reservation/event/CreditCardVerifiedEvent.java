package io.pivotal.pal.data.rentaltruck.reservation.event;

public class CreditCardVerifiedEvent {

    private String confirmationNumber;

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }
}
