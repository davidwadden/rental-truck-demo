package io.pivotal.pal.data.rentaltruck.reservation.entity;

import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Objects;

import static com.datastax.driver.core.DataType.Name.COUNTER;

@Table("count_by_metro_area_truck_type_store_and_date")
public class CountByMetroAreaTruckTypeStoreAndDate {

    @PrimaryKey
    private final CountByMetroAreaTruckTypeStoreAndDateKey key;

    @Column("count_on_hand")
    @CassandraType(type = COUNTER)
    private Integer countOnHand;

    public CountByMetroAreaTruckTypeStoreAndDate(CountByMetroAreaTruckTypeStoreAndDateKey key,
                                                 Integer countOnHand) {
        this.key = key;
        this.countOnHand = countOnHand;
    }

    private CountByMetroAreaTruckTypeStoreAndDate() {
        this.key = null;
        this.countOnHand = null;
    }

    public CountByMetroAreaTruckTypeStoreAndDateKey getKey() {
        return key;
    }

    public Integer getCountOnHand() {
        return countOnHand;
    }

    public void setCountOnHand(Integer countOnHand) {
        this.countOnHand = countOnHand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountByMetroAreaTruckTypeStoreAndDate that = (CountByMetroAreaTruckTypeStoreAndDate) o;
        return Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    @Override
    public String toString() {
        return "CountByMetroAreaTruckTypeStoreAndDate{" +
                "key=" + key +
                ", countOnHand=" + countOnHand +
                '}';
    }
}
