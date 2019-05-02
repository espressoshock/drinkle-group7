package com.espressoshock.drinkle.models;

public class Brand {
    private String name;
    private String description;
    private IngredientCategory productType;

    public Brand(String name, String description, IngredientCategory productType) {
        this.name = name;
        this.description = description;
        this.productType = productType;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public IngredientCategory getProductType() { return productType;
    }

    @Override
    public String toString() {
        return name;
    }
}
