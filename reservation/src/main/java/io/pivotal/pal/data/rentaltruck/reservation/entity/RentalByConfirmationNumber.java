package io.pivotal.pal.data.rentaltruck.reservation.entity;

import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import static com.datastax.driver.core.DataType.Name.SET;
import static com.datastax.driver.core.DataType.Name.TEXT;

@Table("rentals_by_confirmation_number")
public class RentalByConfirmationNumber {

    @PrimaryKey("confirmation_number")
    private final String confirmationNumber;

    @Column("status")
    private String status;

    @Column("truck_type")
    private String truckType;

    @Column("truck_id")
    private String truckId;

    @Column("customer_name")
    private String customerName;

    @Column("metro_area")
    private String metroArea;

    @Column("pickup_store_id")
    private String pickupStoreId;

    @Column("pickup_date")
    private LocalDate pickupDate;

    @Column("pickup_mileage")
    private Integer pickupMileage;

    @Column("reserve_dropoff_store_id")
    private String reserveDropoffStoreId;

    @Column("reserve_dropoff_date")
    private LocalDate reserveDropoffDate;

    @Column("dropoff_store_id")
    private String dropoffStoreId;

    @Column("dropoff_date")
    private LocalDate dropoffDate;

    @Column("dropoff_mileage")
    private Integer dropoffMileage;

    @Column("transaction_id")
    private String transactionId;

    @Column("pickup_damage_notes")
    private Set<String> pickupDamageNotes;

    @Column("dropoff_damage_notes")
    @CassandraType(type = SET, typeArguments = TEXT)
    private Set<String> dropoffDamageNotes;

    public RentalByConfirmationNumber(String confirmationNumber,
                                      String status,
                                      String truckType,
                                      String truckId,
                                      String customerName,
                                      String metroArea,
                                      String pickupStoreId,
                                      LocalDate pickupDate,
                                      Integer pickupMileage,
                                      String reserveDropoffStoreId,
                                      LocalDate reserveDropoffDate,
                                      String dropoffStoreId,
                                      LocalDate dropoffDate,
                                      Integer dropoffMileage,
                                      String transactionId,
                                      Set<String> pickupDamageNotes,
                                      Set<String> dropoffDamageNotes) {
        this.confirmationNumber = confirmationNumber;
        this.status = status;
        this.truckType = truckType;
        this.truckId = truckId;
        this.customerName = customerName;
        this.metroArea = metroArea;
        this.pickupStoreId = pickupStoreId;
        this.pickupDate = pickupDate;
        this.pickupMileage = pickupMileage;
        this.reserveDropoffStoreId = reserveDropoffStoreId;
        this.reserveDropoffDate = reserveDropoffDate;
        this.dropoffStoreId = dropoffStoreId;
        this.dropoffDate = dropoffDate;
        this.dropoffMileage = dropoffMileage;
        this.transactionId = transactionId;
        this.pickupDamageNotes = pickupDamageNotes;
        this.dropoffDamageNotes = dropoffDamageNotes;
    }

    private RentalByConfirmationNumber() {
        this.confirmationNumber = null;
        this.status = null;
        this.truckType = null;
        this.truckId = null;
        this.customerName = null;
        this.metroArea = null;
        this.pickupStoreId = null;
        this.pickupDate = null;
        this.pickupMileage = null;
        this.reserveDropoffStoreId = null;
        this.reserveDropoffDate = null;
        this.dropoffStoreId = null;
        this.dropoffDate = null;
        this.dropoffMileage = null;
        this.transactionId = null;
        this.pickupDamageNotes = null;
        this.dropoffDamageNotes = null;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTruckType() {
        return truckType;
    }

    public void setTruckType(String truckType) {
        this.truckType = truckType;
    }

    public String getTruckId() {
        return truckId;
    }

    public void setTruckId(String truckId) {
        this.truckId = truckId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMetroArea() {
        return metroArea;
    }

    public void setMetroArea(String metroArea) {
        this.metroArea = metroArea;
    }

    public String getPickupStoreId() {
        return pickupStoreId;
    }

    public void setPickupStoreId(String pickupStoreId) {
        this.pickupStoreId = pickupStoreId;
    }

    public LocalDate getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(LocalDate pickupDate) {
        this.pickupDate = pickupDate;
    }

    public Integer getPickupMileage() {
        return pickupMileage;
    }

    public void setPickupMileage(Integer pickupMileage) {
        this.pickupMileage = pickupMileage;
    }

    public String getReserveDropoffStoreId() {
        return reserveDropoffStoreId;
    }

    public void setReserveDropoffStoreId(String reserveDropoffStoreId) {
        this.reserveDropoffStoreId = reserveDropoffStoreId;
    }

    public LocalDate getReserveDropoffDate() {
        return reserveDropoffDate;
    }

    public void setReserveDropoffDate(LocalDate reserveDropoffDate) {
        this.reserveDropoffDate = reserveDropoffDate;
    }

    public String getDropoffStoreId() {
        return dropoffStoreId;
    }

    public void setDropoffStoreId(String dropoffStoreId) {
        this.dropoffStoreId = dropoffStoreId;
    }

    public LocalDate getDropoffDate() {
        return dropoffDate;
    }

    public void setDropoffDate(LocalDate dropoffDate) {
        this.dropoffDate = dropoffDate;
    }

    public Integer getDropoffMileage() {
        return dropoffMileage;
    }

    public void setDropoffMileage(Integer dropoffMileage) {
        this.dropoffMileage = dropoffMileage;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Set<String> getPickupDamageNotes() {
        return pickupDamageNotes;
    }

    public void setPickupDamageNotes(Set<String> pickupDamageNotes) {
        this.pickupDamageNotes = pickupDamageNotes;
    }

    public Set<String> getDropoffDamageNotes() {
        return dropoffDamageNotes;
    }

    public void setDropoffDamageNotes(Set<String> dropoffDamageNotes) {
        this.dropoffDamageNotes = dropoffDamageNotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentalByConfirmationNumber that = (RentalByConfirmationNumber) o;
        return Objects.equals(confirmationNumber, that.confirmationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(confirmationNumber);
    }

    @Override
    public String toString() {
        return "RentalByConfirmationNumber{" +
                "confirmationNumber='" + confirmationNumber + '\'' +
                ", status='" + status + '\'' +
                ", truckType='" + truckType + '\'' +
                ", truckId='" + truckId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", metroArea='" + metroArea + '\'' +
                ", pickupStoreId='" + pickupStoreId + '\'' +
                ", pickupDate=" + pickupDate +
                ", pickupMileage=" + pickupMileage +
                ", reserveDropoffStoreId='" + reserveDropoffStoreId + '\'' +
                ", reserveDropoffDate=" + reserveDropoffDate +
                ", dropoffStoreId='" + dropoffStoreId + '\'' +
                ", dropoffDate=" + dropoffDate +
                ", dropoffMileage=" + dropoffMileage +
                ", transactionId='" + transactionId + '\'' +
                ", pickupDamageNotes=" + pickupDamageNotes +
                ", dropoffDamageNotes=" + dropoffDamageNotes +
                '}';
    }
}
