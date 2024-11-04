package com.example.nutrichef;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dish);

        String name = getIntent().getStringExtra("DishName");
        String mealType = getIntent().getStringExtra("MealType");

        if(mealType.equals("entree")) {
            Dish dish = EntreeActivity.getDishByName(name);
        }
        else if(mealType.equals("appetizer")) {
            Dish dish = AppetizerActivity.getDishByName(name);
        }
        else if(mealType.equals("dessert")) {
            Dish dish = DessertActivity.getDishByName(name);
        }


        TextView nameView = findViewById(R.id.dishName);
        nameView.setText(name);
        TextView ingredientView = findViewById(R.id.dishIngredient);
        ingredientView.setText(dish.getIngredients());
        TextView instructView = findViewById(R.id.dishInstruct);
        instructView.setText(dish.getInstructions());
        TextView nutritionView = findViewById(R.id.dishNutrition);
        nutritionView.setText(dish.getNutrition());

        Button modifyButton = findViewById(R.id.modifyButton);
        modifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DishActivity.this, modifyActivity.class);
                intent.putExtra("DishName", dish.getName());
                intent.putExtra("MealType", "entree");
                startActivity(intent);
            }
        });

        Button deleteButton = (R.id.deleteButtonButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DishActivity.this, deleteActivity.class);
                intent.putExtra("DishName", dish.getName());
                intent.putExtra("MealType", mealType);
                startActivity(intent);
            }
        });

    }
}
