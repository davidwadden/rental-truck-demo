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
    private final String status;

    @Column("pickup_city")
    private final String pickupCity;

    @Column("pickup_state")
    private final String pickupState;

    @Column("pickup_date")
    private final LocalDate pickupDate;

    @Column("dropoff_city")
    private final String dropoffCity;

    @Column("dropoff_state")
    private final String dropoffState;

    @Column("dropoff_date")
    private final LocalDate dropoffDate;

    @Column("truck_type")
    private final String truckType;

    @Column("customer_name")
    private final String customerName;

    public ReservationByConfirmationNumber(String confirmationNumber,
                                           String status, String pickupCity,
                                           String pickupState,
                                           LocalDate pickupDate,
                                           String dropoffCity,
                                           String dropoffState,
                                           LocalDate dropoffDate,
                                           String truckType,
                                           String customerName) {
        this.confirmationNumber = confirmationNumber;
        this.status = status;
        this.pickupCity = pickupCity;
        this.pickupState = pickupState;
        this.pickupDate = pickupDate;
        this.dropoffCity = dropoffCity;
        this.dropoffState = dropoffState;
        this.dropoffDate = dropoffDate;
        this.truckType = truckType;
        this.customerName = customerName;
    }

    private ReservationByConfirmationNumber() {
        this.confirmationNumber = null;
        this.status = null;
        this.pickupCity = null;
        this.pickupState = null;
        this.pickupDate = null;
        this.dropoffCity = null;
        this.dropoffState = null;
        this.dropoffDate = null;
        this.truckType = null;
        this.customerName = null;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public String getStatus() {
        return status;
    }

    public String getPickupCity() {
        return pickupCity;
    }

    public String getPickupState() {
        return pickupState;
    }

    public LocalDate getPickupDate() {
        return pickupDate;
    }

    public String getDropoffCity() {
        return dropoffCity;
    }

    public String getDropoffState() {
        return dropoffState;
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
                ", pickupCity='" + pickupCity + '\'' +
                ", pickupState='" + pickupState + '\'' +
                ", pickupDate=" + pickupDate +
                ", dropoffCity='" + dropoffCity + '\'' +
                ", dropoffState='" + dropoffState + '\'' +
                ", dropoffDate=" + dropoffDate +
                ", truckType='" + truckType + '\'' +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}
