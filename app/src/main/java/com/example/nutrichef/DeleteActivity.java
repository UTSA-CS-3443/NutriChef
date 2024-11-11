package com.example.nutrichef;

import static com.example.nutrichef.MainActivity.dishes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nutrichef.model.Dish;
import com.example.nutrichef.model.DishContainer;

public class DeleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_dish);

        // Retrieve dish name passed from the previous activity
        String dishName = getIntent().getStringExtra("DishName");
        Button deleteButton = findViewById(R.id.deleteButton);

        deleteButton.setOnClickListener(v -> {
            Dish dish = DishActivity.getDishByName(dishName);
            if (dish != null) {
                // Remove from static list and from the file
                dishes.remove(dish);
                DishContainer dishContainer = new DishContainer(DeleteActivity.this);
                dishContainer.removeDish(dish);

                // Notify user and navigate back to EntreeActivity
                Toast.makeText(DeleteActivity.this, "Dish deleted!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DeleteActivity.this, EntreeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK); // Clear stack to prevent memory leaks
                startActivity(intent);
                finish(); // Close DeleteActivity after deletion
            } else {
                Toast.makeText(DeleteActivity.this, "Dish not found!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
