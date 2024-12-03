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
 * This class handles the screen for displaying all Dessert dishes stored within the app
 *
 * @author Luke Alvarado
 *
 * UTSA CS 3443
 * NutriChef
 * Fall 2024
 */

public class DessertActivity extends AppCompatActivity {

    //Layout for storing individual dishes dynamically
    private LinearLayout dishView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dessert);

        dishView = findViewById(R.id.buttonContainer);

        //Button for adding a dish
        Button addButton = findViewById(R.id.addNewDishButton);
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(DessertActivity.this, AddActivity.class);
            intent.putExtra("MealType", "dessert");
            startActivity(intent);
        });

        //Buttons for taking user to other 2 screens of dishes
        Button entreeButton = findViewById(R.id.entreeButton);
        entreeButton.setOnClickListener(v -> startActivity(new Intent(DessertActivity.this, EntreeActivity.class)));

        Button appetizerButton = findViewById(R.id.appetizerButton);
        appetizerButton.setOnClickListener(v -> startActivity(new Intent(DessertActivity.this, AppetizerActivity.class)));
    }

    //Reload all dishes everytime this screen is opened
    @Override
    protected void onResume() {
        super.onResume();
        loadDessertDishes();
    }

    //Method for loading all appetizers into this screen
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
     * Returns all dishes that are labeled as appetizers
     * @param allDishes
     * @return
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
     * Adds a specific dish to the screen
     * @param dish
     * @param container
     */
    private void addDishButton(Dish dish, LinearLayout container) {
        //Declare new layout
        LinearLayout dishLayout = new LinearLayout(this);
        dishLayout.setOrientation(LinearLayout.HORIZONTAL);

        //Declare button for new dish
        Button dishInfo = new Button(this);
        dishInfo.setText(dish.getDishName());
        dishInfo.setTextSize(18);

        //Set font
        Typeface customFont = ResourcesCompat.getFont(this, R.font.welcome);
        dishInfo.setTypeface(customFont);

        //When clicked move to DishActivity
        dishInfo.setOnClickListener(v -> {
            Intent intent = new Intent(DessertActivity.this, DishActivity.class);
            intent.putExtra("DishName", dish.getDishName());
            intent.putExtra("MealType", "dessert");
            startActivity(intent);
        });

        //Add dish to layout
        dishLayout.addView(dishInfo);
        dishLayout.setPadding(0, 0, 0, 100);
        container.addView(dishLayout);
    }

    /**
     * Find a dish based on name
     * @param name
     * @return
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
