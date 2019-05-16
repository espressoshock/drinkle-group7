package com.espressoshock.drinkle.models;



public class Ingredient {

    //TODO: Remove
    private String description;


    private String name;
    private int alcoholPercentage;
    private int pricePerLiter;
    private BrandsEnum brand;
    private int magnituted;

    public Ingredient(String description, String name, int alcoholPercentage, int pricePerLiter, BrandsEnum brand, int magnituted) {
        this.description = description;
        this.name = name;
        this.alcoholPercentage = alcoholPercentage;
        this.pricePerLiter = pricePerLiter;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAlcoholPercentage() {
        return alcoholPercentage;
    }

    public void setAlcoholPercentage(int alcoholPercentage) {
        this.alcoholPercentage = alcoholPercentage;
    }

    public int getPricePerLiter() {
        return pricePerLiter;
    }

    public void setPricePerLiter(int pricePerLiter) {
        this.pricePerLiter = pricePerLiter;
    }

    public BrandsEnum getBrand() {
        return brand;
    }

    public void setBrand(BrandsEnum brand) {
        this.brand = brand;
    }

    public int getMagnituted() {
        return magnituted;
    }

    public void setMagnituted(int magnituted) {
        this.magnituted = magnituted;
    }
}
