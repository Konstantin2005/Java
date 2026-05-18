package com.beautifulgit.mealplanner.dto;

import com.beautifulgit.mealplanner.model.MealType;

import java.time.LocalDate;

public record MealPlanResponse(
        Long id,
        LocalDate date,
        MealType mealType,
        Long recipeId,
        String note
) {
}
