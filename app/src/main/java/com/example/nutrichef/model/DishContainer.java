package com.example.nutrichef.model;

import static com.example.nutrichef.MainActivity.dishes;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * This class manages the storage and retrieval of {@link Dish} objects.
 * It provides functionality to add, remove, modify, and load dishes, as well as to initialize default dishes.
 * Dish information is stored in the device's local storage as text files.
 *
 * @author Benjamin Keilholz
 * @version 1.0
 *
 * UTSA CS 3443 - NutriChef - Fall 2024
 */
public class DishContainer {

    /**
     * The context of the application, used for file operations.
     */
    private Context context;

    /**
     * Constructs a {@code DishContainer} with the specified context.
     *
     * @param context the context used for accessing local storage
     */
    public DishContainer(Context context) {
        this.context = context;
    }

    /**
     * Initializes the application with a set of default dishes.
     * Default dishes are added to the dish list on startup.
     */
    public void startUp() {
        Dish appetizer1 = new Dish("appetizer", "Mozzarella Sticks", "Mozzarella Cheese, Breading, Buttermilk",
                "Coat cheese in buttermilk, Coat with breading, Fry", "5g Sodium, 2g Protein");
        Dish appetizer2 = new Dish("appetizer", "Nachos", "Tortilla chips, cheese, chicken",
                "Coat chips with cheese, Coat chips with chicken", "2g Sodium, 5g Protein");
        Dish appetizer3 = new Dish("appetizer", "Fried pickles", "Pickles, Buttermilk, Breading",
                "Dip pickles in buttermilk, Coat with breading, Fry", "6g Sodium, 1g Protein");
        Dish entree1 = new Dish("entree", "Steak", "Meat, Seasoning, Butter",
                "Season the meat, Cook in a pan while basting with butter, Let rest", "10g protein, 2g Sodium");
        Dish entree2 = new Dish("entree", "Cheeseburger", "Beef, Cheese, Bread",
                "Cook meat in a patty shape, Place cheese on patty, Place patty on Bun", "5g Sodium, 8g Protein");
        Dish entree3 = new Dish("entree", "Ravioli", "Flour, Eggs, Cheese",
                "Make dough, Cut dough into proper shape, Place cheese in shapes, Cook", "3g Sodium, 5g Protein");
        Dish dessert1 = new Dish("dessert", "Cake", "Flour, Milk, Eggs, Cocoa Powder, Sugar",
                "Mix wet ingredients in bowl, Combine with Dry, Bake", "6g Sodium, 3g Protein, 6g Sugar");
        Dish dessert2 = new Dish("dessert", "Ice Cream", "Heavy Cream, Milk, Sugar",
                "Combine all ingredients, Freeze", "3g Sodium, 2g Protein, 4g Sugar");
        Dish dessert3 = new Dish("dessert", "Cookies", "Flour, Eggs, Butter, Milk, Chocolate Chips",
                "Combine all wet ingredients in a bowl, Add in Dry and Chocolate chips, Bake", "4g Sodium, 2g Protein, 4g Sugar");

        addDish(appetizer1);
        addDish(appetizer2);
        addDish(appetizer3);
        addDish(entree1);
        addDish(entree2);
        addDish(entree3);
        addDish(dessert1);
        addDish(dessert2);
        addDish(dessert3);
    }

    /**
     * Loads all dishes from the local storage and updates the dish list.
     * Clears the current list of dishes and reads files representing dishes from the device's storage.
     */
    public void loadDishes() {
        File[] files;
        FileInputStream in;
        BufferedReader reader;

        dishes.clear(); // Empty the arraylist and start from scratch to prevent duplicates

        files = context.getFilesDir().listFiles();
        if (files != null && files.length > 1) { // Ensure there are files
            for (int i = 1; i < files.length; i++) { // Skip the first file (profileInstalled.txt)
                try {
                    // Open file and prepare for reading
                    in = context.openFileInput(files[i].getName());
                    reader = new BufferedReader(new InputStreamReader(in));

                    // Create a new Dish
                    Dish newDish = new Dish();
                    newDish.setDishName(files[i].getName().replace(".txt", ""));

                    String line;
                    boolean typeSet = false;
                    boolean readingIngredients = false;
                    boolean readingInstructions = false;

                    // Read file line by line
                    while ((line = reader.readLine()) != null) {
                        line = line.trim(); // Remove extra whitespace

                        // Extract dish type
                        if (!typeSet) {
                            if (line.contains(":")) {
                                newDish.setDishType(line.substring(0, line.indexOf(':')).trim());
                                typeSet = true;
                            }
                            continue;
                        }

                        // Detect "Ingredients:" section
                        if (line.equals("Ingredients:")) {
                            readingIngredients = true;
                            readingInstructions = false;
                            continue;
                        }

                        // Detect "Instructions:" section
                        if (line.equals("Instructions:")) {
                            readingIngredients = false;
                            readingInstructions = true;
                            continue;
                        }

                        // Detect "Nutritional Inforomation:" section
                        if (line.equals("Nutritional Information:")) {
                            readingIngredients = false;
                            readingInstructions = false;
                            continue;
                        }

                        // Populate the appropriate section based on current section
                        if (readingIngredients) {
                            newDish.setDishIngredients(newDish.getDishIngredients() + line + "\n");
                        } else if (readingInstructions) {
                            newDish.setDishInstructions(newDish.getDishInstructions() + line + "\n");
                        } else {
                            // Remaining lines after "Nutritional Inforomation:" are treated as nutritional info
                            newDish.setDishNutrients(newDish.getDishNutrients() + line + "\n");
                        }
                    }

                    // Add the completed Dish to the dishes list
                    dishes.add(newDish);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Adds a new {@link Dish} to the storage and updates the dish list.
     *
     * @param newDish the dish to add
     */
    public void addDish(Dish newDish) {
        try (FileOutputStream out = context.openFileOutput(newDish.getDishName().replace(' ', '-') + ".txt", Context.MODE_PRIVATE);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out))) {

            writer.write(newDish.getDishType() + ": " + newDish.getDishName() + "\n");
            writer.write("Ingredients: \n");
            for (String ingredient : newDish.getDishIngredients().split(",")) {
                writer.write(ingredient.trim() + "\n");
            }
            writer.write("Instructions: \n");
            for (String instruction : newDish.getDishInstructions().split(",")) {
                writer.write(instruction.trim() + "\n");
            }
            writer.write("Nutritional Information: \n");
            for (String nutrient : newDish.getDishNutrients().split(",")) {
                writer.write(nutrient.trim() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error creating the dish file.");
        }
        loadDishes();
    }

    /**
     * Removes a {@link Dish} from the storage and updates the dish list.
     *
     * @param dish the dish to remove
     */
    public void removeDish(Dish dish) {
        File[] files = context.getFilesDir().listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().equals(dish.getDishName().replace(' ', '-') + ".txt")) {
                    file.delete();
                }
            }
        }
        loadDishes();
    }

    /**
     * Modifies an existing {@link Dish} by overwriting its file with new data.
     *
     * @param modifiedDish the dish with updated data
     */
    public void modifyDish(Dish modifiedDish) {
        addDish(modifiedDish);
    }
}
