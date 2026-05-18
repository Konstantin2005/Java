package com.beautifulgit.mealplanner.service;

import com.beautifulgit.mealplanner.dto.IngredientDto;
import com.beautifulgit.mealplanner.dto.RecipeCreateRequest;
import com.beautifulgit.mealplanner.dto.RecipeResponse;
import com.beautifulgit.mealplanner.dto.RecipeUpdateRequest;
import com.beautifulgit.mealplanner.exception.NotFoundException;
import com.beautifulgit.mealplanner.model.Ingredient;
import com.beautifulgit.mealplanner.model.Recipe;
import com.beautifulgit.mealplanner.repository.InMemoryMealPlannerStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    private final InMemoryMealPlannerStore store;

    public RecipeService(InMemoryMealPlannerStore store) {
        this.store = store;
    }

    public RecipeResponse create(RecipeCreateRequest request) {
        Recipe saved = store.saveRecipe(new Recipe(
                null,
                request.name(),
                request.description(),
                request.calories(),
                request.ingredients().stream().map(this::toIngredient).toList(),
                request.mealType()
        ));
        return toResponse(saved);
    }

    public List<RecipeResponse> findAll() {
        return store.findAllRecipes().stream().map(this::toResponse).toList();
    }

    public List<RecipeResponse> search(String query, com.beautifulgit.mealplanner.model.MealType mealType) {
        return store.findRecipes(query, mealType).stream().map(this::toResponse).toList();
    }

    public RecipeResponse findById(Long id) {
        return store.findRecipe(id)
                .map(this::toResponse)
                .orElseThrow(() -> new NotFoundException("Recipe not found: " + id));
    }

    public RecipeResponse update(Long id, RecipeUpdateRequest request) {
        Recipe existing = store.findRecipe(id)
                .orElseThrow(() -> new NotFoundException("Recipe not found: " + id));
        Recipe updated = store.updateRecipe(id, new Recipe(
                existing.id(),
                request.name(),
                request.description(),
                request.calories(),
                request.ingredients().stream().map(this::toIngredient).toList(),
                request.mealType()
        ));
        return toResponse(updated);
    }

    public void delete(Long id) {
        store.findRecipe(id)
                .orElseThrow(() -> new NotFoundException("Recipe not found: " + id));
        store.deleteRecipe(id);
    }

    private Ingredient toIngredient(IngredientDto dto) {
        return new Ingredient(dto.name(), dto.amount(), dto.unit());
    }

    private RecipeResponse toResponse(Recipe recipe) {
        return new RecipeResponse(
                recipe.id(),
                recipe.name(),
                recipe.description(),
                recipe.calories(),
                recipe.mealType(),
                recipe.ingredients().stream()
                        .map(ingredient -> new IngredientDto(ingredient.name(), ingredient.amount(), ingredient.unit()))
                        .toList()
        );
    }
}
