package com.aoizora.dao.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "building_material")
public class BuildingMaterial implements Serializable {

    private static final long serialVersionUID = -6611026384958159106L;

    @Id
    @Enumerated(EnumType.STRING)
    private BuildingMaterialId id;

    private int rucksackOrder;

    private int newbieBoxDropMin;

    private int newbieBoxDropMax;

    private float newbieBoxDropRate;

    private float hiddenObjectsGameDropRate;

    public BuildingMaterial() {
        super();
    }

    public BuildingMaterial(BuildingMaterialId id) {
        super();
        this.id = id;
    }



    public BuildingMaterialId getId() {
        return id;
    }

    public int getRucksackOrder() {
        return rucksackOrder;
    }

    public int getNewbieBoxDropMin() {
        return newbieBoxDropMin;
    }

    public int getNewbieBoxDropMax() {
        return newbieBoxDropMax;
    }

    public float getNewbieBoxDropRate() {
        return newbieBoxDropRate;
    }

    public float getHiddenObjectsGameDropRate() {
        return hiddenObjectsGameDropRate;
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
        BuildingMaterial other = (BuildingMaterial) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        return "BuildingMaterial [id=" + id
                + ", rucksackOrder=" + rucksackOrder
                + ", newbieBoxDropMin=" + newbieBoxDropMin
                + ", newbieBoxDropMax=" + newbieBoxDropMax
                + ", newbieBoxDropRate=" + newbieBoxDropRate
                + ", hiddenObjectsGameDropRate="
                + hiddenObjectsGameDropRate
                + "]";
    }

}

