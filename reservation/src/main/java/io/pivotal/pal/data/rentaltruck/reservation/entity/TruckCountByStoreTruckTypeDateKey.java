package io.pivotal.pal.data.rentaltruck.reservation.entity;

import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import static com.datastax.driver.core.DataType.Name.DATE;
import static org.springframework.cassandra.core.Ordering.ASCENDING;
import static org.springframework.cassandra.core.PrimaryKeyType.CLUSTERED;
import static org.springframework.cassandra.core.PrimaryKeyType.PARTITIONED;

@PrimaryKeyClass
public class TruckCountByStoreTruckTypeDateKey implements Serializable {

    @PrimaryKeyColumn(name = "store_id", ordinal = 0, type = PARTITIONED)
    private final String storeId;

    @PrimaryKeyColumn(name = "truck_type", ordinal = 1, type = PARTITIONED)
    private final String truckType;

    @PrimaryKeyColumn(name = "reserve_date", ordinal = 2, type = CLUSTERED, ordering = ASCENDING)
    @CassandraType(type = DATE)
    private final LocalDate reserveDate;

    public TruckCountByStoreTruckTypeDateKey(String storeId, String truckType, LocalDate reserveDate) {
        this.storeId = storeId;
        this.truckType = truckType;
        this.reserveDate = reserveDate;
    }

    private TruckCountByStoreTruckTypeDateKey() {
        this.storeId = null;
        this.truckType = null;
        this.reserveDate = null;
    }

    public String getStoreId() {
        return storeId;
    }

    public String getTruckType() {
        return truckType;
    }

    public LocalDate getReserveDate() {
        return reserveDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TruckCountByStoreTruckTypeDateKey that = (TruckCountByStoreTruckTypeDateKey) o;
        return Objects.equals(storeId, that.storeId) &&
                Objects.equals(truckType, that.truckType) &&
                Objects.equals(reserveDate, that.reserveDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeId, truckType, reserveDate);
    }

    @Override
    public String toString() {
        return "TruckCountByStoreTruckTypeDateKey{" +
                "storeId='" + storeId + '\'' +
                ", truckType='" + truckType + '\'' +
                ", reserveDate=" + reserveDate +
                '}';
    }
}
