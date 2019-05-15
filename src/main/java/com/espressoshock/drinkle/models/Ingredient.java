package com.espressoshock.drinkle.models;



public class Ingredient {

    private String name;
    private String description;
    private IngredientCategory category;
    private BrandsEnum brand;

    public BrandsEnum getBrandsEnum() {
        return brand;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public Ingredient(String name, String description,
        IngredientCategory category, BrandsEnum brand) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.brand = brand;
    }
}
