package io.pivotal.pal.data.rentaltruck.reservation.trucktype.query;

import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import static org.springframework.cassandra.core.Ordering.ASCENDING;
import static org.springframework.cassandra.core.PrimaryKeyType.CLUSTERED;
import static org.springframework.cassandra.core.PrimaryKeyType.PARTITIONED;

@PrimaryKeyClass
public class TruckTypeByLocationAndDateKey implements Serializable {

    @PrimaryKeyColumn(name = "pickup_city", ordinal = 0, type = PARTITIONED)
    private final String pickupCity;

    @PrimaryKeyColumn(name = "pickup_state", ordinal = 1, type = PARTITIONED)
    private final String pickupState;

    @PrimaryKeyColumn(name = "pickup_date", ordinal = 2, type = PARTITIONED)
    private final LocalDate pickupDate;

    @PrimaryKeyColumn(name = "truck_type", ordinal = 3, type = CLUSTERED, ordering = ASCENDING)
    private final String truckType;

    public TruckTypeByLocationAndDateKey(String pickupCity, String pickupState, LocalDate pickupDate, String truckType) {
        this.pickupCity = pickupCity;
        this.pickupState = pickupState;
        this.pickupDate = pickupDate;
        this.truckType = truckType;
    }

    private TruckTypeByLocationAndDateKey() {
        this.pickupCity = null;
        this.pickupState = null;
        this.pickupDate = null;
        this.truckType = null;
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

    public String getTruckType() {
        return truckType;
    }

    @Override
    public String toString() {
        return "TruckTypeByLocationAndDateKey{" +
                "pickupCity='" + pickupCity + '\'' +
                ", pickupState='" + pickupState + '\'' +
                ", pickupDate=" + pickupDate +
                ", truckType='" + truckType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TruckTypeByLocationAndDateKey that = (TruckTypeByLocationAndDateKey) o;
        return Objects.equals(pickupCity, that.pickupCity) &&
                Objects.equals(pickupState, that.pickupState) &&
                Objects.equals(pickupDate, that.pickupDate) &&
                Objects.equals(truckType, that.truckType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pickupCity, pickupState, pickupDate, truckType);
    }
}
