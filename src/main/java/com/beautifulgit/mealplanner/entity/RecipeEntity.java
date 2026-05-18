package com.beautifulgit.mealplanner.entity;

import com.beautifulgit.mealplanner.model.MealType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "recipes")
public class RecipeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int calories;

    @Enumerated(EnumType.STRING)
    private MealType mealType;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "recipe_ingredients", joinColumns = @JoinColumn(name = "recipe_id"))
    private List<IngredientEmbeddable> ingredients = new ArrayList<>();

    protected RecipeEntity() {
    }

    public RecipeEntity(String name, String description, int calories, MealType mealType, List<IngredientEmbeddable> ingredients) {
        this.name = name;
        this.description = description;
        this.calories = calories;
        this.mealType = mealType;
        this.ingredients = ingredients;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public MealType getMealType() {
        return mealType;
    }

    public List<IngredientEmbeddable> getIngredients() {
        return ingredients;
    }

    public void update(String name, String description, int calories, MealType mealType, List<IngredientEmbeddable> ingredients) {
        this.name = name;
        this.description = description;
        this.calories = calories;
        this.mealType = mealType;
        this.ingredients = ingredients;
    }
}
