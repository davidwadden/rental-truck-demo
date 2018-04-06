package io.pivotal.pal.data.rentaltruck.reservation.dropoffstore.query;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.io.Serializable;
import java.util.Objects;

@Table("stores_by_location")
public class StoreByLocation implements Serializable {

    @PrimaryKey
    private final StoreByLocationKey key;

    @Column
    private final String address;

    @Column
    private final String description;

    public StoreByLocation(StoreByLocationKey key, String address, String description) {
        this.key = key;
        this.address = address;
        this.description = description;
    }

    private StoreByLocation() {
        this.key = null;
        this.address = null;
        this.description = null;
    }

    public StoreByLocationKey getKey() {
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
        StoreByLocation that = (StoreByLocation) o;
        return Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    @Override
    public String toString() {
        return "StoreByLocation{" +
                "key=" + key +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
