package com.espressoshock.drinkle.models;

public enum BeverageCategory implements ICategory {
    //***** MORE TO COME
    NON_ALCOHOLIC("Alcohol-free"),
    CLASSIC("Contemporary Classic"),
    HOT("Hot drinks"), FROZEN("Frozen drinks"), LONG("Long drinks"),
    SHORT("Short drinks"), SHOT("Shot drinks"), APERITIVE("Aperitive drinks"), DIGESTIVE("Digestive drinks"),
    ANY_TIME("Any time drinks");

    private final String displayName;

    BeverageCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return displayName;
    }
}
