package io.pivotal.pal.data.rentaltruck.reservation;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table("catalog_items")
public class CatalogItem {

    @PrimaryKey
    private String id;

    @Column("truck_type")
    private String truckType;

    public CatalogItem() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTruckType() {
        return truckType;
    }

    public void setTruckType(String truckType) {
        this.truckType = truckType;
    }

    @Override
    public String toString() {
        return "CatalogItem{" +
                "id='" + id + '\'' +
                ", truckType='" + truckType + '\'' +
                '}';
    }
}
