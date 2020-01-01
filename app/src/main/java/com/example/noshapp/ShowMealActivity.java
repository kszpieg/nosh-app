package com.example.noshapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noshapp.models.Ingredients;
import com.example.noshapp.models.Meal;

import java.util.ArrayList;

public class ShowMealActivity extends AppCompatActivity {

    private boolean ifMealWasChosen = false;
    private String mealNameIfChosen;
    private int mealIdIfChosen;

    TextView tvMealName;
    TextView tvMealIngredients;
    TextView tvMealPortions;
    TextView tvMealDescription;
    TextView tvMealDescriptionTitle;

    Button btnToDoList;

    ArrayList<Meal> mealsFromDb = new ArrayList<>();
    ArrayList<Ingredients> ingredientsFromDb = new ArrayList<>();
    DatabaseAccess db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_meal);

        tvMealName = findViewById(R.id.tvMealName);
        tvMealIngredients = findViewById(R.id.tvMealIngredients);
        tvMealPortions = findViewById(R.id.tvMealPortions);
        tvMealDescription = findViewById(R.id.tvMealDescription);
        tvMealDescriptionTitle = findViewById(R.id.tvMealDescriptionTitle);

        btnToDoList = findViewById(R.id.btnToDoList);

        Intent myCallerIntent = getIntent();
        Bundle bundle = myCallerIntent.getExtras();

        if (bundle == null) {
            if (ifMealWasChosen) {
                showMeal(mealNameIfChosen, mealIdIfChosen);
            } else {
                displayDefaultTexts();
            }

        } else {
            String mealNameFromList = bundle.getString("mealName");
            int mealIDFromList = bundle.getInt("mealID");
            mealNameIfChosen = mealNameFromList;
            mealIdIfChosen = mealIDFromList;
            ifMealWasChosen = true;
            showMeal(mealNameFromList, mealIDFromList);
        }
    }

    private void showMeal(String mealNameFromList, int mealIDFromList) {
        db = DatabaseAccess.getInstance(getApplicationContext());
        db.open();
        mealsFromDb = db.getMealsFromDb();
        ingredientsFromDb = db.getIngredientsForMeal(mealIDFromList);
        Meal targetMeal = null;
        for (Meal meal : mealsFromDb) {
            if (mealNameFromList.equals(meal.getName())) {
                targetMeal = meal;
            }
        }
        tvMealName.setText(targetMeal.getName());
        tvMealIngredients.setText(displayIngredients(ingredientsFromDb));
        tvMealPortions.setText(Integer.toString(targetMeal.getPortions()));
        tvMealDescription.setText(targetMeal.getDescription());
        tvMealDescriptionTitle.setText("Opis");
    }

    private String displayIngredients(ArrayList<Ingredients> ingredientsFromDb) {
        String name;
        String count;
        String unit;
        String ingredientsDescription = "";
        for (Ingredients ingredients : ingredientsFromDb) {
            name = ingredients.getName();
            count = Float.toString(ingredients.getCount());
            unit = ingredients.getUnit();
            ingredientsDescription += "• " + name + " - " + count + " " + unit + "\n";
        }
        return ingredientsDescription;
    }

    private void displayDefaultTexts() {
        tvMealName.setText("");
        tvMealIngredients.setText("");
        tvMealPortions.setText("");
        tvMealDescription.setText("");
        tvMealDescriptionTitle.setText("Wybierz danie");
    }
}
