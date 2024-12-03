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
 * This class handles the screen for displaying all Entree dishes stored within the app
 *
 * @author Luke Alvarado
 *
 * UTSA CS 3443
 * NutriChef
 * Fall 2024
 */

public class EntreeActivity extends AppCompatActivity {

    //Layout for storing individual dishes dynamically
    private LinearLayout dishView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entree);
        dishView = findViewById(R.id.buttonContainer);

        //Button for adding a dish
        Button addButton = findViewById(R.id.addNewDishButton);
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(EntreeActivity.this, AddActivity.class);
            intent.putExtra("MealType", "entree");
            startActivity(intent);
        });

        //Buttons for taking user to other 2 screens of dishes
        Button appetizerButton = findViewById(R.id.appetizerButton);
        appetizerButton.setOnClickListener(v -> startActivity(new Intent(EntreeActivity.this, AppetizerActivity.class)));

        Button dessertButton = findViewById(R.id.dessertButton);
        dessertButton.setOnClickListener(v -> startActivity(new Intent(EntreeActivity.this, DessertActivity.class)));


    }

    //Reload all dishes everytime this screen is opened
    @Override
    protected void onResume() {
        super.onResume();
        loadEntreeDishes();
    }

    //Method for loading all appetizers into this screen
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
     * Returns all dishes that are labeled as appetizers
     * @return
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
     * Adds a specific dish to the screen
     * @param dish
     * @param container
     */
    private void addDishButton(Dish dish, LinearLayout container) {
        LinearLayout dishLayout = new LinearLayout(this);
        dishLayout.setOrientation(LinearLayout.HORIZONTAL);

        Button dishInfo = new Button(this);
        dishInfo.setText(dish.getDishName());
        dishInfo.setTextSize(18);

        Typeface customFont = ResourcesCompat.getFont(this, R.font.welcome);
        dishInfo.setTypeface(customFont);

        dishInfo.setOnClickListener(v -> {
            Intent intent = new Intent(EntreeActivity.this, DishActivity.class);
            intent.putExtra("DishName", dish.getDishName());
            intent.putExtra("MealType", "entree");
            startActivity(intent);
        });

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
