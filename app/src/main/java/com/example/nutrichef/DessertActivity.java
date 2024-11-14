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
import com.example.nutrichef.model.DishContainer;

import java.util.ArrayList;
import java.util.List;

public class DessertActivity extends AppCompatActivity {

    private LinearLayout dishView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dessert);

        dishView = findViewById(R.id.buttonContainer);

        Button addButton = findViewById(R.id.addNewDishButton);
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(DessertActivity.this, AddActivity.class);
            intent.putExtra("MealType", "dessert");
            startActivity(intent);
        });

        Button entreeButton = findViewById(R.id.entreeButton);
        entreeButton.setOnClickListener(v -> startActivity(new Intent(DessertActivity.this, EntreeActivity.class)));

        Button appetizerButton = findViewById(R.id.appetizerButton);
        appetizerButton.setOnClickListener(v -> startActivity(new Intent(DessertActivity.this, AppetizerActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDessertDishes();
    }

    private void loadDessertDishes() {
        // Clear any existing views to prevent duplicates
        dishView.removeAllViews();
        // Filter and display dessert dishes
        ArrayList<Dish> dessertDishes = getDessertDishes(dishes);
        for (Dish dish : dessertDishes) {
            addDishButton(dish, dishView);
        }
    }

    private ArrayList<Dish> getDessertDishes(List<Dish> allDishes) {
        ArrayList<Dish> dessertDishes = new ArrayList<>();
        for (Dish dish : allDishes) {
            if (dish.getDishType().equals("dessert")) {
                dessertDishes.add(dish);
            }
        }
        return dessertDishes;
    }

    private void addDishButton(Dish dish, LinearLayout container) {
        LinearLayout dishLayout = new LinearLayout(this);
        dishLayout.setOrientation(LinearLayout.HORIZONTAL);

        Button dishInfo = new Button(this);
        dishInfo.setText(dish.getDishName());
        dishInfo.setTextSize(18);

        Typeface customFont = ResourcesCompat.getFont(this, R.font.welcome);
        dishInfo.setTypeface(customFont);

        dishInfo.setOnClickListener(v -> {
            Intent intent = new Intent(DessertActivity.this, DishActivity.class);
            intent.putExtra("DishName", dish.getDishName());
            intent.putExtra("MealType", "dessert");
            startActivity(intent);
        });

        dishLayout.addView(dishInfo);
        dishLayout.setPadding(0, 0, 0, 100);
        container.addView(dishLayout);
    }

    public static Dish getDishByName(String name) {
        for (Dish dish : dishes) {
            if (dish.getDishName().equals(name)) {
                return dish;
            }
        }
        return null;
    }
}
