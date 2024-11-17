package com.example.nutrichef;

import static com.example.nutrichef.MainActivity.dishes;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import com.example.nutrichef.model.Dish;

public class DishActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dish);

        String name = getIntent().getStringExtra("DishName");
        String mealType = getIntent().getStringExtra("MealType");

        // Initialize dish as a non-final variable
        Dish dish = null;

        // Ensure that dish is fetched based on the meal type
        if ("entree".equals(mealType)) {
            dish = EntreeActivity.getDishByName(name);
        } else if ("appetizer".equals(mealType)) {
            dish = AppetizerActivity.getDishByName(name);
        } else if ("dessert".equals(mealType)) {
            dish = DessertActivity.getDishByName(name);
        }

        // Check if the dish is null
        if (dish == null) {
            Toast.makeText(this, "Dish not found", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity if the dish is null
            return;
        }

        // Use a final reference to dish for the lambda expressions
        final Dish finalDish = dish;

        TextView nameView = findViewById(R.id.dishName);
        nameView.setText(name);
        TextView ingredientView = findViewById(R.id.dishIngredient);
        ingredientView.setText(dish.getDishIngredients());
        TextView instructView = findViewById(R.id.dishInstruct);
        instructView.setText(dish.getDishInstructions());
        TextView nutritionView = findViewById(R.id.dishNutrition);
        nutritionView.setText(dish.getDishNutrients());
        ImageView dishImageView = findViewById(R.id.defImage);
        int imageResourceId = getDishImageResourceId(dish.getDishType());
        dishImageView.setImageResource(imageResourceId);

        // Creates Intent for the modify button
        Button modifyButton = findViewById(R.id.modifyButton);
        modifyButton.setOnClickListener(v -> {
            Intent intent2 = new Intent(DishActivity.this, ModifyActivity.class);
            intent2.putExtra("DishName", finalDish.getDishName()); // Use finalDish
            intent2.putExtra("MealType", mealType);
            startActivity(intent2);
        });

        // Creates Intent for the delete button
        Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(v -> {
            Intent intent3 = new Intent(DishActivity.this, DeleteActivity.class);
            intent3.putExtra("DishName", finalDish.getDishName()); // Use finalDish
            intent3.putExtra("MealType", mealType);
            startActivity(intent3);
        });


        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(DishActivity.this, EntreeActivity.class);
                startActivity(intent);
                finish();  // This example finishes the activity
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