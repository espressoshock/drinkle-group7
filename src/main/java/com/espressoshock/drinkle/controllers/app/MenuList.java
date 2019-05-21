package com.espressoshock.drinkle.controllers.app;

import com.espressoshock.drinkle.viewLoader.EventDispatcherAdapter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuList extends EventDispatcherAdapter {
    /**
     * 3.1.4 Menu
     * The Menu class is a specialization of the Blueprint class and represents a list of different
     * beverages or cocktails served at a meal or event that typically shares a common theme (Fig.10).
     * <p>
     * *******************************Menu Attributes******************************************
     * private List<Beverage> beverages;
     * private String name;
     * private String theme;
     * private String pictureURL;
     * <p>
     * *******************************Menu Constructor*****************************************
     * public Menu(String metadata, Timestamp ts, int blueprintId, List<Permission> permissions, AccessLevel accessLevel, Statistic statistic, List<Beverage> beverages, String name, String theme, String pictureURL) {
     * <p>
     * super(metadata, ts, blueprintId, permissions, accessLevel, statistic);
     * this.beverages = beverages;
     * this.name = name;
     * this.theme = theme;
     * this.pictureURL = pictureURL;
     * }
     */

    @FXML
    public void openCreateMenuView() throws Exception {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/app/create-new-menu.fxml"));
        Scene newMenu = new Scene(root);
        primaryStage.setTitle("Drinkle - Create new Menu");
        primaryStage.setResizable(false);
        primaryStage.setScene(newMenu);
        primaryStage.show();
    }
}
