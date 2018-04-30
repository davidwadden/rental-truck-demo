package io.pivotal.pal.data.rentaltruck.reservation.entity;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Objects;

@Table("stores_by_metro_area")
public class StoreByMetroArea {

    @PrimaryKey
    private final StoreByMetroAreaKey key;

    @Column("hours_of_operation")
    private String hoursOfOperation;

    @Column("description")
    private String description;

    public StoreByMetroArea(StoreByMetroAreaKey key, String hoursOfOperation, String description) {
        this.key = key;
        this.hoursOfOperation = hoursOfOperation;
        this.description = description;
    }

    private StoreByMetroArea() {
        this.key = null;
        this.hoursOfOperation = null;
        this.description = null;
    }

    public StoreByMetroAreaKey getKey() {
        return key;
    }

    public String getHoursOfOperation() {
        return hoursOfOperation;
    }

    public void setHoursOfOperation(String hoursOfOperation) {
        this.hoursOfOperation = hoursOfOperation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreByMetroArea that = (StoreByMetroArea) o;
        return Objects.equals(key, that.key) &&
                Objects.equals(hoursOfOperation, that.hoursOfOperation) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, hoursOfOperation, description);
    }

    @Override
    public String toString() {
        return "StoreByMetroArea{" +
                "key=" + key +
                ", hoursOfOperation='" + hoursOfOperation + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
