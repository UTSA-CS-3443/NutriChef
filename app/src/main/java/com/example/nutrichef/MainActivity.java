package com.example.nutrichef;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author gar407
 * The MainActivity class is the main entry point for the application.
 * It dynamically generates buttons for each Airship in the Fleet and
 * handles click events to launch AirshipActivity with the selected Airship's registry.
 *
 * This class also provides a method to retrieve an Airship by its registry.
 *
 */
public class MainActivity extends AppCompatActivity {

    public static ArrayList<Dish> dishes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            // Load the fleet data from CSV files located in assets
            dishContainer.loadDishes(this);
        } catch (IOException e) {
            throw new RuntimeException("Error loading fleet data.", e);
        }

        Button startButton = findViewById(R.id.start_button);
        Button helpButton = findViewById(R.id.helpButton);

        // Set an OnClickListener to handle button clicks
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EntreeActivity.class);
                startActivity(intent);
            }
        });
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(intent);
            }
        });
    }

}


