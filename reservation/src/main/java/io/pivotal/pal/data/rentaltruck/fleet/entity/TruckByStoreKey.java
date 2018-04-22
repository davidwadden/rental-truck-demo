package io.pivotal.pal.data.rentaltruck.fleet.entity;

import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.Objects;

import static org.springframework.cassandra.core.Ordering.ASCENDING;
import static org.springframework.cassandra.core.PrimaryKeyType.CLUSTERED;
import static org.springframework.cassandra.core.PrimaryKeyType.PARTITIONED;

@PrimaryKeyClass
public class TruckByStoreKey implements Serializable {

    @PrimaryKeyColumn(type = PARTITIONED, ordinal = 0, name = "store_id")
    private final String storeId;

    @PrimaryKeyColumn(type = CLUSTERED, ordinal = 1, name = "truck_id", ordering = ASCENDING)
    private final String truckId;

    public TruckByStoreKey(String storeId, String truckId) {
        this.storeId = storeId;
        this.truckId = truckId;
    }

    private TruckByStoreKey() {
        this.storeId = null;
        this.truckId = null;
    }

    public String getStoreId() {
        return storeId;
    }

    public String getTruckId() {
        return truckId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TruckByStoreKey that = (TruckByStoreKey) o;
        return Objects.equals(storeId, that.storeId) &&
                Objects.equals(truckId, that.truckId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeId, truckId);
    }

    @Override
    public String toString() {
        return "TruckByStoreKey{" +
                "storeId='" + storeId + '\'' +
                ", truckId='" + truckId + '\'' +
                '}';
    }
}
