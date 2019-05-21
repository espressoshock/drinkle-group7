package com.espressoshock.drinkle.viewLoader;

import com.espressoshock.drinkle.controllers.app.*;
import com.espressoshock.drinkle.controllers.auth.*;

public enum ViewMetadata {
    AUTH_LOGIN(AuthLogin.class, "/fxml/auth/auth-login.fxml"),
    AUTH_REGISTRATION(AuthRegistration.class, "/fxml/auth/auth-registration.fxml"),
    AUTH_REGISTRATION_COMPANY(AuthRegistration.class, "/fxml/auth/auth-registration-company.fxml"),
    APP_INGREDIENT_LIST(IngredientList.class, "/fxml/app/ingredient-list.fxml"),
    APP_BEVERAGE_LIST(BeverageList.class, "/fxml/app/beverage-list.fxml"),
    APP_MENU_LIST(MenuList.class, "/fxml/app/menu-list.fxml"),
    APP_BEVERAGE_BUILDER(BeverageBuilder.class, "/fxml/app/beverage-builder.fxml"),
    //APP_DASHBOARD(Dashboard.class, "/fxml/app/dashboard.fxml"),
    APP_DASHBOARD(BeverageBuilder.class, "/fxml/app/beverage-builder.fxml"),
    APP_TEAM_LIST(TeamList.class, "/fxml/app/team-list.fxml"),
    APP_SIDEBAR(Sidebar.class, "/fxml/app/sidebar.fxml");


    private final Class controllerClass;
    private final String resourcePath;

    ViewMetadata(Class controllerClass, String resourcePath) {
        this.controllerClass = controllerClass;
        this.resourcePath = resourcePath;
    }

    public Class getControllerClass() {
        return controllerClass;
    }

    public String getResourcePath() {
        return resourcePath;
    }
}
