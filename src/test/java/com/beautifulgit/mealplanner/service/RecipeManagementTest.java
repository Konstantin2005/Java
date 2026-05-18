package com.beautifulgit.mealplanner.service;

import com.beautifulgit.mealplanner.dto.IngredientDto;
import com.beautifulgit.mealplanner.dto.RecipeCreateRequest;
import com.beautifulgit.mealplanner.dto.RecipeUpdateRequest;
import com.beautifulgit.mealplanner.model.MealType;
import com.beautifulgit.mealplanner.repository.InMemoryMealPlannerStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RecipeManagementTest {
    private RecipeService recipeService;

    @BeforeEach
    void setUp() {
        recipeService = new RecipeService(new InMemoryMealPlannerStore());
    }

    @Test
    void searchesRecipesByQueryAndMealType() {
        recipeService.create(new RecipeCreateRequest(
                "Banana oatmeal",
                "Breakfast with fruit",
                300,
                MealType.BREAKFAST,
                List.of(new IngredientDto("Oats", 50, "g"))
        ));
        recipeService.create(new RecipeCreateRequest(
                "Chicken salad",
                "Light lunch",
                250,
                MealType.LUNCH,
                List.of(new IngredientDto("Chicken", 100, "g"))
        ));

        var results = recipeService.search("banana", MealType.BREAKFAST);

        assertEquals(1, results.size());
        assertTrue(results.get(0).name().contains("Banana"));
    }

    @Test
    void updatesAndDeletesRecipe() {
        var created = recipeService.create(new RecipeCreateRequest(
                "Soup",
                "Dinner",
                150,
                MealType.DINNER,
                List.of(new IngredientDto("Water", 200, "ml"))
        ));

        var updated = recipeService.update(created.id(), new RecipeUpdateRequest(
                "Soup deluxe",
                "Dinner with herbs",
                180,
                MealType.DINNER,
                List.of(new IngredientDto("Water", 250, "ml"))
        ));

        assertEquals("Soup deluxe", updated.name());

        recipeService.delete(created.id());

        assertEquals(0, recipeService.findAll().size());
    }
}
