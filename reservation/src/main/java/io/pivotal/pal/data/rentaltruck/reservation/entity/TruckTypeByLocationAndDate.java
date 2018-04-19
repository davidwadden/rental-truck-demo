package io.pivotal.pal.data.rentaltruck.reservation.entity;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Objects;

@Table("truck_types_by_location_and_date")
public class TruckTypeByLocationAndDate {

    @PrimaryKey
    private final TruckTypeByLocationAndDateKey key;

    @Column("truck_make")
    private String truckMake;

    @Column("truck_model")
    private String truckModel;

    public TruckTypeByLocationAndDate(TruckTypeByLocationAndDateKey key, String truckMake, String truckModel) {
        this.key = key;
        this.truckMake = truckMake;
        this.truckModel = truckModel;
    }

    private TruckTypeByLocationAndDate() {
        this.key = null;
        this.truckMake = null;
        this.truckModel = null;
    }

    public TruckTypeByLocationAndDateKey getKey() {
        return key;
    }

    public String getTruckMake() {
        return truckMake;
    }

    public void setTruckMake(String truckMake) {
        this.truckMake = truckMake;
    }

    public String getTruckModel() {
        return truckModel;
    }

    public void setTruckModel(String truckModel) {
        this.truckModel = truckModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TruckTypeByLocationAndDate that = (TruckTypeByLocationAndDate) o;
        return Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    @Override
    public String toString() {
        return "TruckTypeByLocationAndDate{" +
                "key=" + key +
                ", truckMake='" + truckMake + '\'' +
                ", truckModel='" + truckModel + '\'' +
                '}';
    }
}
