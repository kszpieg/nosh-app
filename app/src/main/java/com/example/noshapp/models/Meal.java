package com.example.noshapp.models;

public class Meal {

    private String name;
    private int portions;
    private String description;

    public Meal(String mealName, int mealPortions, String mealDescription) {
        name = mealName;
        portions = mealPortions;
        description = mealDescription;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
