package com.example.nutrichef;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class HelperActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_helper);

        // Get the TextView from the layout
        TextView helperView = findViewById(R.id.helperView);


        String helpText = "Usage Instructions:\n\n" +
                "App Functionalities:\n" +
                "- **Select Recipes by Category**: Navigate through different categories (Appetizer, Entrée, Dessert) using the buttons at the bottom of the screen to explore various recipes.\n" +
                "\n- **Add New Dish**: Tap the '+' icon at the bottom of the screen. You will be prompted to enter the Dish Name, Ingredients, Instructions, and Nutrition Info.\n" +
                "\n- **Remove Dish**: To delete a dish, select it from your recipe list. On the selected page, click the 'Remove Dish' button in the bottom left corner. A confirmation prompt will appear, as this action is irreversible.\n" +
                "\n- **Modify Dish**: Choose the dish you wish to edit. On the selected page, click the 'Modify Dish' button in the bottom right corner. This allows you to update Ingredients, Instructions, or Nutrition Info. Confirm the changes when prompted.\n";
        helperView.setText(helpText);
    }
}
