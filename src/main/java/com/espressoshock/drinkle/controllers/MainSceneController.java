package com.espressoshock.drinkle.controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable {
    @FXML
    Slider slider;
    @FXML
    ProgressBar progressGlass;
    @FXML
    Label lblVol, lblCost, lblHeader, lblIngredient;
    @FXML
    Tab tab1,tab2;
    @FXML
    Pane paneTab1, titleBar, paneRegion;
    @FXML
    Rectangle rectangle;

    private double initialX,initialY;
    public void choseIngredient(Event event) {
        Label lbl = (Label) event.getSource();
        String s = lbl.getText();
        System.out.println(s);
        lblIngredient.setText(lbl.getText());
    }

    public void setText(ActionEvent event){
    Button btn = (Button) event.getSource();
    lblHeader.setText(btn.getText()+" button pressed!");
    }
    private void sliderProgressChange(){
        slider.valueProperty().addListener((arg0, arg1, arg2) -> {
            NumberFormat numFormat = NumberFormat.getInstance();
            numFormat.setMaximumFractionDigits(2);
            numFormat.setGroupingUsed(false);
            double sliderValue = slider.getValue();
            sliderValue = Double.valueOf(numFormat.format(sliderValue));
            Double dbl2 = sliderValue*100;
            Double dbl3 = dbl2 * 0.0315;
            progressGlass.setProgress(sliderValue);

            lblVol.setText(String.valueOf(dbl2.intValue()));
            lblCost.setText(numFormat.format(dbl3)+"$");

        });

    }
    private void setGraphic(){
        tab1.setGraphic(new ImageView("images/editorIcon.png"));
        tab2.setGraphic(new ImageView("images/drinkIcon.png"));
    }
    private void addDraggableNode(final Node node) {

        node.setOnMousePressed(me -> {
            if (me.getButton() != MouseButton.MIDDLE) {
                initialX = me.getSceneX();
                initialY = me.getSceneY();
            }
        });

        node.setOnMouseDragged(me -> {
            if (me.getButton() != MouseButton.MIDDLE) {
                node.getScene().getWindow().setX(me.getScreenX() - initialX);
                node.getScene().getWindow().setY(me.getScreenY() - initialY);
            }
        });
    }
    @FXML
    private void exitProgramAction() {
        System.exit(0);
    }
    @FXML
    public void minimizeProgramAction(Event minimizeProgramEvent) {
        Stage stage = (Stage)((ImageView)minimizeProgramEvent.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
    private static void clipChildren(Region region, double arc) {

        final Rectangle outputClip = new Rectangle();
        outputClip.setArcWidth(arc);
        outputClip.setArcHeight(arc);
        region.setClip(outputClip);

        region.layoutBoundsProperty().addListener((ov, oldValue, newValue) -> {
            outputClip.setWidth(newValue.getWidth());
            outputClip.setHeight(newValue.getHeight());
        });
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sliderProgressChange();
        setGraphic();
        addDraggableNode(rectangle);
        clipChildren(paneRegion,30);
    }
}