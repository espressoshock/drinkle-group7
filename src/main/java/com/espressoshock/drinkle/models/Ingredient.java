package com.espressoshock.drinkle.models;

import java.sql.Timestamp;
import java.util.List;

public class Ingredient extends Blueprint {
    private String name;
    private String description;
    private String orderCode;
    private float abv;
    private String pictureURL;
    private IngredientCategory category;
    private Package aPackage;
    private Brand brand;

    public Ingredient(String metadata, Timestamp ts, int blueprintId, List<Permission> permissions, AccessLevel accessLevel, Statistic statistic, String name, String description, String orderCode, float abv, String pictureURL, IngredientCategory category, Package aPackage, Brand brand) {
        super(metadata, ts, blueprintId, permissions, accessLevel, statistic);
        this.name = name;
        this.description = description;
        this.orderCode = orderCode;
        this.abv = abv;
        this.pictureURL = pictureURL;
        this.category = category;
        this.aPackage = aPackage;
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public float getAbv() {
        return abv;
    }

    public void setAbv(float abv) {
        this.abv = abv;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public IngredientCategory getCategory() {
        return category;
    }

    public void setCategory(IngredientCategory category) {
        this.category = category;
    }

    public Package getaPackage() {
        return aPackage;
    }

    public void setaPackage(Package aPackage) {
        this.aPackage = aPackage;
    }
}
