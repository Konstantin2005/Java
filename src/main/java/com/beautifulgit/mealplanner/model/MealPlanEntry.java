package com.beautifulgit.mealplanner.model;

import java.time.LocalDate;

public record MealPlanEntry(
        Long id,
        LocalDate date,
        MealType mealType,
        Long recipeId,
        String note
) {
}
