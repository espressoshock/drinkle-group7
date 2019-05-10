package com.espressoshock.drinkle.controllers.app;

import com.espressoshock.drinkle.models.Ingredient;
import com.espressoshock.drinkle.progressIndicator.RingProgressIndicator;
import com.espressoshock.drinkle.viewLoader.EventDispatcherAdapter;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.*;


public class BeverageBuilder extends EventDispatcherAdapter implements Initializable {
    //-------------------------Test variables only!!!----------------

    Integer volumeSeparator = 0;
    Double progressSeparator = 0.0;
    String strDouble = null;
    String cost = "0.0";
    double costIncrease = 0.0;
    double slidervalueset = 0.0;
    Double volume = null;
    int index = 0;
    Random rand = new Random();
    //------------------------End of Test variables------------------
    /* -> to be implemented when ingridient list is ready
    ArrayList<Ingredient> choseIngredientsList = new ArrayList<>();
    ArrayList<Ingredient> addedIngredients = new ArrayList<>();
    */
    ArrayList<Ing> choseIngredientsList = new ArrayList<>();
    ObservableList<Ing> observableListChoseIngredient = FXCollections.observableArrayList(choseIngredientsList);
    Ing selected = null;
    RingProgressIndicator alcoholPercent = new RingProgressIndicator();
    RingProgressIndicator pourCostPercent = new RingProgressIndicator();
    @FXML
    Label lblChosenName, lblVolume, lblCost, lblTotalVolume;
    @FXML
    AnchorPane alcoholPercentCircle, pourCostCircle;
    @FXML
    VBox vBoxChosenIngredients, vBoxListOfIngredients;
    @FXML
    Slider slider;
    @FXML
    ProgressBar progressGlass;

