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

    public ReservationByCustomerName(ReservationByCustomerNameKey key,
                                     String status,
                                     String pickupCity,
                                     String pickupState,
                                     LocalDate pickupDate,
                                     String dropoffCity,
                                     String dropoffState,
                                     LocalDate dropoffDate,
                                     String truckType) {
        this.key = key;
        this.status = status;
        this.pickupCity = pickupCity;
        this.pickupState = pickupState;
        this.pickupDate = pickupDate;
        this.dropoffCity = dropoffCity;
        this.dropoffState = dropoffState;
        this.dropoffDate = dropoffDate;
        this.truckType = truckType;
    }

    private ReservationByCustomerName() {
        this.key = null;
        this.status = null;
        this.pickupCity = null;
        this.pickupState = null;
        this.pickupDate = null;
        this.dropoffCity = null;
        this.dropoffState = null;
        this.dropoffDate = null;
        this.truckType = null;
    }

    public ReservationByCustomerNameKey getKey() {
        return key;
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
                ", pickupCity='" + pickupCity + '\'' +
                ", pickupState='" + pickupState + '\'' +
                ", pickupDate=" + pickupDate +
                ", dropoffCity='" + dropoffCity + '\'' +
                ", dropoffState='" + dropoffState + '\'' +
                ", dropoffDate=" + dropoffDate +
                ", truckType='" + truckType + '\'' +
                '}';
    }
}
