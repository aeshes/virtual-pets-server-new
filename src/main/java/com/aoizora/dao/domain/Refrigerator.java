package com.aoizora.dao.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "refrigerator")
public class Refrigerator implements Serializable {

    private static final long serialVersionUID = -6335332962524762996L;

    @Id
    private int id;

    private int experience;

    @OneToMany(mappedBy = "refrigerator",
            cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "building_material_id")
    private Map<BuildingMaterialId, RefrigeratorCost> refrigeratorCosts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public Map<BuildingMaterialId, RefrigeratorCost>
    getRefrigeratorCosts() {
        return refrigeratorCosts;
    }

    public void setRefrigeratorCosts(Map<BuildingMaterialId,
            RefrigeratorCost> refrigeratorCosts) {
        this.refrigeratorCosts = refrigeratorCosts;
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
        Refrigerator other = (Refrigerator) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "Refrigerator [id=" + id + "]";
    }
}
