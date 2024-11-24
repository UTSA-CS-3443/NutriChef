/**
 * The DeleteActivity allows users to delete a dish by retrieving its name from the previous activity.
 * When the “Delete” button is clicked, it removes the dish from both the static list and the file,
 * then notifies the user with a toast message.
 * Afterward, it redirects the user to the appropriate meal type activity (entree, dessert, or appetizer)
 * and clears the activity stack to prevent memory leaks.
 */
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
        String mealType = getIntent().getStringExtra("MealType");

        // Set onClickListener for the delete button
        deleteButton.setOnClickListener(v -> {
            // Find the dish by name from the static list of dishes
            Dish dish = DishActivity.getDishByName(dishName);
            if (dish != null) {

                // If the dish exists, remove it from both the static list and the dish container
                dishes.remove(dish);
                DishContainer dishContainer = new DishContainer(DeleteActivity.this);
                dishContainer.removeDish(dish);  // Removes the dish from the file

                // Show a toast message to confirm deletion
                Toast.makeText(DeleteActivity.this, "Dish deleted!", Toast.LENGTH_SHORT).show();

                // Prepare the intent to redirect the user back to the corresponding activity
                Intent intent = new Intent();
                if (mealType.equals("entree")) {
                    intent = new Intent(DeleteActivity.this, EntreeActivity.class);
                }
                else if (mealType.equals("dessert")) {
                    intent = new Intent(DeleteActivity.this, DessertActivity.class);
                }
                else if (mealType.equals("appetizer")) {
                    intent = new Intent(DeleteActivity.this, AppetizerActivity.class);
                }

                // Set flags to clear the activity stack and prevent memory leaks
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // Close DeleteActivity after deletion
            } else {

                // Show a toast message if the dish is not found
                Toast.makeText(DeleteActivity.this, "Dish not found!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
