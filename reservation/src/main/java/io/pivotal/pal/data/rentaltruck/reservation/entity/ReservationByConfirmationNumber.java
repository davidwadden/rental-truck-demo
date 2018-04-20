package io.pivotal.pal.data.rentaltruck.reservation.entity;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.time.LocalDate;
import java.util.Objects;

@Table("reservations_by_confirmation_number")
public class ReservationByConfirmationNumber {

    @PrimaryKey("confirmation_number")
    private final String confirmationNumber;

    @Column("status")
    private String status;

    @Column("pickup_store_id")
    private String pickupStoreId;

    @Column("pickup_date")
    private LocalDate pickupDate;

    @Column("dropoff_store_id")
    private String dropoffStoreId;

    @Column("dropoff_date")
    private LocalDate dropoffDate;

    @Column("truck_type")
    private String truckType;

    @Column("customer_name")
    private String customerName;

    @Column("credit_card_number")
    private String creditCardNumber;
    private String metroArea;
    private LocalDate reserveStartDate;
    private LocalDate reserveEndDate;

    public ReservationByConfirmationNumber(String confirmationNumber,
                                           String status,
                                           String pickupStoreId,
                                           LocalDate pickupDate,
                                           String dropoffStoreId,
                                           LocalDate dropoffDate,
                                           String truckType,
                                           String customerName,
                                           String creditCardNumber) {
        this.confirmationNumber = confirmationNumber;
        this.status = status;
        this.pickupStoreId = pickupStoreId;
        this.pickupDate = pickupDate;
        this.dropoffStoreId = dropoffStoreId;
        this.dropoffDate = dropoffDate;
        this.truckType = truckType;
        this.customerName = customerName;
        this.creditCardNumber = creditCardNumber;
    }

    private ReservationByConfirmationNumber() {
        this.confirmationNumber = null;
        this.status = null;
        this.pickupStoreId = null;
        this.pickupDate = null;
        this.dropoffStoreId = null;
        this.dropoffDate = null;
        this.truckType = null;
        this.customerName = null;
        this.creditCardNumber = null;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPickupStoreId() {
        return pickupStoreId;
    }

    public void setPickupStoreId(String pickupStoreId) {
        this.pickupStoreId = pickupStoreId;
    }

    public LocalDate getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(LocalDate pickupDate) {
        this.pickupDate = pickupDate;
    }

    public String getDropoffStoreId() {
        return dropoffStoreId;
    }

    public void setDropoffStoreId(String dropoffStoreId) {
        this.dropoffStoreId = dropoffStoreId;
    }

    public LocalDate getDropoffDate() {
        return dropoffDate;
    }

    public void setDropoffDate(LocalDate dropoffDate) {
        this.dropoffDate = dropoffDate;
    }

    public String getTruckType() {
        return truckType;
    }

    public void setTruckType(String truckType) {
        this.truckType = truckType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationByConfirmationNumber that = (ReservationByConfirmationNumber) o;
        return Objects.equals(confirmationNumber, that.confirmationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(confirmationNumber);
    }

    @Override
    public String toString() {
        return "ReservationByConfirmationNumber{" +
                "confirmationNumber='" + confirmationNumber + '\'' +
                ", status='" + status + '\'' +
                ", pickupStoreId='" + pickupStoreId + '\'' +
                ", pickupDate=" + pickupDate +
                ", dropoffStoreId='" + dropoffStoreId + '\'' +
                ", dropoffDate=" + dropoffDate +
                ", truckType='" + truckType + '\'' +
                ", customerName='" + customerName + '\'' +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                '}';
    }

    public String getMetroArea() {
        return metroArea;
    }

    public void setMetroArea(String metroArea) {
        this.metroArea = metroArea;
    }

    public LocalDate getReserveStartDate() {
        return reserveStartDate;
    }

    public void setReserveStartDate(LocalDate reserveStartDate) {
        this.reserveStartDate = reserveStartDate;
    }

    public LocalDate getReserveEndDate() {
        return reserveEndDate;
    }

    public void setReserveEndDate(LocalDate reserveEndDate) {
        this.reserveEndDate = reserveEndDate;
    }
}
