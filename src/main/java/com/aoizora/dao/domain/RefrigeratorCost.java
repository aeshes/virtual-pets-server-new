package com.aoizora.dao.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="refrigerator_cost")
public class RefrigeratorCost {

    @Id
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Refrigerator refrigerator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_material_id")
    private BuildingMaterial buildingMaterial;

    private int cost;

    public RefrigeratorCost() {
        super();
    }

    public RefrigeratorCost(int id, Refrigerator refrigerator,
                            BuildingMaterial buildingMaterial, int cost) {
        super();
        this.id = id;
        this.refrigerator = refrigerator;
        this.buildingMaterial = buildingMaterial;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Refrigerator getRefrigerator() {
        return refrigerator;
    }

    public void setRefrigerator(Refrigerator refrigerator) {
        this.refrigerator = refrigerator;
    }

    public BuildingMaterial getBuildingMaterial() {
        return buildingMaterial;
    }

    public void setBuildingMaterial(BuildingMaterial buildingMaterial) {
        this.buildingMaterial = buildingMaterial;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RefrigeratorCost other = (RefrigeratorCost) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        return "RefrigeratorCost [id=" + id
                + ", refrigerator=" + refrigerator
                + ", buildingMaterial=" + buildingMaterial
                + ", cost=" + cost
                + "]";
    }
}

