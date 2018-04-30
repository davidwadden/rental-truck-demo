package io.pivotal.pal.data.rentaltruck.reservation.entity;

import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.Objects;

import static org.springframework.cassandra.core.Ordering.ASCENDING;
import static org.springframework.cassandra.core.PrimaryKeyType.CLUSTERED;
import static org.springframework.cassandra.core.PrimaryKeyType.PARTITIONED;

@PrimaryKeyClass
public class StoreByMetroAreaKey implements Serializable {

    @PrimaryKeyColumn(name = "metro_area", ordinal = 0, type = PARTITIONED)
    private final String metroArea;

    @PrimaryKeyColumn(name = "store_id", ordinal = 1, type = CLUSTERED, ordering = ASCENDING)
    private final String storeId;

    public StoreByMetroAreaKey(String metroArea, String storeId) {
        this.metroArea = metroArea;
        this.storeId = storeId;
    }

    private StoreByMetroAreaKey() {
        this.metroArea = null;
        this.storeId = null;
    }

    public String getMetroArea() {
        return metroArea;
    }

    public String getStoreId() {
        return storeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreByMetroAreaKey that = (StoreByMetroAreaKey) o;
        return Objects.equals(metroArea, that.metroArea) &&
                Objects.equals(storeId, that.storeId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(metroArea, storeId);
    }

    @Override
    public String toString() {
        return "StoreByMetroAreaKey{" +
                "metroArea='" + metroArea + '\'' +
                ", storeId='" + storeId + '\'' +
                '}';
    }
}
