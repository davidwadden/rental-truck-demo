package io.pivotal.pal.data.rentaltruck.fleet.command.addtruck;

import java.util.Objects;

public class AddTruckCommandDto {

    private final String truckId;
    private final String truckType;
    private final Integer mileage;

    public AddTruckCommandDto(String truckId, String truckType, Integer mileage) {
        this.truckId = truckId;
        this.truckType = truckType;
        this.mileage = mileage;
    }

    public String getTruckId() {
        return truckId;
    }

    public String getTruckType() {
        return truckType;
    }

    public Integer getMileage() {
        return mileage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddTruckCommandDto that = (AddTruckCommandDto) o;
        return Objects.equals(truckId, that.truckId) &&
                Objects.equals(truckType, that.truckType) &&
                Objects.equals(mileage, that.mileage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(truckId, truckType, mileage);
    }

    @Override
    public String toString() {
        return "AddTruckCommandDto{" +
                "truckId='" + truckId + '\'' +
                ", truckType='" + truckType + '\'' +
                ", mileage=" + mileage +
                '}';
    }
}
