package com.example.nutrichef;

import static com.example.nutrichef.MainActivity.dishes;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import com.example.nutrichef.model.Dish;
import java.util.ArrayList;
import java.util.List;

/**
 * This activity handles the screen for displaying all appetizer dishes stored within the app.
 * It allows users to navigate between different dish types (Appetizers, Entrees, and Desserts),
 * add new dishes, and view detailed information about each dish.
 *
 * @author Luke Alvarado
 */
public class AppetizerActivity extends AppCompatActivity {

    // Layout for storing individual dishes dynamically
    private LinearLayout dishView;

    /**
     * Initializes the activity and sets up the layout with buttons to navigate between screens.
     * It also sets the functionality for the "Add New Dish" button to open the AddActivity.
     *
     * @param savedInstanceState If the activity is being re-initialized after being previously shut down,
     *                           this bundle contains the data it most recently supplied.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appetizer);

        dishView = findViewById(R.id.buttonContainer);

        // Button for adding a new dish
        Button addButton = findViewById(R.id.addNewDishButton);
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(AppetizerActivity.this, AddActivity.class);
            intent.putExtra("MealType", "appetizer");
            startActivity(intent);
        });

        // Buttons for navigating to Entree and Dessert screens
        Button entreeButton = findViewById(R.id.entreeButton);
        entreeButton.setOnClickListener(v -> startActivity(new Intent(AppetizerActivity.this, EntreeActivity.class)));

        Button dessertButton = findViewById(R.id.dessertButton);
        dessertButton.setOnClickListener(v -> startActivity(new Intent(AppetizerActivity.this, DessertActivity.class)));
    }

    /**
     * Reloads all dishes every time this screen is opened. Filters and displays only appetizer dishes.
     */
    @Override
    protected void onResume() {
        super.onResume();
        loadAppetizerDishes();
    }

    /**
     * Loads all appetizer dishes into the screen by filtering the full list of dishes
     * and displaying each appetizer as a button.
     */
    private void loadAppetizerDishes() {
        // Clear any existing views to prevent duplicates
        dishView.removeAllViews();

        // Filter and display appetizer dishes
        ArrayList<Dish> appetizerDishes = getAppetizerDishes(dishes);
        for (Dish dish : appetizerDishes) {
            addDishButton(dish, dishView);
        }
    }

    /**
     * Filters and returns all dishes labeled as appetizers from a list of all dishes.
     *
     * @param allDishes A list of all dishes available in the app.
     * @return A list of appetizer dishes.
     */
    private ArrayList<Dish> getAppetizerDishes(List<Dish> allDishes) {
        ArrayList<Dish> appetizerDishes = new ArrayList<>();
        for (Dish dish : allDishes) {
            if ("appetizer".equals(dish.getDishType())) {
                appetizerDishes.add(dish);
            }
        }
        return appetizerDishes;
    }

    /**
     * Adds a specific dish to the screen as a button. When clicked, it navigates to the DishActivity
     * where users can view detailed information about the dish.
     *
     * @param dish The dish to be displayed as a button.
     * @param container The layout container where the dish button will be added.
     */
    private void addDishButton(Dish dish, LinearLayout container) {
        // Declare new layout for the dish button
        LinearLayout dishLayout = new LinearLayout(this);
        dishLayout.setOrientation(LinearLayout.HORIZONTAL);

        // Declare button for the dish
        Button dishInfo = new Button(this);
        dishInfo.setText(dish.getDishName());
        dishInfo.setTextSize(18);

        // Set custom font
        Typeface customFont = ResourcesCompat.getFont(this, R.font.welcome);
        dishInfo.setTypeface(customFont);

        // When clicked, move to DishActivity to view detailed dish information
        dishInfo.setOnClickListener(v -> {
            Intent intent = new Intent(AppetizerActivity.this, DishActivity.class);
            intent.putExtra("DishName", dish.getDishName());
            intent.putExtra("MealType", "appetizer");
            startActivity(intent);
        });

        // Add the dish button to the layout
        dishLayout.addView(dishInfo);
        dishLayout.setPadding(0, 0, 0, 100);
        container.addView(dishLayout);
    }

    /**
     * Finds and returns a dish based on its name.
     *
     * @param name The name of the dish to find.
     * @return The dish matching the given name, or null if no dish is found.
     */
    public static Dish getDishByName(String name) {
        for (Dish dish : dishes) {
            if (dish.getDishName().equals(name)) {
                return dish;
            }
        }
        return null;
    }
}
