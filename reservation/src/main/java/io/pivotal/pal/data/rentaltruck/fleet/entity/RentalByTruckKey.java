package io.pivotal.pal.data.rentaltruck.fleet.entity;

import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.Objects;

import static org.springframework.cassandra.core.Ordering.ASCENDING;
import static org.springframework.cassandra.core.PrimaryKeyType.CLUSTERED;
import static org.springframework.cassandra.core.PrimaryKeyType.PARTITIONED;

@PrimaryKeyClass
public class RentalByTruckKey implements Serializable {

    @PrimaryKeyColumn(name = "truck_id", ordinal = 0, type = PARTITIONED)
    private final String truckId;

    @PrimaryKeyColumn(name = "confirmation_number", ordinal = 1, type = CLUSTERED, ordering = ASCENDING)
    private final String confirmationNumber;

    public RentalByTruckKey(String truckId, String confirmationNumber) {
        this.truckId = truckId;
        this.confirmationNumber = confirmationNumber;
    }

    public String getTruckId() {
        return truckId;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalByTruckKey that = (RentalByTruckKey) o;
        return Objects.equals(truckId, that.truckId) &&
                Objects.equals(confirmationNumber, that.confirmationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(truckId, confirmationNumber);
    }

    @Override
    public String toString() {
        return "RentalByTruckKey{" +
                "truckId='" + truckId + '\'' +
                ", confirmationNumber='" + confirmationNumber + '\'' +
                '}';
    }
}
