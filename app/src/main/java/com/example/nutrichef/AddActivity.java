package com.example.nutrichef;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nutrichef.model.Dish;
import com.example.nutrichef.model.DishContainer;

public class AddActivity extends AppCompatActivity{
    DishContainer dishContainer = new DishContainer(this);
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_dish);


        String mealType = getIntent().getStringExtra("MealType");
        Button addButton = findViewById(R.id.add);
        addButton.setOnClickListener(v -> {
            Toast.makeText(AddActivity.this, "Dish added!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            if (mealType.equals("entree")) {
            intent = new Intent(AddActivity.this, EntreeActivity.class);
            }
            else if (mealType.equals("dessert")) {
            intent = new Intent(AddActivity.this, DessertActivity.class);
            }
            else if (mealType.equals("appetizer")) {
            intent = new Intent(AddActivity.this, AppetizerActivity.class);
            }

        EditText dishNameInput = findViewById(R.id.userInput1);
        EditText dishIngredientsInput = findViewById(R.id.userInput2);
        EditText dishInstructionsInput = findViewById(R.id.userInput3);
        EditText dishNutrientsInput = findViewById(R.id.userInput4);

        String dishName = dishNameInput.getText().toString();
        String ingredients = dishIngredientsInput.getText().toString();
        String instructions = dishInstructionsInput.getText().toString();
        String nutrients = dishNutrientsInput.getText().toString();

        dishContainer.addDish(new Dish(mealType, dishName, ingredients, instructions, nutrients));
        startActivity(intent);
        });


    }
}
