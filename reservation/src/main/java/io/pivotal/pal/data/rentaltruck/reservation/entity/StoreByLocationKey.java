package io.pivotal.pal.data.rentaltruck.reservation.entity;

import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

import java.util.Objects;

import static org.springframework.cassandra.core.Ordering.ASCENDING;
import static org.springframework.cassandra.core.PrimaryKeyType.CLUSTERED;
import static org.springframework.cassandra.core.PrimaryKeyType.PARTITIONED;

@PrimaryKeyClass
public class StoreByLocationKey {

    @PrimaryKeyColumn(name = "dropoff_city", ordinal = 0, type = PARTITIONED)
    private final String dropoffCity;

    @PrimaryKeyColumn(name = "dropoff_state", ordinal = 1, type = PARTITIONED)
    private final String dropoffState;

    @PrimaryKeyColumn(name = "store_id", ordinal = 2, type = CLUSTERED, ordering = ASCENDING)
    private final String storeId;

    public StoreByLocationKey(String dropoffCity, String dropoffState, String storeId) {
        this.dropoffCity = dropoffCity;
        this.dropoffState = dropoffState;
        this.storeId = storeId;
    }

    private StoreByLocationKey() {
        this.dropoffCity = null;
        this.dropoffState = null;
        this.storeId = null;
    }

    public String getDropoffCity() {
        return dropoffCity;
    }

    public String getDropoffState() {
        return dropoffState;
    }

    public String getStoreId() {
        return storeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreByLocationKey that = (StoreByLocationKey) o;
        return Objects.equals(dropoffCity, that.dropoffCity) &&
                Objects.equals(dropoffState, that.dropoffState) &&
                Objects.equals(storeId, that.storeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dropoffCity, dropoffState, storeId);
    }

    @Override
    public String toString() {
        return "StoreByLocationKey{" +
                "dropoffCity='" + dropoffCity + '\'' +
                ", dropoffState='" + dropoffState + '\'' +
                ", storeId='" + storeId + '\'' +
                '}';
    }
}
