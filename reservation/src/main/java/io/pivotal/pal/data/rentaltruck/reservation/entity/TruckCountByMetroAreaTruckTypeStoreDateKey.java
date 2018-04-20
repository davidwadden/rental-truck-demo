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
public class TruckCountByMetroAreaTruckTypeStoreDateKey implements Serializable {

    @PrimaryKeyColumn(name = "metro_area", ordinal = 0, type = PARTITIONED)
    private final String metroArea;

    @PrimaryKeyColumn(name = "truck_type", ordinal = 1, type = PARTITIONED)
    private final String truckType;

    @PrimaryKeyColumn(name = "store_id", ordinal = 2, type = CLUSTERED, ordering = ASCENDING)
    private final String storeId;

    @PrimaryKeyColumn(name = "reserve_date", ordinal = 3, type = CLUSTERED, ordering = ASCENDING)
    @CassandraType(type = DATE)
    private final LocalDate reserveDate;

    public TruckCountByMetroAreaTruckTypeStoreDateKey(String metroArea,
                                                      String truckType,
                                                      String storeId,
                                                      LocalDate reserveDate) {
        this.metroArea = metroArea;
        this.truckType = truckType;
        this.storeId = storeId;
        this.reserveDate = reserveDate;
    }

    private TruckCountByMetroAreaTruckTypeStoreDateKey() {
        this.metroArea = null;
        this.truckType = null;
        this.storeId = null;
        this.reserveDate = null;
    }

    public String getMetroArea() {
        return metroArea;
    }

    public String getTruckType() {
        return truckType;
    }

    public String getStoreId() {
        return storeId;
    }

    public LocalDate getReserveDate() {
        return reserveDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TruckCountByMetroAreaTruckTypeStoreDateKey that = (TruckCountByMetroAreaTruckTypeStoreDateKey) o;
        return Objects.equals(metroArea, that.metroArea) &&
                Objects.equals(truckType, that.truckType) &&
                Objects.equals(storeId, that.storeId) &&
                Objects.equals(reserveDate, that.reserveDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(metroArea, truckType, storeId, reserveDate);
    }

    @Override
    public String toString() {
        return "TruckCountByMetroAreaTruckTypeStoreDateKey{" +
                "metroArea='" + metroArea + '\'' +
                ", truckType='" + truckType + '\'' +
                ", storeId='" + storeId + '\'' +
                ", reserveDate=" + reserveDate +
                '}';
    }
}
