package com.aoizora.dao.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "food")
public class Food implements Serializable {

    private static final long serialVersionUID = 8791181701061581183L;

    @Id
    @Enumerated(EnumType.STRING)
    private FoodId id;

    @Column(name = "refrigerator_id")
    private int refrigeratorLevel;

    private int refrigeratorOrder;

    private float hiddenObjectsGameDropRate;

    public Food() {
        super();
    }

    public Food(FoodId id, int refrigeratorLevel, int refrigeratorOrder,
                float hiddenObjectsGameDropRate) {
        super();
        this.id = id;
        this.refrigeratorLevel = refrigeratorLevel;
        this.refrigeratorOrder = refrigeratorOrder;
        this.hiddenObjectsGameDropRate = hiddenObjectsGameDropRate;
    }

    public FoodId getId() {
        return id;
    }

    public int getRefrigeratorLevel() {
        return refrigeratorLevel;
    }

    public int getRefrigeratorOrder() {
        return refrigeratorOrder;
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
        Food other = (Food) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        return "Food [id=" + id
                + ", refrigeratorLevel=" + refrigeratorLevel
                + ", refrigeratorOrder=" + refrigeratorOrder
                + ", hiddenObjectsGameDropRate="
                + hiddenObjectsGameDropRate
                + "]";
    }


}
