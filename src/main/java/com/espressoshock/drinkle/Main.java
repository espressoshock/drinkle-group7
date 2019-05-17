package com.espressoshock.drinkle;
import com.espressoshock.drinkle.databaseLayer.ConnectionLayer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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


    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;



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





        // example: Retrieve Private Acc.

        try {
            connection = ConnectionLayer.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM drinkleg7.private_account");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("email");
                System.out.printf("id: %d, name:%s, email: %s\n", id, name, address);
            }

        } catch (SQLException ex) {
            System.out.println("Exception: ");
            System.out.println(ex);
        }

        //shows current jdk used by the app itself.
        System.out.println(System.getProperties());

    }


}