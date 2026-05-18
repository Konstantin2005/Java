package com.beautifulgit.mealplanner.repository;

import com.beautifulgit.mealplanner.model.MealPlanEntry;
import com.beautifulgit.mealplanner.model.Recipe;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public synchronized Optional<Recipe> findRecipe(Long id) {
        return Optional.ofNullable(recipes.get(id));
    }

    public synchronized List<Recipe> findAllRecipes() {
        return new ArrayList<>(recipes.values());
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
}
