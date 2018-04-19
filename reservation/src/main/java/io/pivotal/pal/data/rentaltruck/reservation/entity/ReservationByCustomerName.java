package io.pivotal.pal.data.rentaltruck.reservation.entity;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.time.LocalDate;
import java.util.Objects;

/**
 * TODO: Can likely be refactored into materialized view of reservation_by_confirmation_number
 * <br/>
 * This needs to be done via CQL directly, for now defining a separate table using annotation config.
 */
@Table("reservations_by_customer_name")
public class ReservationByCustomerName {

    @PrimaryKey
    private final ReservationByCustomerNameKey key;

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

    @Column("credit_card_number")
    private String creditCardNumber;

    public ReservationByCustomerName(ReservationByCustomerNameKey key,
                                     String status,
                                     String pickupStoreId,
                                     LocalDate pickupDate,
                                     String dropoffStoreId,
                                     LocalDate dropoffDate,
                                     String truckType,
                                     String creditCardNumber) {
        this.key = key;
        this.status = status;
        this.pickupStoreId = pickupStoreId;
        this.pickupDate = pickupDate;
        this.dropoffStoreId = dropoffStoreId;
        this.dropoffDate = dropoffDate;
        this.truckType = truckType;
        this.creditCardNumber = creditCardNumber;
    }

    private ReservationByCustomerName() {
        this.key = null;
        this.status = null;
        this.pickupStoreId = null;
        this.pickupDate = null;
        this.dropoffStoreId = null;
        this.dropoffDate = null;
        this.truckType = null;
    }

    public ReservationByCustomerNameKey getKey() {
        return key;
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
        ReservationByCustomerName that = (ReservationByCustomerName) o;
        return Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    @Override
    public String toString() {
        return "ReservationByCustomerName{" +
                "key=" + key +
                ", status='" + status + '\'' +
                ", pickupStoreId='" + pickupStoreId + '\'' +
                ", pickupDate=" + pickupDate +
                ", dropoffStoreId='" + dropoffStoreId + '\'' +
                ", dropoffDate=" + dropoffDate +
                ", truckType='" + truckType + '\'' +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                '}';
    }
}
