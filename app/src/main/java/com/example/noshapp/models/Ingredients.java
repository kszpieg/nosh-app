package com.example.noshapp.models;

public class Ingredients {

    private String name;
    private float count;
    private String unit;
    private int idMeal;

    public Ingredients(String ingredientName, float ingredientCount, String ingredientUnit, int ingredientIdMeal) {
        name = ingredientName;
        count = ingredientCount;
        unit = ingredientUnit;
        idMeal = ingredientIdMeal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCount() {
        return count;
    }

    public void setCount(float count) {
        this.count = count;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(int idMeal) {
        this.idMeal = idMeal;
    }
}
