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
 * This activity handles the screen for displaying all dessert dishes stored within the app.
 * It allows users to navigate between different dish types (Appetizers, Entrees, and Desserts),
 * add new dishes, and view detailed information about each dish.
 *
 * @author Luke Alvarado
 */
public class DessertActivity extends AppCompatActivity {

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
        setContentView(R.layout.dessert);

        dishView = findViewById(R.id.buttonContainer);

        // Button for adding a new dish
        Button addButton = findViewById(R.id.addNewDishButton);
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(DessertActivity.this, AddActivity.class);
            intent.putExtra("MealType", "dessert");
            startActivity(intent);
        });

        // Buttons for navigating to Appetizer and Entree screens
        Button entreeButton = findViewById(R.id.entreeButton);
        entreeButton.setOnClickListener(v -> startActivity(new Intent(DessertActivity.this, EntreeActivity.class)));

        Button appetizerButton = findViewById(R.id.appetizerButton);
        appetizerButton.setOnClickListener(v -> startActivity(new Intent(DessertActivity.this, AppetizerActivity.class)));
    }

    /**
     * Reloads all dishes every time this screen is opened. Filters and displays only dessert dishes.
     */
    @Override
    protected void onResume() {
        super.onResume();
        loadDessertDishes();
    }

    /**
     * Loads all dessert dishes into the screen by filtering the full list of dishes
     * and displaying each dessert as a button.
     */
    private void loadDessertDishes() {
        // Clear any existing views to prevent duplicates
        dishView.removeAllViews();

        // Filter and display dessert dishes
        ArrayList<Dish> dessertDishes = getDessertDishes(dishes);
        for (Dish dish : dessertDishes) {
            addDishButton(dish, dishView);
        }
    }

    /**
     * Filters and returns all dishes labeled as desserts from a list of all dishes.
     *
     * @param allDishes A list of all dishes available in the app.
     * @return A list of dessert dishes.
     */
    private ArrayList<Dish> getDessertDishes(List<Dish> allDishes) {
        ArrayList<Dish> dessertDishes = new ArrayList<>();
        for (Dish dish : allDishes) {
            if (dish.getDishType().equals("dessert")) {
                dessertDishes.add(dish);
            }
        }
        return dessertDishes;
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
            Intent intent = new Intent(DessertActivity.this, DishActivity.class);
            intent.putExtra("DishName", dish.getDishName());
            intent.putExtra("MealType", "dessert");
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
