package com.beautifulgit.mealplanner.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record IngredientDto(
        @NotBlank String name,
        @Min(0) double amount,
        @NotBlank String unit
) {
}
