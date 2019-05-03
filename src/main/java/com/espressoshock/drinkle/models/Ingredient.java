package com.espressoshock.drinkle.models;

import javafx.scene.image.Image;

import java.sql.Timestamp;
import java.util.List;

public class Ingredient{    //TODO  extends Blueprint{

    private String name;
    private String description;
    private String orderCode;
    private float abv;
    private String pictureURL;
    private IngredientCategory category;
    private Package aPackage;
    private Brand brand;
    private double price;
    private double alcohol;


    public Ingredient(String name, String description, IngredientCategory category, Brand brand, double price, double alcohol) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.alcohol = alcohol;
    }

/*    public Ingredient(String metadata, Timestamp ts, int blueprintId, List<Permission> permissions, AccessLevel accessLevel, Statistic statistic,
                      String name, String description, String orderCode, float abv, String pictureURL, IngredientCategory category, Package aPackage, Brand brand) {
        super(metadata, ts, blueprintId, permissions, accessLevel, statistic);
        this.name = name;
        this.description = description;
        this.orderCode = orderCode;
        this.abv = abv;
        this.pictureURL = pictureURL;
        this.category = category;
        this.aPackage = aPackage;
        this.brand = brand;
        }*/

    public double getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(double alcohol) {
        this.alcohol = alcohol;
    }

    public String getName() {
        return name;
    }

    public Brand getBrand() {
        return brand;
    }

    public String getDescription() {
        return description;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public float getAbv() {
        return abv;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public IngredientCategory getCategory() {
        return category;
    }

    public Package getaPackage() {return aPackage;}

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public void setAbv(float abv) {
        this.abv = abv;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public void setCategory(IngredientCategory category) {
        this.category = category;
    }

    public void setaPackage(Package aPackage) {this.aPackage = aPackage;}

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }
}
