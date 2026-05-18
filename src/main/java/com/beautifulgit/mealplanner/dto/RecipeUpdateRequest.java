package com.beautifulgit.mealplanner.dto;

import com.beautifulgit.mealplanner.model.MealType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RecipeUpdateRequest(
        @NotBlank String name,
        @NotBlank String description,
        @Min(0) int calories,
        @NotNull MealType mealType,
        @Valid List<IngredientDto> ingredients
) {
}
