package com.aoizora.api;

import com.aoizora.dao.domain.PetType;

import java.util.Objects;

public class PetDTO {

    private Integer petId;
    private String petName;
    private PetType petType;

    public PetDTO() {

    }

    public PetDTO(Integer petId, String petName, PetType petType) {
        this.petId = petId;
        this.petName = petName;
        this.petType = petType;
    }

    public Integer getPetId() {
        return petId;
    }

    public void setPetId(Integer petId) {
        this.petId = petId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PetDTO petDTO = (PetDTO) o;
        return Objects.equals(petId, petDTO.petId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(petId);
    }
}
