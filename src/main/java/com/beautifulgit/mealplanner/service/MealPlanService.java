package com.beautifulgit.mealplanner.service;

import com.beautifulgit.mealplanner.dto.MealPlanCreateRequest;
import com.beautifulgit.mealplanner.dto.MealPlanResponse;
import com.beautifulgit.mealplanner.exception.NotFoundException;
import com.beautifulgit.mealplanner.model.MealPlanEntry;
import com.beautifulgit.mealplanner.repository.InMemoryMealPlannerStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealPlanService {
    private final InMemoryMealPlannerStore store;

    public MealPlanService(InMemoryMealPlannerStore store) {
        this.store = store;
    }

    public MealPlanResponse create(MealPlanCreateRequest request) {
        store.findRecipe(request.recipeId())
                .orElseThrow(() -> new NotFoundException("Recipe not found: " + request.recipeId()));
        MealPlanEntry saved = store.saveMealPlan(new MealPlanEntry(
                null,
                request.date(),
                request.mealType(),
                request.recipeId(),
                request.note()
        ));
        return toResponse(saved);
    }

    public List<MealPlanResponse> findAll() {
        return store.findAllMealPlans().stream().map(this::toResponse).toList();
    }

    private MealPlanResponse toResponse(MealPlanEntry entry) {
        return new MealPlanResponse(entry.id(), entry.date(), entry.mealType(), entry.recipeId(), entry.note());
    }
}
