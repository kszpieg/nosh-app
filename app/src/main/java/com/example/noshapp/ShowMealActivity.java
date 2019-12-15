package com.example.noshapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ShowMealActivity extends AppCompatActivity {

    TextView tvMealName;
    TextView tvMealIngredients;
    TextView tvMealPortions;
    TextView tvMealDescription;
    TextView tvMealDescriptionTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_meal);

        tvMealName = findViewById(R.id.tvMealName);
        tvMealIngredients = findViewById(R.id.tvMealIngredients);
        tvMealPortions = findViewById(R.id.tvMealPortions);
        tvMealDescription = findViewById(R.id.tvMealDescription);
        tvMealDescriptionTitle = findViewById(R.id.tvMealDescriptionTitle);


    }

    private void displayDefaultTexts() {
        tvMealName.setText("");
        tvMealIngredients.setText("");
        tvMealPortions.setText("");
        tvMealDescription.setText("");
        tvMealDescriptionTitle.setText("Wybierz danie");
    }
}
