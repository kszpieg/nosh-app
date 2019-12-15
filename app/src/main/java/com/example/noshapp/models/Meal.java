package com.example.noshapp.models;

import java.util.List;

public class Meal {

    private String name;
    private List<Ingredients> ingredients;
    private int portions;
    private String description;

    public Meal(String mealName, List<Ingredients> mealIngredients, int mealPortions, String mealDescription) {
        name = mealName;
        ingredients = mealIngredients;
        portions = mealPortions;
        description = mealDescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public int getPortions() {
        return portions;
    }

    public void setPortions(int portions) {
        this.portions = portions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
