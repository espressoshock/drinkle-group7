package com.espressoshock.drinkle.models;



public class Ingredient {

    //TODO: Remove
    private String description;


    private String name;
    private int alcoholPercentage;
    private int pricePerLiter;
    private IngredientCategory category;
    private BrandsEnum brand;
    private int magnituted;

    public Ingredient(String description, String name, int alcoholPercentage, int pricePerLiter,
        IngredientCategory category, BrandsEnum brand, int magnituted) {
        this.description = description;
        this.name = name;
        this.alcoholPercentage = alcoholPercentage;
        this.pricePerLiter = pricePerLiter;
        this.category = category;
        this.brand = brand;
        this.magnituted = magnituted;
    }


    public BrandsEnum getBrandsEnum() {
        return brand;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

}
