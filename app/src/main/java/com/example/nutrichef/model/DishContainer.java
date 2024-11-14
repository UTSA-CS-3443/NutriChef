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

    public void startUp(){
        Dish appetizer1 = new Dish();
        appetizer1.setDishName("Mozzarella Sticks");
        appetizer1.setDishIngredients("Mozzarella Cheese, Breading, Buttermilk");
        appetizer1.setDishInstructions("Coat cheese in buttermilk, Coat with breading, Fry");
        appetizer1.setDishNutrients("5g Sodium, 2g Protein");
        appetizer1.setDishType("appetizer");
        Dish appetizer2 = new Dish();
        appetizer2.setDishName("Nachos");
        appetizer2.setDishIngredients("Tortilla chips, cheese, chicken");
        appetizer2.setDishInstructions("Coat chips with cheese, Coat chips with chicken");
        appetizer2.setDishNutrients("2g Sodium, 5g Protein");
        appetizer2.setDishType("appetizer");
        Dish appetizer3 = new Dish();
        appetizer3.setDishName("Fried pickles");
        appetizer3.setDishIngredients("Pickles, Buttermilk, Breading");
        appetizer3.setDishInstructions("Dip pickles in buttermilk, Coat with breading, Fry");
        appetizer3.setDishNutrients("6g Sodium, 1g Protein");
        appetizer3.setDishType("appetizer");
        Dish entree1 = new Dish();
        entree1.setDishName("Steak");
        entree1.setDishIngredients("Meat, Seasoning, Butter");
        entree1.setDishInstructions("Season the meat, Cook in a pan while basting with butter, Let rest");
        entree1.setDishNutrients("10g protein, 2g Sodium");
        entree1.setDishType("entree");
        Dish entree2 = new Dish();
        entree2.setDishName("Cheeseburger");
        entree2.setDishIngredients("Beef, Cheese, Bread");
        entree2.setDishInstructions("Cook meat in a patty shape, Place cheese on patty, Place patty on Bun");
        entree2.setDishNutrients("5g Sodium, 8g Protein");
        entree2.setDishType("entree");
        Dish entree3 = new Dish();
        entree3.setDishName("Ravioli");
        entree3.setDishIngredients("Flour, Eggs, Cheese");
        entree3.setDishInstructions("Make dough, Cut dough into proper shape, Place cheese in shapes, Cook");
        entree3.setDishNutrients("3g Sodium, 5g Protein");
        entree3.setDishType("entree");
        Dish dessert1 = new Dish();
        dessert1.setDishName("Cake");
        dessert1.setDishIngredients("Flour, Milk, Eggs, Cocoa Powder, Sugar");
        dessert1.setDishInstructions("Mix wet ingredients in bowl, Combine with Dry, Bake");
        dessert1.setDishNutrients("6g Sodium, 3g Protein, 6g Sugar");
        dessert1.setDishType("dessert");
        Dish dessert2 = new Dish();
        dessert2.setDishName("Ice Cream");
        dessert2.setDishIngredients("Heavy Cream, Milk, Sugar");
        dessert2.setDishInstructions("Combine all ingredients, Freeze");
        dessert2.setDishNutrients("3g Sodium, 2g Protein, 4g Sugar");
        dessert2.setDishType("dessert");
        Dish dessert3 = new Dish();
        dessert3.setDishName("Cookies");
        dessert3.setDishIngredients("Flour, Eggs, Butter, Milk, Chocolate Chips");
        dessert3.setDishInstructions("Combine all wet ingredients in a bowl, Add in Dry and Chocolate chips, Bake");
        dessert3.setDishNutrients("4g Sodium, 2g Protein, 4g Sugar");
        dessert3.setDishType("dessert");
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
                        writer.write("Ingredients: \n");
                        String[] ingredients = newDish.getDishIngredients().split(",");
                        for(String s : ingredients){
                            s = s.trim();
                            writer.write(s +"\n");
                        }
                        writer.write("\n");
                        break;
                    case 2:
                        writer.write("Instructions: \n");
                        String[] instructions = newDish.getDishInstructions().split(",");
                        for(String s : instructions){
                            s = s.trim();
                            writer.write(s +"\n");
                        }
                        writer.write("\n");
                        break;
                    case 3:
                        writer.write("Nutritional Information: \n");
                        String[] nutrition = newDish.getDishNutrients().split(",");
                        for(String s : nutrition){
                            s = s.trim();
                            writer.write(s +"\n");
                        }
                        writer.write("\n");
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

