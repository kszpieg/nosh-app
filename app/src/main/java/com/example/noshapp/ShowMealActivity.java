package com.example.noshapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noshapp.models.Meal;

import java.util.ArrayList;

public class ShowMealActivity extends AppCompatActivity {

    TextView tvMealName;
    TextView tvMealIngredients;
    TextView tvMealPortions;
    TextView tvMealDescription;
    TextView tvMealDescriptionTitle;

    ArrayList<Meal> mealsFromDb = new ArrayList<>();
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

        Intent myCallerIntent = getIntent();
        Bundle bundle = myCallerIntent.getExtras();

        if (bundle == null) {
            displayDefaultTexts();
        } else {
            String mealNameFromList = bundle.getString("mealName");
            showMeal(mealNameFromList);
        }
    }

    private void showMeal(String mealNameFromList) {
        db = DatabaseAccess.getInstance(getApplicationContext());
        db.open();
        mealsFromDb = db.getMealsFromDb();
        String targetMealName = mealNameFromList;
        Meal targetMeal = null;
        for (Meal meal : mealsFromDb)
        {
            if(targetMealName.equals(meal.getName())){
                targetMeal = meal;
            }
        }
        tvMealName.setText(targetMeal.getName());
        tvMealIngredients.setText("");
        tvMealPortions.setText(targetMeal.getPortions());
        tvMealDescription.setText(targetMeal.getDescription());
        tvMealDescriptionTitle.setText("Opis");
    }

    private void displayDefaultTexts() {
        tvMealName.setText("");
        tvMealIngredients.setText("");
        tvMealPortions.setText("");
        tvMealDescription.setText("");
        tvMealDescriptionTitle.setText("Wybierz danie");
    }
}
