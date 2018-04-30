package io.pivotal.pal.data.rentaltruck.event;

import java.time.LocalDate;
import java.util.Objects;

public class ReservationRequestedEvent {

    private final String confirmationNumber;
    private final String truckType;
    private final String metroArea;
    private final String pickupStoreId;
    private final LocalDate pickupDate;
    private final String dropoffStoreId;
    private final LocalDate dropoffDate;
    private final String customerName;
    private final String creditCardNumber;

    public ReservationRequestedEvent(String confirmationNumber,
                                     String truckType,
                                     String metroArea,
                                     String pickupStoreId,
                                     LocalDate pickupDate,
                                     String dropoffStoreId,
                                     LocalDate dropoffDate,
                                     String customerName,
                                     String creditCardNumber) {
        this.confirmationNumber = confirmationNumber;
        this.metroArea = metroArea;
        this.pickupStoreId = pickupStoreId;
        this.pickupDate = pickupDate;
        this.dropoffStoreId = dropoffStoreId;
        this.dropoffDate = dropoffDate;
        this.truckType = truckType;
        this.customerName = customerName;
        this.creditCardNumber = creditCardNumber;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public String getTruckType() {
        return truckType;
    }

    public String getMetroArea() {
        return metroArea;
    }

    public String getPickupStoreId() {
        return pickupStoreId;
    }

    public LocalDate getPickupDate() {
        return pickupDate;
    }

    public String getDropoffStoreId() {
        return dropoffStoreId;
    }

    public LocalDate getDropoffDate() {
        return dropoffDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationRequestedEvent that = (ReservationRequestedEvent) o;
        return Objects.equals(confirmationNumber, that.confirmationNumber) &&
                Objects.equals(truckType, that.truckType) &&
                Objects.equals(metroArea, that.metroArea) &&
                Objects.equals(pickupStoreId, that.pickupStoreId) &&
                Objects.equals(pickupDate, that.pickupDate) &&
                Objects.equals(dropoffStoreId, that.dropoffStoreId) &&
                Objects.equals(dropoffDate, that.dropoffDate) &&
                Objects.equals(customerName, that.customerName) &&
                Objects.equals(creditCardNumber, that.creditCardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(confirmationNumber,
                truckType,
                metroArea,
                pickupStoreId,
                pickupDate,
                dropoffStoreId,
                dropoffDate, customerName,
                creditCardNumber);
    }

    @Override
    public String toString() {
        return "ReservationRequestedEvent{" +
                "confirmationNumber='" + confirmationNumber + '\'' +
                ", truckType='" + truckType + '\'' +
                ", metroArea='" + metroArea + '\'' +
                ", pickupStoreId='" + pickupStoreId + '\'' +
                ", pickupDate=" + pickupDate +
                ", dropoffStoreId='" + dropoffStoreId + '\'' +
                ", dropoffDate=" + dropoffDate +
                ", customerName='" + customerName + '\'' +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                '}';
    }
}
