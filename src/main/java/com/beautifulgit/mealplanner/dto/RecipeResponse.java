package com.beautifulgit.mealplanner.dto;

import com.beautifulgit.mealplanner.model.MealType;

import java.util.List;

public record RecipeResponse(
        Long id,
        String name,
        String description,
        int calories,
        MealType mealType,
        List<IngredientDto> ingredients
) {
}
