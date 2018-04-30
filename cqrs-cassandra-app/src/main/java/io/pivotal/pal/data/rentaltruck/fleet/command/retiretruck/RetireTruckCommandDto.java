package io.pivotal.pal.data.rentaltruck.fleet.command.retiretruck;

import java.time.LocalDate;
import java.util.Objects;

public class RetireTruckCommandDto {

    private final String truckId;
    private final LocalDate retireDate;
    private final Integer retireMileage;

    public RetireTruckCommandDto(String truckId, LocalDate retireDate, Integer retireMileage) {
        this.truckId = truckId;
        this.retireDate = retireDate;
        this.retireMileage = retireMileage;
    }

    public String getTruckId() {
        return truckId;
    }

    public LocalDate getRetireDate() {
        return retireDate;
    }

    public Integer getRetireMileage() {
        return retireMileage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RetireTruckCommandDto that = (RetireTruckCommandDto) o;
        return Objects.equals(truckId, that.truckId) &&
                Objects.equals(retireDate, that.retireDate) &&
                Objects.equals(retireMileage, that.retireMileage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(truckId, retireDate, retireMileage);
    }

    @Override
    public String toString() {
        return "RetireTruckCommandDto{" +
                "truckId='" + truckId + '\'' +
                ", retireDate=" + retireDate +
                ", retireMileage=" + retireMileage +
                '}';
    }
}
