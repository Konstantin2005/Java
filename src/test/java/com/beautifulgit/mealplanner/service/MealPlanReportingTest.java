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

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MealPlanReportingTest {
    private MealPlanService mealPlanService;
    private RecipeService recipeService;

    @BeforeEach
    void setUp() {
        var store = new InMemoryMealPlannerStore();
        recipeService = new RecipeService(store);
        mealPlanService = new MealPlanService(store);
    }

    @Test
    void filtersByDateRangeAndExportsCsv() {
        var recipe = recipeService.create(new RecipeCreateRequest(
                "Porridge",
                "Simple breakfast",
                200,
                MealType.BREAKFAST,
                List.of(new IngredientDto("Oats", 50, "g"))
        ));
        mealPlanService.create(new MealPlanCreateRequest(LocalDate.of(2026, 5, 18), MealType.BREAKFAST, recipe.id(), "Day one"));
        mealPlanService.create(new MealPlanCreateRequest(LocalDate.of(2026, 5, 20), MealType.BREAKFAST, recipe.id(), "Day two"));

        var filtered = mealPlanService.findByDateRange(LocalDate.of(2026, 5, 19), LocalDate.of(2026, 5, 21));
        assertEquals(1, filtered.size());

        var csv = mealPlanService.exportCsv(LocalDate.of(2026, 5, 18), LocalDate.of(2026, 5, 18));
        assertTrue(csv.startsWith("id,date,mealType,recipeId,note"));
        assertTrue(csv.contains("2026-05-18"));
    }
}
