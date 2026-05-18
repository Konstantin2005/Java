package com.beautifulgit.mealplanner.model;

import java.util.List;

public record Recipe(
        Long id,
        String name,
        String description,
        int calories,
        List<Ingredient> ingredients,
        MealType mealType
) {
}
