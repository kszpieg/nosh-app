package com.example.noshapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ShoppingToDoListActivity extends AppCompatActivity {

    ListView lvIngredients;
    Button btnClearList;

    ArrayList<String> ingredientsList;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_to_do_list);

        lvIngredients = findViewById(R.id.lvIngredients);
        btnClearList = findViewById(R.id.btnClearList);

        viewData();
    }

    private void viewData() {
        ingredientsList = getIngredientsArray();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,ingredientsList);
        lvIngredients.setAdapter(adapter);
    }

    private ArrayList<String> getIngredientsArray() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = preferences.getString("ingredients_list", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json,type);
    }
}
