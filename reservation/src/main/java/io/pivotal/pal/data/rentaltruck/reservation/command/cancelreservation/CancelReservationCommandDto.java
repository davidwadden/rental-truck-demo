package io.pivotal.pal.data.rentaltruck.reservation.command.cancelreservation;

import java.util.Objects;

public class CancelReservationCommandDto {

    private final String confirmationNumber;
    private final String cancellationNotes;

    public CancelReservationCommandDto(String confirmationNumber, String cancellationNotes) {
        this.confirmationNumber = confirmationNumber;
        this.cancellationNotes = cancellationNotes;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public String getCancellationNotes() {
        return cancellationNotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CancelReservationCommandDto that = (CancelReservationCommandDto) o;
        return Objects.equals(confirmationNumber, that.confirmationNumber) &&
                Objects.equals(cancellationNotes, that.cancellationNotes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(confirmationNumber, cancellationNotes);
    }

    @Override
    public String toString() {
        return "CancelReservationCommandDto{" +
                "confirmationNumber='" + confirmationNumber + '\'' +
                ", cancellationNotes='" + cancellationNotes + '\'' +
                '}';
    }
}
