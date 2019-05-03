package com.espressoshock.drinkle.controllers.wrappers;

public enum ViewPaths {
    AUTH_LOGIN("/fxml/auth/auth-login.fxml"), AUTH_REGISTRATION("/fxml/auth/auth-registration.fxml"),
    MAIN_DASHBOARD("/fxml/main/dashboard.fxml"), INGREDIENT_LIST("/fxml/main/ingredient-list.fxml"), BEVERAGE_LIST("/fxml/main/beverage-list.fxml"),
    MENU_LIST("/fxml/main/menu-list.fxml"), BEVERAGE_BUILDER("/fxml/main/beverage-builder.fxml");

    private final String path;

    ViewPaths(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
