package com.beautifulgit.mealplanner.repository;

import com.beautifulgit.mealplanner.entity.RecipeEntity;
import com.beautifulgit.mealplanner.model.MealType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<RecipeEntity, Long> {
    List<RecipeEntity> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);

    List<RecipeEntity> findByMealType(MealType mealType);

    List<RecipeEntity> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseAndMealType(
            String name, String description, MealType mealType);
}
