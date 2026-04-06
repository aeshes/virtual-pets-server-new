package com.aoizora.api.dto;


import com.aoizora.dao.domain.PetType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreatePetRequest(
        @NotNull @Size(min = 3, max = 50) String name,
        @NotNull PetType petType,
        @Size(min = 1, max = 50) String comment) {
};