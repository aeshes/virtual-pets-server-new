package com.aoizora.dao.domain;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Запись о достижении питомца.
 */
@Entity
@Table(name="pet_achievement")
public class PetAchievement {

    /**
     * Первичный ключ записи. Генерируемый.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pet_achievement_seq")
    @SequenceGenerator(name = "pet_achievement_seq", sequenceName = "pet_achievement_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "achievement_id")
    @Enumerated(EnumType.STRING)
    private AchievementId achievement;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pet pet;

    /**
     * Отображалось ли игроку сообщение о получении достижения.
     */
    private Boolean wasShown = false;

    /**
     * Для оптимистичной блокировки.
     */
    @Version
    private int version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public AchievementId getAchievement() {
        return achievement;
    }

    public void setAchievement(AchievementId achievement) {
        this.achievement = achievement;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Boolean getWasShown() {
        return wasShown;
    }

    public void setWasShown(Boolean wasShown) {
        this.wasShown = wasShown;
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
        PetAchievement other = (PetAchievement) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "PetAchievement [id=" + id + ", achievement=" + achievement
                + ", pet=" + pet + ", wasShown=" + wasShown + ", version="
                + version + "]";
    }
}
