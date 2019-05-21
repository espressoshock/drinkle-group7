package com.espressoshock.drinkle.models;

public class Brand {
    private String name;
    private String description;

    public Brand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
