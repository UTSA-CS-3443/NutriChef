/**
 * The MainActivity class is responsible for initializing the app and displaying the main screen.
 * It loads dish data from CSV files using the DishContainer class and provides buttons for navigating to different activities,
 * such as EntreeActivity and HelperActivity.
 * It also manages a static list of dishes used throughout the app.
 *
 *  * @author Mauricio Villegas
 *
 *  *  * UTSA CS 3443
 *  *  * NutriChef
 *  *  * Fall 2024
 */
package com.example.nutrichef;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.nutrichef.model.Dish;
import com.example.nutrichef.model.DishContainer;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Static list of dishes, accessible from other activities
    public static ArrayList<Dish> dishes = new ArrayList<>();
    // Instance of DishContainer to manage dish data.
    DishContainer dishContainer = new DishContainer(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load the dish data from CSV files located in assets
        dishContainer.startUp();

        // Find buttons from the layout
        Button startButton = findViewById(R.id.start_button);
        Button helpButton = findViewById(R.id.helpButton);

        // Set an OnClickListener to handle the start button click
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the EntreeActivity when the start button is clicked
                Intent intent = new Intent(MainActivity.this, EntreeActivity.class);
                startActivity(intent);
            }
        });

        // Set an OnClickListener to handle the help button click
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the HelperActivity when the help button is clicked
                Intent intent = new Intent(MainActivity.this, HelperActivity.class);
                startActivity(intent);
            }
        });
    }
}