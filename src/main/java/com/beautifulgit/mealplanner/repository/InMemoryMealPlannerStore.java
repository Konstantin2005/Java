package com.beautifulgit.mealplanner.repository;

import com.beautifulgit.mealplanner.model.MealPlanEntry;
import com.beautifulgit.mealplanner.model.Recipe;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.time.LocalDate;

@Component
public class InMemoryMealPlannerStore {
    private final Map<Long, Recipe> recipes = new HashMap<>();
    private final Map<Long, MealPlanEntry> mealPlans = new HashMap<>();

    private long recipeSequence = 1L;
    private long mealPlanSequence = 1L;

    public synchronized Recipe saveRecipe(Recipe recipe) {
        Recipe stored = new Recipe(recipeSequence++, recipe.name(), recipe.description(), recipe.calories(), recipe.ingredients(), recipe.mealType());
        recipes.put(stored.id(), stored);
        return stored;
    }

    public synchronized Recipe updateRecipe(Long id, Recipe recipe) {
        Recipe stored = new Recipe(id, recipe.name(), recipe.description(), recipe.calories(), recipe.ingredients(), recipe.mealType());
        recipes.put(id, stored);
        return stored;
    }

    public synchronized void deleteRecipe(Long id) {
        recipes.remove(id);
        mealPlans.entrySet().removeIf(entry -> id.equals(entry.getValue().recipeId()));
    }

    public synchronized Optional<Recipe> findRecipe(Long id) {
        return Optional.ofNullable(recipes.get(id));
    }

    public synchronized List<Recipe> findAllRecipes() {
        return new ArrayList<>(recipes.values());
    }

    public synchronized List<Recipe> findRecipes(String query, com.beautifulgit.mealplanner.model.MealType mealType) {
        Predicate<Recipe> predicate = recipe -> true;
        if (query != null && !query.isBlank()) {
            String normalized = query.toLowerCase();
            predicate = predicate.and(recipe -> recipe.name().toLowerCase().contains(normalized)
                    || recipe.description().toLowerCase().contains(normalized));
        }
        if (mealType != null) {
            predicate = predicate.and(recipe -> recipe.mealType() == mealType);
        }
        return recipes.values().stream()
                .filter(predicate)
                .sorted((left, right) -> Long.compare(left.id(), right.id()))
                .toList();
    }

    public synchronized void clear() {
        recipes.clear();
        mealPlans.clear();
        recipeSequence = 1L;
        mealPlanSequence = 1L;
    }

    public synchronized MealPlanEntry saveMealPlan(MealPlanEntry entry) {
        MealPlanEntry stored = new MealPlanEntry(mealPlanSequence++, entry.date(), entry.mealType(), entry.recipeId(), entry.note());
        mealPlans.put(stored.id(), stored);
        return stored;
    }

    public synchronized List<MealPlanEntry> findAllMealPlans() {
        return new ArrayList<>(mealPlans.values());
    }

    public synchronized List<MealPlanEntry> findMealPlans(LocalDate from, LocalDate to) {
        return mealPlans.values().stream()
                .filter(entry -> from == null || !entry.date().isBefore(from))
                .filter(entry -> to == null || !entry.date().isAfter(to))
                .sorted((left, right) -> left.date().compareTo(right.date()))
                .toList();
    }
}
