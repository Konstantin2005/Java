package com.beautifulgit.mealplanner.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class IngredientEmbeddable {
    private String name;
    private double amount;
    private String unit;

    protected IngredientEmbeddable() {
    }

    public IngredientEmbeddable(String name, double amount, String unit) {
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }
}