    private void sliderProgressChange(){
        slider.valueProperty().addListener((arg0, arg1, arg2) -> {
            try {
                strDouble = String.format("%.2f", slider.getValue()-slidervalueset);
                volume = Double.parseDouble(String.format("%.2f", slider.getValue())) * 100;
                cost = String.format("%.2f", costIncrease + (Double.parseDouble(strDouble)*selected.getPrice()/10));
                progressGlass.setProgress(Double.parseDouble(String.format("%.2f", slider.getValue())));
                lblVolume.setText(String.valueOf(volume.intValue()));
                lblCost.setText(cost);
            } catch(NumberFormatException ex) {
                //throws exception only on MacOS.
                System.out.println(ex);
            }
        });


    }

public void choseIngredient(Event event){
    Label lbl = (Label) event.getSource();
    selected =(Ing)lbl.getUserData();
    lblChosenName.setText(lbl.getText());
}
public void choseIngredientListElement(Ing object){
    Ing obj = object;
    Label choseIngredientName = new Label();
    Label choseIngredientPrice = new Label();
    choseIngredientName.setText(obj.getName());
    choseIngredientName.setOnMouseClicked((Event event) -> {choseIngredient(event);});
    choseIngredientName.setCursor(Cursor.HAND);
    choseIngredientPrice.setText(obj.getPrice() + " $/l");
    choseIngredientPrice.setLayoutX(210.0);
    Group choseIngredient = new Group();
    choseIngredient.getChildren().add(choseIngredientName);
    choseIngredient.getChildren().add(choseIngredientPrice);
    choseIngredientName.setUserData(obj);
    vBoxListOfIngredients.getChildren().add(choseIngredient);
}
    public void dummyIngredientAddToList(){
        dummyIngredientCreate();
        for (Ing a: choseIngredientsList){
            choseIngredientListElement(a);
        }
    }
public void dummyIngredientCreate(){//for Test Purpose Only!!!!
                                       // To be replaced with observable list of ingredient objects

    String [] names = new String[]{"Juice","Vodka","Whiskey","Gin","Beer","Brandy","Some other ingredient","Water"};
    for (int i = 0; i<60; i++){
        DecimalFormat df2 = new DecimalFormat("#.##");
        double rangeMin = 10.0;
        double rangeMax = 100.0;
        double randomValue = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
        Ing ingred = new Ing(names[rand.nextInt(8)],rand.nextInt(100),Double.valueOf(df2.format(randomValue)));
        choseIngredientsList.add(ingred);
    }
}



public void addIngredientWidget(){
        // Test purposes only
    costIncrease = Double.parseDouble(cost);
    slidervalueset = slider.getValue();
    double diff = slider.getValue() - slider.getMin();
    index = 1;
    Integer setVolume = volume.intValue() - volumeSeparator;
    Double setProgress = progressGlass.getProgress()-progressSeparator;
    Label ingredientName = new Label();
    Label ingredientVolume = new Label();
    ProgressBar addedIngredientPercentBar = new ProgressBar();
    Button overlay = new Button();
    ContextMenu removeMenu = new ContextMenu();
    MenuItem item1 = new MenuItem("Remove");

    removeMenu.getItems().add(item1);
    overlay.setText("");
    overlay.setStyle("-fx-background-color: transparent");
    overlay.setLayoutX(14.0);
    overlay.setPrefHeight(36.0);
    overlay.setPrefWidth(204.0);

    ingredientName.setText(lblChosenName.getText());
    ingredientName.setLayoutX(16.0);
    ingredientVolume.setText(setVolume+"ml");
    ingredientVolume.setLayoutX(195.0);
    addedIngredientPercentBar.setLayoutX(14.0);
    addedIngredientPercentBar.setLayoutY(29.0);
    addedIngredientPercentBar.setProgress(setProgress);
    addedIngredientPercentBar.setPrefWidth(204);
    addedIngredientPercentBar.setPrefHeight(8);

    Group ingredient = new Group();
    ingredient.getChildren().addAll(ingredientName, ingredientVolume, addedIngredientPercentBar, overlay);
    addedIng added = new addedIng(lblChosenName.getText(),setVolume,setProgress);
    overlay.setContextMenu(removeMenu);
    item1.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {

            slider.setMin(diff);
            slider.setValue(slider.getMin()-setProgress);
            volumeSeparator = volumeSeparator - added.getVolume();
            progressSeparator = progressSeparator-added.getProgressBar();
            vBoxChosenIngredients.getChildren().remove(ingredient);

            /////////////////////////////

        }
    });
    vBoxChosenIngredients.getChildren().add(ingredient);
    volumeSeparator = volume.intValue();
    progressSeparator = progressGlass.getProgress();
    slider.setMin(slider.getValue());
    //cost = String.format("%.2f", Double.parseDouble(cost)+selected.getPrice()/10);
    index =+1;


}
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    alcoholPercent.setProgress(17);
    pourCostPercent.setProgress(36);
    alcoholPercentCircle.getChildren().add(alcoholPercent);
    pourCostCircle.getChildren().add(pourCostPercent);
    dummyIngredientAddToList();
    sliderProgressChange();
        lblTotalVolume.setText("100");


    }
}
////////////////////class to test view -> to be deleted when Ingredients are available
 class Ing{
    private String name;
    private Integer alcoholPercentage;
    private Double price;

    public Ing(String name, Integer alcoholPercentage, Double price) {
        this.name = name;
        this.alcoholPercentage = alcoholPercentage;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Integer getAlcoholPercentage() {
        return alcoholPercentage;
    }

    public Double getPrice() {
        return price;
    }
}
class addedIng{
    private String name;
    private Integer Volume;
    private Double progressBar;

    public addedIng(String name, Integer volume, Double progressBar) {
        this.name = name;
        Volume = volume;
        this.progressBar = progressBar;
    }

    public String getName() {
        return name;
    }

    public Integer getVolume() {
        return Volume;
    }

    public Double getProgressBar() {
        return progressBar;
    }
}
