package com.example.noshapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnMealList, btnMeal, btnShoppingToDoList, btnShowMap;
    TextView tvWelcome, tvAppName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMealList = findViewById(R.id.btnMealList);
        btnMeal = findViewById(R.id.btnMeal);
        btnShoppingToDoList = findViewById(R.id.btnShoppingToDoList);
        btnShowMap = findViewById(R.id.btnShowMap);

        tvAppName = findViewById(R.id.tvAppName);
        tvWelcome = findViewById(R.id.tvWelcome);

        btnMealList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), MealListActivity.class);
                startActivity(intent);
            }
        });

        btnMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), ShowMealActivity.class);
                startActivity(intent);
            }
        });

        btnShoppingToDoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), ShoppingToDoListActivity.class);
                startActivity(intent);
            }
        });

        btnShowMap.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tutaj wkleić kod do mapowej klasy
            }
        }));
    }
}
