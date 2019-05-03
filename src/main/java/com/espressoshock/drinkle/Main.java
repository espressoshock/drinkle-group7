package com.espressoshock.drinkle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    private static final int windowWidth = 1000;
    private static final int windowHeight = 729;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ingredient-view.fxml"));

        Scene mainScene = new Scene(root, windowWidth, windowHeight);
        mainScene.setFill(Color.TRANSPARENT);
        //TODO****primaryStage.initStyle(StageStyle.TRANSPARENT);

        primaryStage.setTitle("Drinkle");
        primaryStage.setResizable(false);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }


}