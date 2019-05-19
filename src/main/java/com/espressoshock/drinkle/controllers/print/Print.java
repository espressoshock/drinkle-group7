package com.espressoshock.drinkle.controllers.print;


import com.espressoshock.drinkle.models.Ingredient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

import static com.espressoshock.drinkle.controllers.app.beverageBuilder.BeverageBuilder.*;

public class Print implements Initializable {
    @FXML
    AnchorPane printView;
    @FXML
    VBox ingredientsList;
    @FXML
    TextArea notesTextArea;
    @FXML
    Label costLabel, bvgName;
    @FXML
    ImageView glassImagePrint;

    private void addIngredientWidget2(Ingredient selected) {
        Label ingredientName = new Label();
        Label ingredientVolume = new Label();
        ProgressBar addedIngredientPercentBar = new ProgressBar();

        ingredientName.setLayoutX(16.0);
        ingredientVolume.setLayoutX(154.0);
        ingredientName.setText(selected.getName());
        ingredientVolume.setText(selected.getMagnitude() + "ml");
        addedIngredientPercentBar.setLayoutX(14.0);
        addedIngredientPercentBar.setLayoutY(29.0);
        addedIngredientPercentBar.setPrefWidth(163);
        addedIngredientPercentBar.setPrefHeight(8);
        addedIngredientPercentBar.setProgress((selected.getMagnitude() / (glass.getVolume() / 100.00)) / 100.00);//<-- 1.2 is glass volume/100
        System.out.println((selected.getMagnitude() / (glass.getVolume() / 100.00)) / 100.00);
        Group ingredient = new Group();
        ingredient.getChildren().addAll(ingredientName, ingredientVolume, addedIngredientPercentBar);
        ingredientsList.getChildren().add(ingredient);
    }

    @FXML
    private void populateVbox() {
        for (Ingredient a : addedIngredientsList2) {
            addIngredientWidget2(a);
        }
    }

    public void buttonPrint(ActionEvent event) {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        printDialog(printView, stage);

    }

    private void printDialog(Node subject, Stage window) {
        PrinterJob newPrint = PrinterJob.createPrinterJob();
        if (newPrint == null) {
            return;
        }

        boolean isAvailable = newPrint.showPrintDialog(window);

        if (isAvailable) {
            print(newPrint, subject);
        }
    }

    private void print(PrinterJob job, Node subject) {
        System.out.println(job.jobStatusProperty().asString());

        boolean jobDone = job.printPage(subject);

        if (jobDone) {
            job.endJob();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        populateVbox();
        notesTextArea.setText(bvg.getNotes());
        costLabel.setText(String.valueOf(bvg.getCost()) + " $");
        bvgName.setText(bvg.getName());
        Image img = new Image(glass.getImageUrl());
        glassImagePrint.setImage(img);
    }
}


