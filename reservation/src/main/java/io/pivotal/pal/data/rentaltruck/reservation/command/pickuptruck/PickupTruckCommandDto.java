package io.pivotal.pal.data.rentaltruck.reservation.command.pickuptruck;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

public class PickupTruckCommandDto {

    private final String truckType;
    private final String metroArea;
    private final String pickupStoreId;
    private final LocalDate pickupDate;
    private final String reserveDropoffStoreId;
    private final LocalDate reserveDropoffDate;
    private final String truckId;
    private final Integer pickupMileage;
    private final Set<String> damageNotes;

    public PickupTruckCommandDto(String truckType,
                                 String metroArea,
                                 String pickupStoreId,
                                 LocalDate pickupDate,
                                 String reserveDropoffStoreId,
                                 LocalDate reserveDropoffDate,
                                 String truckId,
                                 Integer pickupMileage,
                                 Set<String> damageNotes) {
        this.truckType = truckType;
        this.metroArea = metroArea;
        this.pickupStoreId = pickupStoreId;
        this.pickupDate = pickupDate;
        this.reserveDropoffStoreId = reserveDropoffStoreId;
        this.reserveDropoffDate = reserveDropoffDate;
        this.truckId = truckId;
        this.pickupMileage = pickupMileage;
        this.damageNotes = damageNotes;
    }

    public String getTruckType() {
        return truckType;
    }

    public String getMetroArea() {
        return metroArea;
    }

    public String getPickupStoreId() {
        return pickupStoreId;
    }

    public LocalDate getPickupDate() {
        return pickupDate;
    }

    public String getReserveDropoffStoreId() {
        return reserveDropoffStoreId;
    }

    public LocalDate getReserveDropoffDate() {
        return reserveDropoffDate;
    }

    public String getTruckId() {
        return truckId;
    }

    public Integer getPickupMileage() {
        return pickupMileage;
    }

    public Set<String> getDamageNotes() {
        return damageNotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PickupTruckCommandDto that = (PickupTruckCommandDto) o;
        return Objects.equals(truckType, that.truckType) &&
                Objects.equals(metroArea, that.metroArea) &&
                Objects.equals(pickupStoreId, that.pickupStoreId) &&
                Objects.equals(pickupDate, that.pickupDate) &&
                Objects.equals(reserveDropoffStoreId, that.reserveDropoffStoreId) &&
                Objects.equals(reserveDropoffDate, that.reserveDropoffDate) &&
                Objects.equals(truckId, that.truckId) &&
                Objects.equals(pickupMileage, that.pickupMileage) &&
                Objects.equals(damageNotes, that.damageNotes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(truckType,
                metroArea,
                pickupStoreId,
                pickupDate,
                reserveDropoffStoreId,
                reserveDropoffDate,
                truckId,
                pickupMileage,
                damageNotes);
    }

    @Override
    public String toString() {
        return "PickupTruckCommandDto{" +
                "truckType='" + truckType + '\'' +
                ", metroArea='" + metroArea + '\'' +
                ", pickupStoreId='" + pickupStoreId + '\'' +
                ", pickupDate=" + pickupDate +
                ", reserveDropoffStoreId='" + reserveDropoffStoreId + '\'' +
                ", reserveDropoffDate=" + reserveDropoffDate +
                ", truckId='" + truckId + '\'' +
                ", pickupMileage=" + pickupMileage +
                ", damageNotes=" + damageNotes +
                '}';
    }
}
