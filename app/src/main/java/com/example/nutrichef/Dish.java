package com.example.nutrichef;



public class Dish {

    private String dishName, dishIngredients, dishInstructions, dishNutrients, dishType;

    public Dish(String dishType, String dishName, String dishIngredients, String dishInstructions, String dishNutrients) {
        this.dishType = dishType;
        this.dishName = dishName;
        this.dishIngredients = dishIngredients;
        this.dishInstructions = dishInstructions;
        this.dishNutrients = dishNutrients;
    }
    public Dish(){
        this.dishType = "";
        this.dishName = "";
        this.dishIngredients = "";
        this.dishInstructions = "";
        this.dishNutrients = "";
    }

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