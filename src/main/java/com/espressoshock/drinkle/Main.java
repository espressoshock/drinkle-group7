package com.espressoshock.drinkle;
import com.espressoshock.drinkle.appState.Current;
import com.espressoshock.drinkle.models.Account;
import com.espressoshock.drinkle.models.PrivateAccount;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
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
    public void start(Stage primaryStage) throws IOException, Exception {
        //RESOURCE LOADING //***********
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/wrappers/main-v-wrapper.fxml"));
        //WINDOW  //***********
        Scene mainScene = new Scene(root, windowWidth, windowHeight);
        mainScene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);


        primaryStage.setTitle("Drinkle");
        primaryStage.setResizable(false);
        primaryStage.setScene(mainScene);
        primaryStage.show();


        /*
        Example for getting, and setting beverages.
         */
        Current
            .environment
            .currentUser
            .getBeverages();

//        Current
////            .environment
////            .currentUser
////            .setBeverages();

        //date:
        String date = Current
            .environment
            .currentDate;
        //etc
        Current
            .environment
            .currentUser
            .getEmail();




       // shows current jdk used by the app itself.
        System.out.println(System.getProperties());

    }


}