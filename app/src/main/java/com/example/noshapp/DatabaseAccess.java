package com.example.noshapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.noshapp.models.Ingredients;
import com.example.noshapp.models.Meal;

import java.util.ArrayList;

public class DatabaseAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess instance;
    private final String MEAL_TABLE = "MEALS";
    private final String INGREDIENTS_TABLE = "INGREDIENTS";

    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.db = openHelper.getWritableDatabase();
    }

    public void close() {
        if (db != null) {
            this.db.close();
        }
    }

    public Cursor viewMealListData() {
        open();
        String query = "Select * from " + MEAL_TABLE;
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

    public ArrayList<Meal> getMealsFromDb() {
        ArrayList<Meal> meals = new ArrayList<>();
        open();
        String query = "Select * from " + MEAL_TABLE;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("Name")) != null) {
                Meal meal = new Meal(c.getString(c.getColumnIndex("Name")), c.getInt(c.getColumnIndex("Portion")), c.getString(c.getColumnIndex("Description")));
                meals.add(meal);
            }
            c.moveToNext();
        }
        close();
        return meals;
    }

    public ArrayList<Ingredients> getIngredientsForMeal(int mealID) {
        ArrayList<Ingredients> ingredients = new ArrayList<>();
        open();
        String query = "Select * from " + INGREDIENTS_TABLE + " where Meal_ID = " + mealID;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("Name")) != null) {
                Ingredients ingredient = new Ingredients(c.getString(c.getColumnIndex("Name")), c.getFloat(c.getColumnIndex("Quantity")), c.getString(c.getColumnIndex("Unit")), c.getInt(c.getColumnIndex("Meal_ID")));
                ingredients.add(ingredient);
            }
            c.moveToNext();
        }
        close();
        return ingredients;
    }

    public ArrayList<Ingredients> getAllIngredients() {
        ArrayList<Ingredients> ingredients = new ArrayList<>();
        open();
        String query = "Select * from " + INGREDIENTS_TABLE;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("Name")) != null) {
                Ingredients ingredient = new Ingredients(c.getString(c.getColumnIndex("Name")), c.getFloat(c.getColumnIndex("Quantity")), c.getString(c.getColumnIndex("Unit")), c.getInt(c.getColumnIndex("Meal_ID")));
                ingredients.add(ingredient);
            }
            c.moveToNext();
        }
        close();
        return ingredients;
    }

    public boolean addNewMeal(int newMealID, Meal newMeal) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", newMealID);
        contentValues.put("Name", newMeal.getName());
        contentValues.put("Portion", newMeal.getPortions());
        contentValues.put("Description", newMeal.getDescription());

        long result = db.insert(MEAL_TABLE, null, contentValues);
        close();
        return result != -1;
    }

    public boolean addNewIngredient(Ingredients ingredients, int ingredientID) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", ingredientID);
        contentValues.put("Name", ingredients.getName());
        contentValues.put("Quantity", ingredients.getCount());
        contentValues.put("Unit", ingredients.getUnit());
        contentValues.put("Meal_ID", ingredients.getIdMeal());

        long result = db.insert(INGREDIENTS_TABLE, null, contentValues);
        close();
        return result != -1;
    }
}
