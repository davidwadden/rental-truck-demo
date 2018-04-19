package io.pivotal.pal.data.rentaltruck.reservation.entity;

import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import static org.springframework.cassandra.core.Ordering.ASCENDING;
import static org.springframework.cassandra.core.PrimaryKeyType.CLUSTERED;
import static org.springframework.cassandra.core.PrimaryKeyType.PARTITIONED;

@PrimaryKeyClass
public class StoreByLocationAndDateAndTruckTypeKey implements Serializable {

    @PrimaryKeyColumn(name = "pickup_city", ordinal = 0, type = PARTITIONED)
    private final String pickupCity;

    @PrimaryKeyColumn(name = "pickup_state", ordinal = 1, type = PARTITIONED)
    private final String pickupState;

    @PrimaryKeyColumn(name = "pickup_date", ordinal = 2, type = PARTITIONED)
    private final LocalDate pickupDate;

    @PrimaryKeyColumn(name = "truck_type", ordinal = 3, type = PARTITIONED)
    private final String truckType;

    @PrimaryKeyColumn(name = "store_id", ordinal = 4, type = CLUSTERED, ordering = ASCENDING)
    private final String storeId;

    public StoreByLocationAndDateAndTruckTypeKey(String pickupCity,
                                                 String pickupState,
                                                 LocalDate pickupDate,
                                                 String truckType,
                                                 String storeId) {
        this.pickupCity = pickupCity;
        this.pickupState = pickupState;
        this.pickupDate = pickupDate;
        this.truckType = truckType;
        this.storeId = storeId;
    }

    private StoreByLocationAndDateAndTruckTypeKey() {
        this.pickupCity = null;
        this.pickupState = null;
        this.pickupDate = null;
        this.truckType = null;
        this.storeId = null;
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

    public String getStoreId() {
        return storeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreByLocationAndDateAndTruckTypeKey that = (StoreByLocationAndDateAndTruckTypeKey) o;
        return Objects.equals(pickupCity, that.pickupCity) &&
                Objects.equals(pickupState, that.pickupState) &&
                Objects.equals(pickupDate, that.pickupDate) &&
                Objects.equals(truckType, that.truckType) &&
                Objects.equals(storeId, that.storeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pickupCity, pickupState, pickupDate, truckType, storeId);
    }

    @Override
    public String toString() {
        return "StoreByLocationAndDateAndTruckTypeKey{" +
                "pickupCity='" + pickupCity + '\'' +
                ", pickupState='" + pickupState + '\'' +
                ", pickupDate=" + pickupDate +
                ", truckType='" + truckType + '\'' +
                ", storeId='" + storeId + '\'' +
                '}';
    }
}
