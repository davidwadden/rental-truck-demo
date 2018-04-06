package io.pivotal.pal.data.rentaltruck.reservation.query.pickupstore;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Objects;

@Table("stores_by_location_and_date_and_truck_type")
public class StoreByLocationAndDateAndTruckType {

    @PrimaryKey
    private final StoreByLocationAndDateAndTruckTypeKey key;

    @Column("address")
    private final String address;

    @Column("description")
    private final String description;

    public StoreByLocationAndDateAndTruckType(StoreByLocationAndDateAndTruckTypeKey key, String address, String description) {
        this.key = key;
        this.address = address;
        this.description = description;
    }

    private StoreByLocationAndDateAndTruckType() {
        this.key = null;
        this.address = null;
        this.description = null;
    }

    public StoreByLocationAndDateAndTruckTypeKey getKey() {
        return key;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreByLocationAndDateAndTruckType that = (StoreByLocationAndDateAndTruckType) o;
        return Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    @Override
    public String toString() {
        return "StoreByLocationAndDateAndTruckType{" +
                "key=" + key +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
