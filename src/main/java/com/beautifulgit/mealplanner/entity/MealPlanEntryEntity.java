package com.beautifulgit.mealplanner.entity;

import com.beautifulgit.mealplanner.model.MealType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "meal_plan_entries")
public class MealPlanEntryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private MealType mealType;

    private Long recipeId;
    private String note;

    protected MealPlanEntryEntity() {
    }

    public MealPlanEntryEntity(LocalDate date, MealType mealType, Long recipeId, String note) {
        this.date = date;
        this.mealType = mealType;
        this.recipeId = recipeId;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public MealType getMealType() {
        return mealType;
    }

    public Long getRecipeId() {
        return recipeId;
    }

    public String getNote() {
        return note;
    }
}
