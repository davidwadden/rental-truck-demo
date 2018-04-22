package io.pivotal.pal.data.rentaltruck.fleet.entity;

import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import static com.datastax.driver.core.DataType.Name.SET;
import static com.datastax.driver.core.DataType.Name.TEXT;

@Table("trucks_by_store")
public class TruckByStore {

    @PrimaryKey
    private final TruckByStoreKey key;

    @Column("parked_since_date")
    private LocalDate parkedSinceDate;

    @Column("current_mileage")
    private Integer currentMileage;

    @Column("damage_notes")
    @CassandraType(type = SET, typeArguments = TEXT)
    private Set<String> damageNotes;

    public TruckByStore(TruckByStoreKey key, LocalDate parkedSinceDate, Integer currentMileage, Set<String> damageNotes) {
        this.key = key;
        this.parkedSinceDate = parkedSinceDate;
        this.currentMileage = currentMileage;
        this.damageNotes = damageNotes;
    }

    private TruckByStore() {
        this.key = null;
        this.parkedSinceDate = null;
        this.currentMileage = null;
        this.damageNotes = null;
    }

    public TruckByStoreKey getKey() {
        return key;
    }

    public LocalDate getParkedSinceDate() {
        return parkedSinceDate;
    }

    public void setParkedSinceDate(LocalDate parkedSinceDate) {
        this.parkedSinceDate = parkedSinceDate;
    }

    public Integer getCurrentMileage() {
        return currentMileage;
    }

    public void setCurrentMileage(Integer currentMileage) {
        this.currentMileage = currentMileage;
    }

    public Set<String> getDamageNotes() {
        return damageNotes;
    }

    public void setDamageNotes(Set<String> damageNotes) {
        this.damageNotes = damageNotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TruckByStore that = (TruckByStore) o;
        return Objects.equals(key, that.key) &&
                Objects.equals(parkedSinceDate, that.parkedSinceDate) &&
                Objects.equals(currentMileage, that.currentMileage) &&
                Objects.equals(damageNotes, that.damageNotes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, parkedSinceDate, currentMileage, damageNotes);
    }

    @Override
    public String toString() {
        return "TruckByStore{" +
                "key=" + key +
                ", parkedSinceDate=" + parkedSinceDate +
                ", currentMileage=" + currentMileage +
                ", damageNotes=" + damageNotes +
                '}';
    }
}
