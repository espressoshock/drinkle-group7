package com.espressoshock.drinkle.models;

public enum Action {
    SHAKE_WELL("Shake Well"), STRAIN("Strain"), SPRINKLE("Sprinkle");

    private final String displayName;

    Action(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
