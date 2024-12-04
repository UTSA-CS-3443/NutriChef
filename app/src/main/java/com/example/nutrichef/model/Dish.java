package com.example.nutrichef.model;

/**
 * Represents an individual dish in the NutriChef application.
 * This class encapsulates all the details about a dish, including its type, name,
 * ingredients, instructions, and nutrient information.
 *
 * @author Benjamin Keilholz
 * @version 1.0
 *
 * UTSA CS 3443 - NutriChef - Fall 2024
 */
public class Dish {

    // Instance variables
    /**
     * The type of the dish (e.g., appetizer, main course, dessert).
     */
    private String dishType;

    /**
     * The name of the dish.
     */
    private String dishName;

    /**
     * The ingredients required to prepare the dish.
     */
    private String dishIngredients;

    /**
     * The instructions for preparing the dish.
     */
    private String dishInstructions;

    /**
     * The nutrient information of the dish.
     */
    private String dishNutrients;

    /**
     * Constructs a {@code Dish} object with all the specified details.
     *
     * @param dishType the type of the dish (e.g., appetizer, main course, dessert)
     * @param dishName the name of the dish
     * @param dishIngredients the ingredients required to prepare the dish
     * @param dishInstructions the instructions for preparing the dish
     * @param dishNutrients the nutrient information of the dish
     */
    public Dish(String dishType, String dishName, String dishIngredients, String dishInstructions, String dishNutrients) {
        this.dishType = dishType;
        this.dishName = dishName;
        this.dishIngredients = dishIngredients;
        this.dishInstructions = dishInstructions;
        this.dishNutrients = dishNutrients;
    }

    /**
     * Constructs an empty {@code Dish} object with default values.
     * All fields are initialized to empty strings.
     */
    public Dish() {
        this.dishType = "";
        this.dishName = "";
        this.dishIngredients = "";
        this.dishInstructions = "";
        this.dishNutrients = "";
    }

    /**
     * Returns the name of the dish.
     *
     * @return the name of the dish
     */
    public String getDishName() {
        return dishName;
    }

    /**
     * Sets the name of the dish.
     *
     * @param dishName the name of the dish
     */
    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    /**
     * Returns the ingredients of the dish.
     *
     * @return the ingredients of the dish
     */
    public String getDishIngredients() {
        return dishIngredients;
    }

    /**
     * Sets the ingredients of the dish.
     *
     * @param dishIngredients the ingredients of the dish
     */
    public void setDishIngredients(String dishIngredients) {
        this.dishIngredients = dishIngredients;
    }

    /**
     * Returns the instructions for preparing the dish.
     *
     * @return the instructions for preparing the dish
     */
    public String getDishInstructions() {
        return dishInstructions;
    }

    /**
     * Sets the instructions for preparing the dish.
     *
     * @param dishInstructions the instructions for preparing the dish
     */
    public void setDishInstructions(String dishInstructions) {
        this.dishInstructions = dishInstructions;
    }

    /**
     * Returns the nutrient information of the dish.
     *
     * @return the nutrient information of the dish
     */
    public String getDishNutrients() {
        return dishNutrients;
    }

    /**
     * Sets the nutrient information of the dish.
     *
     * @param dishNutrients the nutrient information of the dish
     */
    public void setDishNutrients(String dishNutrients) {
        this.dishNutrients = dishNutrients;
    }

    /**
     * Returns the type of the dish.
     *
     * @return the type of the dish
     */
    public String getDishType() {
        return dishType;
    }

    /**
     * Sets the type of the dish.
     *
     * @param dishType the type of the dish (e.g., appetizer, main course, dessert)
     */
    public void setDishType(String dishType) {
        this.dishType = dishType;
    }
}
