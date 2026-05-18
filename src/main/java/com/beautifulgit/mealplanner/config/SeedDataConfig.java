package com.beautifulgit.mealplanner.config;

import com.beautifulgit.mealplanner.dto.IngredientDto;
import com.beautifulgit.mealplanner.dto.MealPlanCreateRequest;
import com.beautifulgit.mealplanner.dto.RecipeCreateRequest;
import com.beautifulgit.mealplanner.model.MealType;
import com.beautifulgit.mealplanner.service.MealPlanService;
import com.beautifulgit.mealplanner.service.RecipeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class SeedDataConfig {

    @Bean
    @Profile("!test")
    CommandLineRunner seedRecipes(RecipeService recipeService, MealPlanService mealPlanService) {
        return args -> {
            var oatmeal = recipeService.create(new RecipeCreateRequest(
                    "Oatmeal with banana",
                    "Simple breakfast with oats, milk and banana.",
                    320,
                    MealType.BREAKFAST,
                    List.of(
                            new IngredientDto("Oats", 80, "g"),
                            new IngredientDto("Milk", 200, "ml"),
                            new IngredientDto("Banana", 1, "pcs")
                    )
            ));

            mealPlanService.create(new MealPlanCreateRequest(
                    LocalDate.now(),
                    MealType.BREAKFAST,
                    oatmeal.id(),
                    "Start the day light"
            ));
        };
    }
}
