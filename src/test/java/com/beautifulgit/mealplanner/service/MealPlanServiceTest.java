package com.beautifulgit.mealplanner.service;

import com.beautifulgit.mealplanner.dto.IngredientDto;
import com.beautifulgit.mealplanner.dto.MealPlanCreateRequest;
import com.beautifulgit.mealplanner.dto.RecipeCreateRequest;
import com.beautifulgit.mealplanner.model.MealType;
import com.beautifulgit.mealplanner.repository.InMemoryMealPlannerStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MealPlanServiceTest {
    private InMemoryMealPlannerStore store;
    private RecipeService recipeService;
    private MealPlanService mealPlanService;

    @BeforeEach
    void setUp() {
        store = new InMemoryMealPlannerStore();
        recipeService = new RecipeService(store);
        mealPlanService = new MealPlanService(store);
    }

    @Test
    void createsMealPlanForExistingRecipe() {
        var recipe = recipeService.create(new RecipeCreateRequest(
                "Salad",
                "Fresh lunch",
                220,
                MealType.LUNCH,
                List.of(new IngredientDto("Lettuce", 1, "head"))
        ));

        var response = mealPlanService.create(new MealPlanCreateRequest(
                LocalDate.of(2026, 5, 18),
                MealType.LUNCH,
                recipe.id(),
                "Meal prep"
        ));

        assertEquals(recipe.id(), response.recipeId());
        assertEquals(1L, response.id());
    }
}
