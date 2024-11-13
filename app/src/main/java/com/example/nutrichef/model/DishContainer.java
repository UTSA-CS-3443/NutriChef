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


public class DishContainer {
    private Context context;
    public DishContainer(Context context){
        this.context = context;

    }

    //Empties the arrayList and refills it with all existing dish files
    public void loadDishes() {
        File[] files;
        FileInputStream in;
        BufferedReader reader;

        dishes.clear(); // Empty the arraylist and start from scratch

        files = context.getFilesDir().listFiles();
        if (files != null && files.length > 1) { // Ensure files is not null and has more than one file
            for (int i = 1; i < files.length; i++) { // Skip the first file (e.g., profileInstalled.txt)
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

                        // Detect "Nutrition Info:" section
                        if (line.equals("Nutrition Info:")) {
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
                            // Remaining lines after "Nutrition Info:" are treated as nutritional info
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



    //Takes in a dish and creates its file. Calls loadDishes() to refresh arrayList
    public void addDish(Dish newDish){
        FileOutputStream out;
        try {
            out = context.openFileOutput(newDish.getDishName().replace(' ', '-') + ".txt", Context.MODE_PRIVATE);

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            writer.write(newDish.getDishType() + ": " + newDish.getDishName() + "\n");
            for(int i = 1; i <= 3; i++){
                switch(i){
                    case 1:
                        writer.write("Ingredients: \n" + newDish.getDishIngredients() + "\n");
                        break;
                    case 2:
                        writer.write("Instructions: \n" + newDish.getDishInstructions() + "\n");
                        break;
                    case 3:
                        writer.write("Nutrition Info: \n" + newDish.getDishNutrients() + "\n");
                        break;
                }
            }
            writer.close();
        }catch(IOException e){System.out.print("COULDN'T MAKE THE FILE\n\n\n\n");}
        loadDishes();//Update list of dishes
    }

    //Takes in a dish to remove. Deletes its file. Calls loadDishes() to refresh arrayList
    public void removeDish(Dish dish){
        File[] files = context.getFilesDir().listFiles();

        if(files != null)//Make sure array of files is not empty
            for(File f : files){
                //If file name == dishName
                if(f.getName().equals(dish.getDishName().replace(' ', '-') + ".txt"))
                    //Delete file
                    f.delete();
            }
        loadDishes();//Update list of dishes
    }

    //Takes in a dish to modify and calls addDish. This method may end up being unnecessary other than clarity
    //But we will keep it for now
    public void modifyDish(Dish modifiedDish){
        addDish(modifiedDish);
    }

}

