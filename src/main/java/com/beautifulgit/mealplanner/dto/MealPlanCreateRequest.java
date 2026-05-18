package com.beautifulgit.mealplanner.dto;

import com.beautifulgit.mealplanner.model.MealType;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record MealPlanCreateRequest(
        @NotNull LocalDate date,
        @NotNull MealType mealType,
        @NotNull Long recipeId,
        String note
) {
}
