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

/**
 * This activity handles the screen for displaying all entree dishes stored within the app.
 * It allows users to navigate between different dish types (Appetizers, Entrees, and Desserts),
 * add new dishes, and view detailed information about each dish.
 *
 * @author Luke Alvarado
 */
public class EntreeActivity extends AppCompatActivity {

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
        setContentView(R.layout.entree);
        dishView = findViewById(R.id.buttonContainer);

        // Button for adding a new dish
        Button addButton = findViewById(R.id.addNewDishButton);
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(EntreeActivity.this, AddActivity.class);
            intent.putExtra("MealType", "entree");
            startActivity(intent);
        });

        // Buttons for navigating to Appetizer and Dessert screens
        Button appetizerButton = findViewById(R.id.appetizerButton);
        appetizerButton.setOnClickListener(v -> startActivity(new Intent(EntreeActivity.this, AppetizerActivity.class)));

        Button dessertButton = findViewById(R.id.dessertButton);
        dessertButton.setOnClickListener(v -> startActivity(new Intent(EntreeActivity.this, DessertActivity.class)));
    }

    /**
     * Reloads all dishes every time this screen is opened. Filters and displays only entree dishes.
     */
    @Override
    protected void onResume() {
        super.onResume();
        loadEntreeDishes();
    }

    /**
     * Loads all entree dishes into the screen by filtering the full list of dishes
     * and displaying each entree as a button.
     */
    private void loadEntreeDishes() {
        // Clear the container to prevent duplicate entries
        dishView.removeAllViews();

        // Filter and display entree dishes
        ArrayList<Dish> entreeDishes = getEntreeDishes();
        for (Dish dish : entreeDishes) {
            addDishButton(dish, dishView);
        }
    }

    /**
     * Filters and returns all dishes labeled as entrees from the list of all dishes.
     *
     * @return A list of entree dishes.
     */
    private ArrayList<Dish> getEntreeDishes() {
        ArrayList<Dish> entreeDishes = new ArrayList<>();

        for (Dish dish : dishes) {
            if ("entree".equals(dish.getDishType())) {
                entreeDishes.add(dish);
            }
        }

        return entreeDishes;
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
            Intent intent = new Intent(EntreeActivity.this, DishActivity.class);
            intent.putExtra("DishName", dish.getDishName());
            intent.putExtra("MealType", "entree");
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
