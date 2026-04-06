package com.aoizora.api.dto;

import com.aoizora.dao.domain.DrinkId;
import jakarta.validation.constraints.NotNull;

public record DrinkRequest(@NotNull Integer petId, @NotNull DrinkId drinkId) {};
