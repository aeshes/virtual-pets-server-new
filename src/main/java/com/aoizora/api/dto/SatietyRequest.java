package com.aoizora.api.dto;

import com.aoizora.dao.domain.FoodId;
import jakarta.validation.constraints.NotNull;

public record SatietyRequest(@NotNull Integer petId, @NotNull FoodId foodId) {
}
