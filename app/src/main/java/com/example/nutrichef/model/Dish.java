package com.example.nutrichef.model;

/**
 * This class handles all of the code pertaining to an individual dish. This mainly includes
 * housing all of the necessary variables along with the methods needed to access/manipulate those variables
 *
 * @author Benjamin Keilholz
 *
 * UTSA CS 3443
 * NutriChef
 * Fall 2024
 */

public class Dish {

    //Instance variables
    private String dishName, dishIngredients, dishInstructions, dishNutrients, dishType;

    /**
     * Constructor for all given variables
     * @param dishType
     * @param dishName
     * @param dishIngredients
     * @param dishInstructions
     * @param dishNutrients
     */
    public Dish(String dishType, String dishName, String dishIngredients, String dishInstructions, String dishNutrients) {
        this.dishType = dishType;
        this.dishName = dishName;
        this.dishIngredients = dishIngredients;
        this.dishInstructions = dishInstructions;
        this.dishNutrients = dishNutrients;
    }

    /**
     * Constructor for no given variables
     */
    public Dish(){
        this.dishType = "";
        this.dishName = "";
        this.dishIngredients = "";
        this.dishInstructions = "";
        this.dishNutrients = "";
    }

    //All getters and setters
    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getDishIngredients() {
        return dishIngredients;
    }

    public void setDishIngredients(String dishIngredients) {
        this.dishIngredients = dishIngredients;
    }

    public String getDishInstructions() {
        return dishInstructions;
    }

    public void setDishInstructions(String dishInstructions) {
        this.dishInstructions = dishInstructions;
    }

    public String getDishNutrients() {
        return dishNutrients;
    }

    public void setDishNutrients(String dishNutrients) {
        this.dishNutrients = dishNutrients;
    }

    public String getDishType() {
        return dishType;
    }

    public void setDishType(String dishType) {
        this.dishType = dishType;
    }
}