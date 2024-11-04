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
        ArrayList<Dish> entreeDishes = new ArrayList();
        for (Dish dish : dishes) {
            if (dish.getMealType().equals("entree")) {
                entreeDishes.add(dish);
            }
        }

        // Set the airship name and registry in the view if the airship is found
        for (Dish dishes : entreeDishes) {
            LinearLayout dishLayout = new LinearLayout(this);
            dishLayout.setOrientation(LinearLayout.HORIZONTAL);

            // Create TextView to display wizard's name and rank
            Button dishInfo = new Button(this);
            dishInfo.setText(dishes.getName());
            dishInfo.setTextSize(18);

            dishInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(EntreeActivity.this, DishActivity.class);
                    intent.putExtra("DishName", dishes.getName());
                    intent.putExtra("MealType", "entree");
                    startActivity(intent);
                }
            });
            dishLayout.addView(dishInfo);
            dishLayout.setPadding(0, 0, 0, 100);

            // Add the wizard layout to the main wizard container
            dishContainer.addView(dishLayout);
        }
    }
    public static Dish getDishByName(String name) {
        for ( Dish dish  : dishes) {
            if (dish.getName().equals(name)) {
                return dish;
            }
        }
        return null;
    }
}
