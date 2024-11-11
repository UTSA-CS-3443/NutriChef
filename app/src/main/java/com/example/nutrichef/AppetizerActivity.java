package com.example.nutrichef;

import static com.example.nutrichef.MainActivity.dishes;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nutrichef.model.Dish;
import com.example.nutrichef.model.DishActivity;

import java.util.ArrayList;
import java.util.List;

public class AppetizerActivity extends AppCompatActivity {

    private LinearLayout dishView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appetizer);

        dishView = findViewById(R.id.buttonContainer);

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

    @Override
    protected void onResume() {
        super.onResume();
        loadAppetizerDishes();
    }

    private void loadAppetizerDishes() {
        // Clear any existing views to prevent duplicates
        dishView.removeAllViews();
        // Filter and display appetizer dishes
        ArrayList<Dish> appetizerDishes = getAppetizerDishes(dishes);
        for (Dish dish : appetizerDishes) {
            addDishButton(dish, dishView);
        }
    }

    private ArrayList<Dish> getAppetizerDishes(List<Dish> allDishes) {
        ArrayList<Dish> appetizerDishes = new ArrayList<>();
        for (Dish dish : allDishes) {
            if ("appetizer".equals(dish.getDishType())) {
                appetizerDishes.add(dish);
            }
        }
        return appetizerDishes;
    }

    private void addDishButton(Dish dish, LinearLayout container) {
        LinearLayout dishLayout = new LinearLayout(this);
        dishLayout.setOrientation(LinearLayout.HORIZONTAL);

        Button dishInfo = new Button(this);
        dishInfo.setText(dish.getDishName());
        dishInfo.setTextSize(18);

        dishInfo.setOnClickListener(v -> {
            Intent intent = new Intent(AppetizerActivity.this, DishActivity.class);
            intent.putExtra("DishName", dish.getDishName());
            intent.putExtra("MealType", "appetizer");
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
