package io.pivotal.pal.data.rentaltruck.reservation.entity;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Objects;

@Table("truck_types")
public class TruckType {

    @PrimaryKey("truck_type")
    private final String truckType;

    @Column("truck_make")
    private String truckMake;

    @Column("truck_model")
    private String truckModel;

    public TruckType(String truckType, String truckMake, String truckModel) {
        this.truckType = truckType;
        this.truckMake = truckMake;
        this.truckModel = truckModel;
    }

    private TruckType() {
        this.truckType = null;
        this.truckMake = null;
        this.truckModel = null;
    }

    public String getTruckType() {
        return truckType;
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
        TruckType truckType1 = (TruckType) o;
        return Objects.equals(truckType, truckType1.truckType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(truckType);
    }

    @Override
    public String toString() {
        return "TruckType{" +
                "truckType='" + truckType + '\'' +
                '}';
    }
}
