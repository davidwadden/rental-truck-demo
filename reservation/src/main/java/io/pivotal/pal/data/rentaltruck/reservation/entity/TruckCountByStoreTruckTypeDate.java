package io.pivotal.pal.data.rentaltruck.reservation.entity;

import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Objects;

import static com.datastax.driver.core.DataType.Name.COUNTER;

@Table("truck_count_by_store_truck_type_date")
public class TruckCountByStoreTruckTypeDate {

    @PrimaryKey
    private final TruckCountByStoreTruckTypeDateKey key;

    @Column("count_on_hand")
    @CassandraType(type = COUNTER)
    private Long countOnHand;

    public TruckCountByStoreTruckTypeDate(TruckCountByStoreTruckTypeDateKey key, Long countOnHand) {
        this.key = key;
        this.countOnHand = countOnHand;
    }

    private TruckCountByStoreTruckTypeDate() {
        this.key = null;
        this.countOnHand = null;
    }

    public TruckCountByStoreTruckTypeDateKey getKey() {
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
        TruckCountByStoreTruckTypeDate that = (TruckCountByStoreTruckTypeDate) o;
        return Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    @Override
    public String toString() {
        return "TruckCountByStoreTruckTypeDate{" +
                "key=" + key +
                ", countOnHand=" + countOnHand +
                '}';
    }
}
