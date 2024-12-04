/**
 * The ModifyActivity class allows users to modify the details of an existing dish, including its name,
 * ingredients, instructions, and nutritional information.
 * It retrieves the selected dish based on its name and meal type, populates the UI with the current details,
 * and allows users to save changes.
 * The updated dish is then saved back into the DishContainer, and a confirmation message is shown to the user.
 *
 *  * @author Mauricio Villegas
 *
 *  *  * UTSA CS 3443
 *  *  * NutriChef
 *  *  * Fall 2024
 */
package com.example.nutrichef;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.nutrichef.model.Dish;
import com.example.nutrichef.model.DishContainer;

public class ModifyActivity extends AppCompatActivity {

    private Dish dish;  // Instance variable to hold the current dish being modified

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        // Retrieve the dish name and meal type from the intent
        String dishName = getIntent().getStringExtra("DishName");
        String mealType = getIntent().getStringExtra("MealType");

        // If dishName or mealType is missing, show an error and close the activity
        if (dishName == null || mealType == null) {
            Toast.makeText(this, "Error: Missing dish details", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Fetch the dish object based on the provided dishName
        dish = DishActivity.getDishByName(dishName);

        // If the dish is not found, show an error and finish the activity
        if (dish == null) {
            Toast.makeText(this, "Dish not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Set up the image view and display the corresponding dish image
        ImageView dishImageView = findViewById(R.id.defImage);
        int imageResourceId = getDishImageResourceId(dish.getDishType());
        if (imageResourceId != 0) {
            dishImageView.setImageResource(imageResourceId);
        }

        // Set up EditText fields and pre-populate them with the current dish's details
        EditText editDishName = findViewById(R.id.editDishName);
        editDishName.setText(dish.getDishName());

        EditText editDishIngredients = findViewById(R.id.editDishIngredients);
        editDishIngredients.setText(dish.getDishIngredients());

        EditText editDishInstructions = findViewById(R.id.editDishInstructions);
        editDishInstructions.setText(dish.getDishInstructions());

        EditText editDishNutrition = findViewById(R.id.editDishNutrition);
        editDishNutrition.setText(dish.getDishNutrients());

        // Set up the button that confirms changes and saves the updated dish
        Button confirmChangesButton = findViewById(R.id.confirmChangesButton);
        confirmChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update the dish object with the new details entered by the user
                dish.setDishName(editDishName.getText().toString());
                dish.setDishIngredients(editDishIngredients.getText().toString());
                dish.setDishInstructions(editDishInstructions.getText().toString());
                dish.setDishNutrients(editDishNutrition.getText().toString());

                // Save the updated dish in the DishContainer
                DishContainer dishContainer = new DishContainer(ModifyActivity.this);
                dishContainer.modifyDish(dish);

                // Show a confirmation toast message and navigate back to the DishActivity.
                Toast.makeText(ModifyActivity.this, "Changes saved successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ModifyActivity.this, DishActivity.class);
                intent.putExtra("DishName", dish.getDishName());
                intent.putExtra("MealType", mealType);
                startActivity(intent);
            }
        });
    }

    // Helper method to retrieve the image resource ID based on the dish type
    private int getDishImageResourceId(String dishType) {
        switch (dishType.toLowerCase()) {
            case "entree":
                return R.drawable.entree;  // Return entree image
            case "dessert":
                return R.drawable.desserts;  // Return dessert image
            case "appetizer":
                return R.drawable.appetizers;  // Return appetizer image
            default:
                return 0;  // Return default if no match is found
        }
    }
}