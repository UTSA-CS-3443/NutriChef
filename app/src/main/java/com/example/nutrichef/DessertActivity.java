package com.example.nutrichef;

import static com.example.nutrichef.MainActivity.dishes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class DessertActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dessert);
        LinearLayout dishContainer = findViewById(R.id.buttonContainer);
        ArrayList<Dish> dessertDishes = getDessertDishes(dishes);
        for (Dish dish : dessertDishes) {
            addDishButton(dish, dishContainer);
        }

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

    private ArrayList<Dish> getDessertDishes(List<Dish> allDishes) {
        ArrayList<Dish> dessertDishes = new ArrayList<>();
        for (Dish dish : allDishes) {
            if (dish.getMealType().equals("dessert")) {
                dessertDishes.add(dish);
            }
        }
        return dessertDishes;
    }

    private void addDishButton(Dish dish, LinearLayout container) {
        LinearLayout dishLayout = new LinearLayout(this);
        dishLayout.setOrientation(LinearLayout.HORIZONTAL);

        Button dishInfo = new Button(this);
        dishInfo.setText(dish.getName());
        dishInfo.setTextSize(18);

        dishInfo.setOnClickListener(v -> {
            Intent intent = new Intent(DessertActivity.this, DishActivity.class);
            intent.putExtra("DishName", dish.getName());
            intent.putExtra("MealType", "dessert");
            startActivity(intent);
        });

        dishLayout.addView(dishInfo);
        dishLayout.setPadding(0, 0, 0, 100);
        container.addView(dishLayout);
    }

    public static Dish getDishByName(String name) {
        for (Dish dish : dishes) {
            if (dish.getName().equals(name)) {
                return dish;
            }
        }
        return null;
    }
}