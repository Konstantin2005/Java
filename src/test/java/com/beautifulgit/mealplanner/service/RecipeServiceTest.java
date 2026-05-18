package com.beautifulgit.mealplanner.service;

import com.beautifulgit.mealplanner.dto.IngredientDto;
import com.beautifulgit.mealplanner.dto.RecipeCreateRequest;
import com.beautifulgit.mealplanner.model.MealType;
import com.beautifulgit.mealplanner.repository.InMemoryMealPlannerStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RecipeServiceTest {
    private RecipeService recipeService;

    @BeforeEach
    void setUp() {
        recipeService = new RecipeService(new InMemoryMealPlannerStore());
    }

    @Test
    void createsRecipeAndReturnsStoredData() {
        var response = recipeService.create(new RecipeCreateRequest(
                "Toast",
                "Simple breakfast",
                150,
                MealType.BREAKFAST,
                List.of(new IngredientDto("Bread", 2, "slices"))
        ));

        assertEquals("Toast", response.name());
        assertEquals(1L, response.id());
        assertEquals(1, response.ingredients().size());
    }
}
