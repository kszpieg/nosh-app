package com.example.noshapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
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

    ArrayList<String> ingredients;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_to_do_list);

        lvIngredients = findViewById(R.id.lvIngredients);
        btnClearList = findViewById(R.id.btnClearList);

        viewData();
        setupListViewListener();
    }

    private void setupListViewListener() {
        lvIngredients.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ingredients.remove(position);
                updateIngredientsArray(ingredients);
                adapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    private void viewData() {
        ingredients = getIngredientsArray();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, ingredients);
        lvIngredients.setAdapter(adapter);
    }

    private ArrayList<String> getIngredientsArray() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = preferences.getString("ingredients_list", null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        return gson.fromJson(json,type);
    }

    private void updateIngredientsArray(ArrayList<String> array){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(array);
        editor.putString("ingredients_list", json);
        editor.apply();
    }
}
