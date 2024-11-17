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

    private Dish dish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        // Retrieve dishName and mealType from the intent
        String dishName = getIntent().getStringExtra("DishName");
        String mealType = getIntent().getStringExtra("MealType");

        // Check if dishName and mealType are null
        if (dishName == null || mealType == null) {
            Toast.makeText(this, "Error: Missing dish details", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Fetch the dish object
        dish = DishActivity.getDishByName(dishName);

        if (dish == null) {
            Toast.makeText(this, "Dish not found", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity if the dish is not found
            return;
        }

        // Set up the image view
        ImageView dishImageView = findViewById(R.id.defImage);
        int imageResourceId = getDishImageResourceId(dish.getDishType());
        if (imageResourceId != 0) {
            dishImageView.setImageResource(imageResourceId);
        }

        // Set up the EditText fields and populate them with the current dish details
        EditText editDishName = findViewById(R.id.editDishName);
        editDishName.setText(dish.getDishName());

        EditText editDishIngredients = findViewById(R.id.editDishIngredients);
        editDishIngredients.setText(dish.getDishIngredients());

        EditText editDishInstructions = findViewById(R.id.editDishInstructions);
        editDishInstructions.setText(dish.getDishInstructions());

        EditText editDishNutrition = findViewById(R.id.editDishNutrition);
        editDishNutrition.setText(dish.getDishNutrients());

        // Set up the confirm changes button
        Button confirmChangesButton = findViewById(R.id.confirmChangesButton);
        confirmChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update the dish object with the new details
                dish.setDishName(editDishName.getText().toString());
                dish.setDishIngredients(editDishIngredients.getText().toString());
                dish.setDishInstructions(editDishInstructions.getText().toString());
                dish.setDishNutrients(editDishNutrition.getText().toString());

                // Save the updated dish (you might need to call a method in DishContainer to save it)
                DishContainer dishContainer = new DishContainer(ModifyActivity.this);
                dishContainer.modifyDish(dish);

                // Show a confirmation message and close the activity
                Toast.makeText(ModifyActivity.this, "Changes saved successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ModifyActivity.this, DishActivity.class);
                intent.putExtra("DishName", dish.getDishName());
                intent.putExtra("MealType", mealType);
                startActivity(intent);
            }
        });
    }

    // Handles retrieval of dish type images
    private int getDishImageResourceId(String dishType) {
        switch (dishType.toLowerCase()) {
            case "entree":
                return R.drawable.entree;
            case "dessert":
                return R.drawable.desserts;
            case "appetizer":
                return R.drawable.appetizers;
            default:
                return 0;
        }
    }
}
