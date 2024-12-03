/**
 * AddActivity allows users to add a new dish by entering its name, ingredients, instructions, and nutritional information.
 * Based on the selected meal type (entree, appetizer, or dessert), the activity redirects the user to the corresponding
 * activity after saving the new dish.
 * The dish is added to a DishContainer, and a toast message confirms the addition.
 */
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

    // Create an instance of DishContainer to manage the list of dishes.
    DishContainer dishContainer = new DishContainer(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_dish);

        // Retrieve the meal type (entree, dessert, or appetizer) passed from the previous activity
        String mealType = getIntent().getStringExtra("MealType");

        // Find the 'Add' button and set an onClickListener for it
        Button addButton = findViewById(R.id.add);
        addButton.setOnClickListener(v -> {

            // Show a Toast message to confirm that the dish was added
            Toast.makeText(AddActivity.this, "Dish added!", Toast.LENGTH_SHORT).show();

            // Prepare the Intent to navigate to the appropriate activity based on the meal type
            Intent intent = new Intent();
            if (mealType.equals("entree")) {
                intent = new Intent(AddActivity.this, EntreeActivity.class);
            } else if (mealType.equals("dessert")) {
                intent = new Intent(AddActivity.this, DessertActivity.class);
            } else if (mealType.equals("appetizer")) {
                intent = new Intent(AddActivity.this, AppetizerActivity.class);
            }

            // Get the input values for the new dish
            EditText dishNameInput = findViewById(R.id.userInput1);
            EditText dishIngredientsInput = findViewById(R.id.userInput2);
            EditText dishInstructionsInput = findViewById(R.id.userInput3);
            EditText dishNutrientsInput = findViewById(R.id.userInput4);

            // Retrieve the text entered by the user
            String dishName = dishNameInput.getText().toString();
            String ingredients = dishIngredientsInput.getText().toString();
            String instructions = dishInstructionsInput.getText().toString();
            String nutrients = dishNutrientsInput.getText().toString();

            // Create a new Dish object with the entered details and the meal type
            dishContainer.addDish(new Dish(mealType, dishName, ingredients, instructions, nutrients));

            // Start the corresponding activity after the dish is added
            startActivity(intent);
        });
    }
}