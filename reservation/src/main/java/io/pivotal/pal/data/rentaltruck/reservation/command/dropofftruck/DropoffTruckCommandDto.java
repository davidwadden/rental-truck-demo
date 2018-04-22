package io.pivotal.pal.data.rentaltruck.reservation.command.dropofftruck;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class DropoffTruckCommandDto {

    private final String confirmationNumber;
    private final String truckId;
    private final String dropoffStoreId;
    private final LocalDate dropoffDate;
    private final Integer dropoffMileage;
    private final Set<String> damageNotes;

    public DropoffTruckCommandDto(String confirmationNumber,
                                  String truckId,
                                  String dropoffStoreId,
                                  LocalDate dropoffDate,
                                  Integer dropoffMileage,
                                  Set<String> damageNotes) {
        this.confirmationNumber = confirmationNumber;
        this.truckId = truckId;
        this.dropoffStoreId = dropoffStoreId;
        this.dropoffDate = dropoffDate;
        this.dropoffMileage = dropoffMileage;
        this.damageNotes = damageNotes;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public String getTruckId() {
        return truckId;
    }

    public String getDropoffStoreId() {
        return dropoffStoreId;
    }

    public LocalDate getDropoffDate() {
        return dropoffDate;
    }

    public Integer getDropoffMileage() {
        return dropoffMileage;
    }

    public Set<String> getDamageNotes() {
        return damageNotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DropoffTruckCommandDto that = (DropoffTruckCommandDto) o;
        return Objects.equals(confirmationNumber, that.confirmationNumber) &&
                Objects.equals(truckId, that.truckId) &&
                Objects.equals(dropoffStoreId, that.dropoffStoreId) &&
                Objects.equals(dropoffDate, that.dropoffDate) &&
                Objects.equals(dropoffMileage, that.dropoffMileage) &&
                Objects.equals(damageNotes, that.damageNotes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(confirmationNumber, truckId, dropoffStoreId, dropoffDate, dropoffMileage, damageNotes);
    }

    @Override
    public String toString() {
        return "DropoffTruckCommandDto{" +
                "confirmationNumber='" + confirmationNumber + '\'' +
                ", truckId='" + truckId + '\'' +
                ", dropoffStoreId='" + dropoffStoreId + '\'' +
                ", dropoffDate=" + dropoffDate +
                ", dropoffMileage=" + dropoffMileage +
                ", damageNotes=" + damageNotes +
                '}';
    }
}
