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

    public static ArrayList<Dish> dishes = new ArrayList<>();
    DishContainer dishContainer = new DishContainer(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Load the dish data from CSV files located in assets
        dishContainer.startUp();

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
                Intent intent = new Intent(MainActivity.this, HelperActivity.class);
                startActivity(intent);
            }
        });
    }

}


