package com.espressoshock.drinkle.models;

import java.sql.Timestamp;
import java.util.List;

public class Beverage extends Blueprint {
    private String name;
    private String description;
    private Recipe recipe;
    private String pictureURL;
    private double pourCost;

    public Beverage(String metadata, Timestamp ts, int blueprintId, List<Permission> permissions, AccessLevel accessLevel, Statistic statistic, String name, String description, Recipe recipe, String pictureURL, double pourCost) {
        super(metadata, ts, blueprintId, permissions, accessLevel, statistic);
        this.name = name;
        this.description = description;
        this.recipe = recipe;
        this.pictureURL = pictureURL;
        this.pourCost = pourCost;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public double getPourCost() {
        return pourCost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public void setPourCost(double pourCost) {
        this.pourCost = pourCost;
    }
}
