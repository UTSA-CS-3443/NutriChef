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

public class EntreeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entree);
        LinearLayout dishContainer = findViewById(R.id.buttonContainer);
        ArrayList<Dish> entreeDishes = getEntreeDishes(dishes);
        for (Dish dish : entreeDishes) {
            addDishButton(dish, dishContainer);
        }

        Button addButton = findViewById(R.id.addNewDishButton);
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(EntreeActivity.this, AddActivity.class);
            intent.putExtra("MealType", "entree");
            startActivity(intent);
        });

        Button appetizerButton = findViewById(R.id.appetizerButton);
        appetizerButton.setOnClickListener(v -> startActivity(new Intent(EntreeActivity.this, AppetizerActivity.class)));

        Button dessertButton = findViewById(R.id.dessertButton);
        dessertButton.setOnClickListener(v -> startActivity(new Intent(EntreeActivity.this, DessertActivity.class)));
    }

    private ArrayList<Dish> getEntreeDishes(List<Dish> allDishes) {
        ArrayList<Dish> entreeDishes = new ArrayList<>();
        for (Dish dish : allDishes) {
            if (dish.getMealType().equals("entree")) {
                entreeDishes.add(dish);
            }
        }
        return entreeDishes;
    }

    private void addDishButton(Dish dish, LinearLayout container) {
        LinearLayout dishLayout = new LinearLayout(this);
        dishLayout.setOrientation(LinearLayout.HORIZONTAL);

        Button dishInfo = new Button(this);
        dishInfo.setText(dish.getName());
        dishInfo.setTextSize(18);

        dishInfo.setOnClickListener(v -> {
            Intent intent = new Intent(EntreeActivity.this, DishActivity.class);
            intent.putExtra("DishName", dish.getName());
            intent.putExtra("MealType", "entree");
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
