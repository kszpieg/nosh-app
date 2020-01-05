package com.example.noshapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.noshapp.models.Ingredients;
import com.example.noshapp.models.Meal;

import java.util.ArrayList;

public class AddMealActivity extends AppCompatActivity {

    EditText etMealName;
    EditText etMealCount;
    EditText etMealDescription;
    Button btnAddMealToList;

    EditText etMealIngredientName, etMealIngredientCount, etMealIngredientUnit;
    Button btnAddMealIngredient;
    LinearLayout container;

    ArrayList<String> ingredientsName = new ArrayList<>();
    ArrayList<String> ingredientsCount = new ArrayList<>();
    ArrayList<String> ingredientsUnit = new ArrayList<>();

    ArrayList<Ingredients> ingredientsNewMealList = new ArrayList<>();
    ArrayList<Meal> mealList;
    Meal newMeal;
    int newMealID;

    DatabaseAccess db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        etMealName = findViewById(R.id.etMealName);
        etMealCount = findViewById(R.id.etMealCount);
        etMealDescription = findViewById(R.id.etMealDescription);
        btnAddMealToList = findViewById(R.id.btnAddMealToList);

        etMealIngredientName = findViewById(R.id.etMealIngredientName);
        etMealIngredientCount = findViewById(R.id.etMealIngredientCount);
        etMealIngredientUnit = findViewById(R.id.etMealIngredientUnit);

        btnAddMealIngredient = findViewById(R.id.btnAddMealIngredient);
        container = findViewById(R.id.container);
        btnAddMealIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View addView = layoutInflater.inflate(R.layout.row, null);
                EditText etMealIngredientNameOut = addView.findViewById(R.id.etMealIngredientNameOut);
                etMealIngredientNameOut.setText(etMealIngredientName.getText().toString());
                EditText etMealIngredientCountOut = addView.findViewById(R.id.etMealIngredientCountOut);
                etMealIngredientCountOut.setText(etMealIngredientCount.getText().toString());
                EditText etMealIngredientUnitOut = addView.findViewById(R.id.etMealIngredientUnitOut);
                etMealIngredientUnitOut.setText(etMealIngredientUnit.getText().toString());

                Button btnRemoveMealIngredient = addView.findViewById(R.id.btnRemoveMealIngredient);

                final View.OnClickListener thisListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((LinearLayout) addView.getParent()).removeView(addView);
                    }
                };
                btnRemoveMealIngredient.setOnClickListener(thisListener);
                container.addView(addView);

                etMealIngredientName.setText("");
                etMealIngredientCount.setText("");
                etMealIngredientUnit.setText("");
            }
        });

        btnAddMealToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listAllIngredients();
                if (!etMealName.getText().toString().equals("") && !etMealCount.getText().toString().equals("")) {
                    if (ingredientsName.size() != 0 && ingredientsCount.size() != 0 && ingredientsUnit.size() != 0) {
                        if (ingredientsName.size() == ingredientsCount.size() && ingredientsName.size() == ingredientsUnit.size()) {
                            int arrayListsize = ingredientsName.size();
                            newMeal = createMeal(etMealName.getText().toString(), etMealCount.getText().toString(), etMealDescription.getText().toString());
                            addMealToDatabase(newMeal);
                            for (int i = 0; i < arrayListsize; i++) {
                                createIngredientsList(ingredientsName.get(i), ingredientsCount.get(i), ingredientsUnit.get(i), newMealID);
                            }
                            addIngredientsListToDatabase(ingredientsNewMealList);
                            Toast.makeText(getApplicationContext(), "Dodano nowy posiłek", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(),MealListActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Któreś pole w składnikach jest puste, uzupełnij", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Nie dodałeś składników. Musi być co najmniej jeden.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Nazwa posiłku lub liczba porcji jest pusta, uzupełnij", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void addIngredientsListToDatabase(ArrayList<Ingredients> ingredientsList) {
        db = DatabaseAccess.getInstance(getApplicationContext());
        db.open();
        ArrayList<Ingredients> ingredientsFromDb;
        for (Ingredients ingredients : ingredientsList) {
            ingredientsFromDb = db.getAllIngredients();
            int sizeIngredientsListInDb = ingredientsFromDb.size();
            int ingredientID = sizeIngredientsListInDb + 1;
            db.addNewIngredient(ingredients, ingredientID);
        }
        db.close();
    }

    private void addMealToDatabase(Meal newMeal) {
        db = DatabaseAccess.getInstance(getApplicationContext());
        db.open();
        mealList = db.getMealsFromDb();
        newMealID = mealList.size() + 1;
        db.addNewMeal(newMealID, newMeal);
        db.close();
    }

    private Meal createMeal(String mealName, String mealCount, String mealDescription) {
        String name = mealName;
        int count = Integer.valueOf(mealCount);
        String description = mealDescription;
        Meal meal = new Meal(name, count, description);
        return meal;
    }

    private void createIngredientsList(String nameIngredient, String countIngredient, String unitIngredient, int newMealID) {
        String name = nameIngredient;
        float count = Float.valueOf(countIngredient);
        String unit = unitIngredient;
        Ingredients ingredient = new Ingredients(name, count, unit, newMealID);
        ingredientsNewMealList.add(ingredient);
    }

    private void listAllIngredients() {
        int childOut = container.getChildCount();
        for (int i = 0; i < childOut; i++) {
            View thisChild = container.getChildAt(i);

            EditText childIngredientNameOut = thisChild.findViewById(R.id.etMealIngredientNameOut);
            EditText childIngredientCountOut = thisChild.findViewById(R.id.etMealIngredientCountOut);
            EditText childIngredientUnitOut = thisChild.findViewById(R.id.etMealIngredientUnitOut);

            ingredientsName.add(childIngredientNameOut.getText().toString());
            ingredientsCount.add(childIngredientCountOut.getText().toString());
            ingredientsUnit.add(childIngredientUnitOut.getText().toString());
        }
    }
}
