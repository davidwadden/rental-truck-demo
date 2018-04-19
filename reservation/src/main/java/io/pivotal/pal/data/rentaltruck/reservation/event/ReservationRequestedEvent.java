package io.pivotal.pal.data.rentaltruck.reservation.event;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ReservationRequestedEvent {

    private final String confirmationNumber;
    private final String pickupCity;
    private final String pickupState;
    private final String pickupDate;
    private final String dropoffCity;
    private final String dropoffState;
    private final String dropoffDate;
    private final String truckType;
    private final String customerName;

    public ReservationRequestedEvent(@JsonProperty("confirmationNumber") String confirmationNumber,
                                     @JsonProperty("pickupCity") String pickupCity,
                                     @JsonProperty("pickupState") String pickupState,
                                     @JsonProperty("pickupDate") String pickupDate,
                                     @JsonProperty("dropoffCity") String dropoffCity,
                                     @JsonProperty("dropoffState") String dropoffState,
                                     @JsonProperty("dropoffDate") String dropoffDate,
                                     @JsonProperty("truckType") String truckType,
                                     @JsonProperty("customerName") String customerName) {
        this.confirmationNumber = confirmationNumber;
        this.pickupCity = pickupCity;
        this.pickupState = pickupState;
        this.pickupDate = pickupDate;
        this.dropoffCity = dropoffCity;
        this.dropoffState = dropoffState;
        this.dropoffDate = dropoffDate;
        this.truckType = truckType;
        this.customerName = customerName;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public String getPickupCity() {
        return pickupCity;
    }

    public String getPickupState() {
        return pickupState;
    }

    public String getPickupDate() {
        return pickupDate;
    }

    public String getDropoffCity() {
        return dropoffCity;
    }

    public String getDropoffState() {
        return dropoffState;
    }

    public String getDropoffDate() {
        return dropoffDate;
    }

    public String getTruckType() {
        return truckType;
    }

    public String getCustomerName() {
        return customerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationRequestedEvent that = (ReservationRequestedEvent) o;
        return Objects.equals(confirmationNumber, that.confirmationNumber) &&
                Objects.equals(pickupCity, that.pickupCity) &&
                Objects.equals(pickupState, that.pickupState) &&
                Objects.equals(pickupDate, that.pickupDate) &&
                Objects.equals(dropoffCity, that.dropoffCity) &&
                Objects.equals(dropoffState, that.dropoffState) &&
                Objects.equals(dropoffDate, that.dropoffDate) &&
                Objects.equals(truckType, that.truckType) &&
                Objects.equals(customerName, that.customerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(confirmationNumber, pickupCity, pickupState, pickupDate, dropoffCity, dropoffState, dropoffDate, truckType, customerName);
    }

    @Override
    public String toString() {
        return "ReservationRequestedEvent{" +
                "confirmationNumber='" + confirmationNumber + '\'' +
                ", pickupCity='" + pickupCity + '\'' +
                ", pickupState='" + pickupState + '\'' +
                ", pickupDate='" + pickupDate + '\'' +
                ", dropoffCity='" + dropoffCity + '\'' +
                ", dropoffState='" + dropoffState + '\'' +
                ", dropoffDate='" + dropoffDate + '\'' +
                ", truckType='" + truckType + '\'' +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}
