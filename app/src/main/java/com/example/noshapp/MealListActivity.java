package com.example.noshapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MealListActivity extends AppCompatActivity {

    ListView lvMealList;
    Button btnAddMeal;

    String[] items = new String[3];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_list);

        lvMealList = findViewById(R.id.lvMealList);
        btnAddMeal = findViewById(R.id.btnAddMeal);

        items[0] = "Spaghetti bolognese";
        items[1] = "Hamburger";
        items[2] = "Pączki";

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items);
        lvMealList.setAdapter(itemsAdapter);

        btnAddMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (getApplicationContext(), AddMealActivity.class);
                startActivity(intent);
            }
        });
    }


}
