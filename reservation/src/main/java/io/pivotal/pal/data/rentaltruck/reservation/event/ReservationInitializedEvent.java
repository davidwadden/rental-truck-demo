package io.pivotal.pal.data.rentaltruck.reservation.event;

import java.time.LocalDate;
import java.util.Objects;

public class ReservationInitializedEvent {

    private final String confirmationNumber;
    private final String pickupStoreId;
    private final LocalDate pickupDate;
    private final String dropoffStoreId;
    private final LocalDate dropoffDate;
    private final String truckType;
    private final String customerName;
    private final String creditCardNumber;

    public ReservationInitializedEvent(String confirmationNumber,
                                       String pickupStoreId,
                                       LocalDate pickupDate,
                                       String dropoffStoreId,
                                       LocalDate dropoffDate,
                                       String truckType,
                                       String customerName,
                                       String creditCardNumber) {
        this.confirmationNumber = confirmationNumber;
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

    public String getTruckType() {
        return truckType;
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
        ReservationInitializedEvent that = (ReservationInitializedEvent) o;
        return Objects.equals(confirmationNumber, that.confirmationNumber) &&
                Objects.equals(pickupStoreId, that.pickupStoreId) &&
                Objects.equals(pickupDate, that.pickupDate) &&
                Objects.equals(dropoffStoreId, that.dropoffStoreId) &&
                Objects.equals(dropoffDate, that.dropoffDate) &&
                Objects.equals(truckType, that.truckType) &&
                Objects.equals(customerName, that.customerName) &&
                Objects.equals(creditCardNumber, that.creditCardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(confirmationNumber, pickupStoreId, pickupDate, dropoffStoreId, dropoffDate, truckType, customerName, creditCardNumber);
    }

    @Override
    public String toString() {
        return "ReservationInitializedEvent{" +
                "confirmationNumber='" + confirmationNumber + '\'' +
                ", pickupStoreId='" + pickupStoreId + '\'' +
                ", pickupDate='" + pickupDate + '\'' +
                ", dropoffStoreId='" + dropoffStoreId + '\'' +
                ", dropoffDate='" + dropoffDate + '\'' +
                ", truckType='" + truckType + '\'' +
                ", customerName='" + customerName + '\'' +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                '}';
    }
}
