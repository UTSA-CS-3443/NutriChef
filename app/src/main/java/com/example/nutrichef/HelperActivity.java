/**
 * The Helper activity consists of a TextView containing Html formatted
 * text that are instructions and a description for the application.
 * This is the screen where the user can reference to for navigation help.
 */

package com.example.nutrichef;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class HelperActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_helper);

        // Get the TextView from the layout
        TextView helperView = findViewById(R.id.helperView);

            // Html formatted text
            String helpText = "<br>" +
                    "<b>App Functionalities:</b><br>" +
                    "- <b>Select Recipes by Category</b>: Navigate through different categories (Appetizer, Entrée, Dessert) using the buttons at the bottom of the screen to explore various recipes.<br><br>" +
                    "- <b>Add New Dish</b>: Tap the '+' icon at the bottom of the screen. You will be prompted to enter the Dish Name, Ingredients, Instructions, and Nutrition Info.<br><br>" +
                    "- <b>Remove Dish</b>: To delete a dish, select it from your recipe list. On the selected page, click the 'Remove Dish' button in the bottom left corner. A confirmation prompt will appear, as this action is irreversible.<br><br>" +
                    "- <b>Modify Dish</b>: Choose the dish you wish to edit. On the selected page, click the 'Modify Dish' button in the bottom right corner. This allows you to update Ingredients, Instructions, or Nutrition Info. Confirm the changes when prompted.<br>";
            helperView.setText(Html.fromHtml(helpText));
    }
}
