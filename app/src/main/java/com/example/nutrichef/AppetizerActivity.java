package com.example.nutrichef;

import static com.example.nutrichef.MainActivity.dishes;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppetizerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appetizer);
        LinearLayout dishContainer = findViewById(R.id.buttonContainer);
        ArrayList<Dish> appetizerDishes = getAppetizerDishes(dishes);
        for (Dish dish : appetizerDishes) {
            addDishButton(dish, dishContainer);
        }

        Button addButton = findViewById(R.id.addNewDishButton);
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(AppetizerActivity.this, AddActivity.class);
            intent.putExtra("MealType", "appetizer");
            startActivity(intent);
        });

        Button entreeButton = findViewById(R.id.entreeButton);
        entreeButton.setOnClickListener(v -> startActivity(new Intent(AppetizerActivity.this, EntreeActivity.class)));

        Button dessertButton = findViewById(R.id.dessertButton);
        dessertButton.setOnClickListener(v -> startActivity(new Intent(AppetizerActivity.this, DessertActivity.class)));
    }

    private ArrayList<Dish> getAppetizerDishes(List<Dish> allDishes) {
        ArrayList<Dish> appetizerDishes = new ArrayList<>();
        for (Dish dish : allDishes) {
            if (dish.getMealType().equals("appetizer")) {
                appetizerDishes.add(dish);
            }
        }
        return appetizerDishes;
    }

    private void addDishButton(Dish dish, LinearLayout container) {
        LinearLayout dishLayout = new LinearLayout(this);
        dishLayout.setOrientation(LinearLayout.HORIZONTAL);

        Button dishInfo = new Button(this);
        dishInfo.setText(dish.getName());
        dishInfo.setTextSize(18);

        dishInfo.setOnClickListener(v -> {
            Intent intent = new Intent(AppetizerActivity.this, DishActivity.class);
            intent.putExtra("DishName", dish.getName());
            intent.putExtra("MealType", "appetizer");
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

