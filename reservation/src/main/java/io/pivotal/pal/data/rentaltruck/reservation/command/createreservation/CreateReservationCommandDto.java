package io.pivotal.pal.data.rentaltruck.reservation.command.createreservation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class CreateReservationCommandDto {

    private final String truckType;
    private final String metroArea;
    private final String pickupStoreId;
    private final String pickupDate;
    private final String dropoffStoreId;
    private final String dropoffDate;
    private final String customerName;
    private final String creditCardNumber;

    @JsonCreator
    public CreateReservationCommandDto(@JsonProperty("truckType") String truckType,
                                       @JsonProperty("metroArea") String metroArea,
                                       @JsonProperty("pickupStoreId") String pickupStoreId,
                                       @JsonProperty("pickupDate") String pickupDate,
                                       @JsonProperty("dropoffStoreId") String dropoffStoreId,
                                       @JsonProperty("dropoffDate") String dropoffDate,
                                       @JsonProperty("customerName") String customerName,
                                       @JsonProperty("creditCardNumber") String creditCardNumber) {
        this.metroArea = metroArea;
        this.pickupStoreId = pickupStoreId;
        this.pickupDate = pickupDate;
        this.dropoffStoreId = dropoffStoreId;
        this.dropoffDate = dropoffDate;
        this.truckType = truckType;
        this.customerName = customerName;
        this.creditCardNumber = creditCardNumber;
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

    public String getPickupDate() {
        return pickupDate;
    }

    public String getDropoffStoreId() {
        return dropoffStoreId;
    }

    public String getDropoffDate() {
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
        CreateReservationCommandDto that = (CreateReservationCommandDto) o;
        return Objects.equals(truckType, that.truckType) &&
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
        return Objects.hash(truckType, metroArea, pickupStoreId, pickupDate, dropoffStoreId, dropoffDate, customerName, creditCardNumber);
    }

    @Override
    public String toString() {
        return "CreateReservationCommandDto{" +
                "truckType='" + truckType + '\'' +
                ", metroArea='" + metroArea + '\'' +
                ", pickupStoreId='" + pickupStoreId + '\'' +
                ", pickupDate='" + pickupDate + '\'' +
                ", dropoffStoreId='" + dropoffStoreId + '\'' +
                ", dropoffDate='" + dropoffDate + '\'' +
                ", customerName='" + customerName + '\'' +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                '}';
    }
}
