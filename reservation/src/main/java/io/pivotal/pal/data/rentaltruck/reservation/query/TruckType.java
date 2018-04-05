package io.pivotal.pal.data.rentaltruck.reservation.query;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Objects;

@Table("truck_types")
public class TruckType {

    @PrimaryKey("truck_type")
    private final String truckType;

    public TruckType(String truckType) {
        this.truckType = truckType;
    }

    public String getTruckType() {
        return truckType;
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
