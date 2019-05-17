package com.espressoshock.drinkle.models;



public class Ingredient {

    private String name;
    private int alcoholPercentage;
    private int pricePerLiter;
    private BrandsEnum brand;
    private int magnitude;

    public Ingredient(String name, int alcoholPercentage, int pricePerLiter, BrandsEnum brand, int magnitude) {

        this.name = name;
        this.alcoholPercentage = alcoholPercentage;
        this.pricePerLiter = pricePerLiter;
        this.brand = brand;
        this.magnitude = magnitude;
    }

    public String getName() {
        return name;
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

    public int getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(int magnitude) {
        this.magnitude = magnitude;
    }
}
