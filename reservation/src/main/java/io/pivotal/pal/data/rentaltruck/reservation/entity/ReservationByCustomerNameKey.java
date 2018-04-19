package io.pivotal.pal.data.rentaltruck.reservation.entity;

import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.Objects;

import static org.springframework.cassandra.core.Ordering.ASCENDING;
import static org.springframework.cassandra.core.PrimaryKeyType.CLUSTERED;
import static org.springframework.cassandra.core.PrimaryKeyType.PARTITIONED;

@PrimaryKeyClass
public class ReservationByCustomerNameKey implements Serializable {

    @PrimaryKeyColumn(name = "customer_name", ordinal = 0, type = PARTITIONED)
    private final String customerName;

    @PrimaryKeyColumn(name = "confirmation_number", ordinal = 1, type = CLUSTERED, ordering = ASCENDING)
    private final String confirmationNumber;

    public ReservationByCustomerNameKey(String customerName, String confirmationNumber) {
        this.customerName = customerName;
        this.confirmationNumber = confirmationNumber;
    }

    private ReservationByCustomerNameKey() {
        this.customerName = null;
        this.confirmationNumber = null;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationByCustomerNameKey that = (ReservationByCustomerNameKey) o;
        return Objects.equals(customerName, that.customerName) &&
                Objects.equals(confirmationNumber, that.confirmationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerName, confirmationNumber);
    }

    @Override
    public String toString() {
        return "ReservationByCustomerNameKey{" +
                "customerName='" + customerName + '\'' +
                ", confirmationNumber='" + confirmationNumber + '\'' +
                '}';
    }
}
