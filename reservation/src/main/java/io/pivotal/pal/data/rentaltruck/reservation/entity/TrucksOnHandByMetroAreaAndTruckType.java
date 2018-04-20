package io.pivotal.pal.data.rentaltruck.reservation.entity;

import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Objects;

import static com.datastax.driver.core.DataType.Name.COUNTER;

@Table("trucks_on_hand_by_metro_area_and_truck_type")
public class TrucksOnHandByMetroAreaAndTruckType {

    @PrimaryKey
    private final TrucksOnHandByMetroAreaAndTruckTypeKey key;

    @Column("count_on_hand")
    @CassandraType(type = COUNTER)
    private Long countOnHand;

    public TrucksOnHandByMetroAreaAndTruckType(TrucksOnHandByMetroAreaAndTruckTypeKey key,
                                               Long countOnHand) {
        this.key = key;
        this.countOnHand = countOnHand;
    }

    private TrucksOnHandByMetroAreaAndTruckType() {
        this.key = null;
        this.countOnHand = null;
    }

    public TrucksOnHandByMetroAreaAndTruckTypeKey getKey() {
        return key;
    }

    public Long getCountOnHand() {
        return countOnHand;
    }

    public void setCountOnHand(Long countOnHand) {
        this.countOnHand = countOnHand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrucksOnHandByMetroAreaAndTruckType that = (TrucksOnHandByMetroAreaAndTruckType) o;
        return Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    @Override
    public String toString() {
        return "TrucksOnHandByMetroAreaAndTruckType{" +
                "key=" + key +
                ", countOnHand=" + countOnHand +
                '}';
    }
}
