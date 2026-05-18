package com.beautifulgit.mealplanner.service;

import com.beautifulgit.mealplanner.dto.IngredientDto;
import com.beautifulgit.mealplanner.dto.RecipeCreateRequest;
import com.beautifulgit.mealplanner.dto.RecipeResponse;
import com.beautifulgit.mealplanner.dto.RecipeUpdateRequest;
import com.beautifulgit.mealplanner.entity.IngredientEmbeddable;
import com.beautifulgit.mealplanner.entity.RecipeEntity;
import com.beautifulgit.mealplanner.exception.NotFoundException;
import com.beautifulgit.mealplanner.model.Ingredient;
import com.beautifulgit.mealplanner.model.Recipe;
import com.beautifulgit.mealplanner.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {
    private final RecipeRepository repository;

    public RecipeService(RecipeRepository repository) {
        this.repository = repository;
    }

    public RecipeResponse create(RecipeCreateRequest request) {
        RecipeEntity saved = repository.save(new RecipeEntity(
                request.name(),
                request.description(),
                request.calories(),
                request.mealType(),
                request.ingredients().stream().map(this::toEmbeddable).toList()
        ));
        return toResponse(saved);
    }

    public List<RecipeResponse> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public List<RecipeResponse> search(String query, com.beautifulgit.mealplanner.model.MealType mealType) {
        List<RecipeEntity> items;
        if (query != null && !query.isBlank() && mealType != null) {
            items = repository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndMealType(query, query, mealType);
        } else if (query != null && !query.isBlank()) {
            items = repository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query);
        } else if (mealType != null) {
            items = repository.findByMealType(mealType);
        } else {
            items = repository.findAll();
        }
        return items.stream().map(this::toResponse).toList();
    }

    public RecipeResponse findById(Long id) {
        return repository.findById(id)
                .map(this::toResponse)
                .orElseThrow(() -> new NotFoundException("Recipe not found: " + id));
    }

    public RecipeResponse update(Long id, RecipeUpdateRequest request) {
        RecipeEntity existing = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Recipe not found: " + id));
        existing.update(
                request.name(),
                request.description(),
                request.calories(),
                request.mealType(),
                request.ingredients().stream().map(this::toEmbeddable).toList()
        );
        return toResponse(repository.save(existing));
    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Recipe not found: " + id));
        repository.deleteById(id);
    }

    private IngredientEmbeddable toEmbeddable(IngredientDto dto) {
        return new IngredientEmbeddable(dto.name(), dto.amount(), dto.unit());
    }

    private RecipeResponse toResponse(RecipeEntity recipe) {
        return new RecipeResponse(
                recipe.getId(),
                recipe.getName(),
                recipe.getDescription(),
                recipe.getCalories(),
                recipe.getMealType(),
                recipe.getIngredients().stream()
                        .map(ingredient -> new IngredientDto(ingredient.getName(), ingredient.getAmount(), ingredient.getUnit()))
                        .toList()
        );
    }
}
