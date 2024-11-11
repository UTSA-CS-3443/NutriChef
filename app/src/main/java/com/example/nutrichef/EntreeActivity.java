package com.example.nutrichef;

import static com.example.nutrichef.MainActivity.dishes;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class EntreeActivity extends AppCompatActivity {

    private LinearLayout dishView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entree);
        dishView = findViewById(R.id.buttonContainer);

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

    @Override
    protected void onResume() {
        super.onResume();
        loadEntreeDishes();
    }

    private void loadEntreeDishes() {
        // Clear the container to prevent duplicate entries
        dishView.removeAllViews();

        // Filter and display entree dishes
        ArrayList<Dish> entreeDishes = getEntreeDishes();
        for (Dish dish : entreeDishes) {
            addDishButton(dish, dishView);
        }
    }

    private ArrayList<Dish> getEntreeDishes() {
        ArrayList<Dish> entreeDishes = new ArrayList<>();

        for (Dish dish : dishes) {

            if ("entree".equals(dish.getDishType())) {
                entreeDishes.add(dish);


            }
        }

        return entreeDishes;
    }

    private void addDishButton(Dish dish, LinearLayout container) {
        LinearLayout dishLayout = new LinearLayout(this);
        dishLayout.setOrientation(LinearLayout.HORIZONTAL);

        Button dishInfo = new Button(this);
        dishInfo.setText(dish.getDishName());
        dishInfo.setTextSize(18);

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

    public static Dish getDishByName(String name) {
        for (Dish dish : dishes) {
            if (dish.getDishName().equals(name)) {
                return dish;
            }
        }
        return null;
    }
}
