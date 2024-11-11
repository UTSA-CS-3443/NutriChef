package com.example.nutrichef.model;

import static com.example.nutrichef.MainActivity.dishes;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nutrichef.AppetizerActivity;
import com.example.nutrichef.DeleteActivity;
import com.example.nutrichef.DessertActivity;
import com.example.nutrichef.EntreeActivity;
import com.example.nutrichef.R;

public class DishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dish);

        String name = getIntent().getStringExtra("DishName");
        String mealType = getIntent().getStringExtra("MealType");

        Dish dish = null;

        // Ensure that dish is fetched based on the meal type
        if ("entree".equals(mealType)) {
            dish = EntreeActivity.getDishByName(name);
        } else if ("appetizer".equals(mealType)) {
            dish = AppetizerActivity.getDishByName(name);
        } else if ("dessert".equals(mealType)) {
            dish = DessertActivity.getDishByName(name);
        }

        if (dish == null) {
            // Handle case where dish is not found
            finish(); // Close the activity if the dish is null
            return;
        }

        TextView nameView = findViewById(R.id.dishName);
        nameView.setText(name);
        TextView ingredientView = findViewById(R.id.dishIngredient);
        ingredientView.setText(dish.getDishIngredients());
        TextView instructView = findViewById(R.id.dishInstruct);
        instructView.setText(dish.getDishInstructions());
        TextView nutritionView = findViewById(R.id.dishNutrition);
        nutritionView.setText(dish.getDishNutrients());

        Button modifyButton = findViewById(R.id.modifyButton);
        modifyButton.setOnClickListener(v -> {
            // Uncomment and adjust intent if modifyActivity is implemented
            // Intent intent = new Intent(DishActivity.this, ModifyActivity.class);
            // intent.putExtra("DishName", dish.getDishName());
            // intent.putExtra("MealType", mealType);
            // startActivity(intent);
        });

        Button deleteButton = findViewById(R.id.deleteButton);
        Dish finalDish = dish;
        deleteButton.setOnClickListener(v -> {
            Intent intent = new Intent(DishActivity.this, DeleteActivity.class);
            intent.putExtra("DishName", finalDish.getDishName()); // Use getDishName()
            intent.putExtra("MealType", mealType);
            startActivity(intent);
            finish();
        });

    }

    public static Dish getDishByName(String name) {
        if (dishes != null) {
            for (Dish d : dishes) {
                if (d.getDishName().equals(name)) {
                    return d;
                }
            }
        }
        return null;
    }
}
