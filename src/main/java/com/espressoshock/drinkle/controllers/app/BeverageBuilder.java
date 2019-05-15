package com.espressoshock.drinkle.controllers.app;

import com.espressoshock.drinkle.progressIndicator.RingProgressIndicator;
import com.espressoshock.drinkle.viewLoader.EventDispatcherAdapter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;


public class BeverageBuilder extends EventDispatcherAdapter implements Initializable {
    //-------------------------Test variables only!!!----------------

    Integer volumeSeparator = 0; // Value has been set to current slider value when ingredient is added
    Double progressSeparator = 0.0; // Value has been set to current progress bar value when ingredient is added
    String sliderDoubleValueAsString = null;
    Integer totalAlcoholPercentage = 0;
    String cost = "0.0"; //initial cost label text
    // double costIncrease = 0.0; <-- not sure what i used this for
    double slidervalueset = 0.0;
    Double glassVolume = null;
    Double volume = null;
    int index = 0;
    Random rand = new Random();
    DecimalFormat df = new DecimalFormat("#.##");
    boolean sliderIsDisabled= false;

    //------------------------End of Test variables------------------
    /* -> to be implemented when ingridient list is ready
    ArrayList<Ingredient> choseIngredientsList = new ArrayList<>();
    ArrayList<Ingredient> addedIngredientsList = new ArrayList<>();
    */
    ArrayList<Ing> choseIngredientsList = new ArrayList<>();
    ObservableList<Ing> observableListChoseIngredient = FXCollections.observableArrayList(choseIngredientsList);
    ArrayList<Glassware> choseGlasswareList = new ArrayList<>();
    ObservableList<Glassware> observableListChoseGlassware = FXCollections.observableArrayList(choseGlasswareList);
    ArrayList<Garnish> choseGarnishList = new ArrayList<>();
    ArrayList<IceType> choseIceTypeList = new ArrayList<>();
    ArrayList<Ing> addedIngredientsList = new ArrayList<>();

    Ing selected = null;// selected object of ingredient
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
    @FXML
    Button btnAddIngredient;


    public int countVolume() {
        int totalVolume = 0;
        for (Ing a : addedIngredientsList) {
            totalVolume = totalVolume + a.getVolumeMagnitude();

        }
        return totalVolume;
    }

    public int countPercentage() {
        int totalVolume = 0;
        int alcoholVolume = 0;
        int totalAlcohol = 0;
        for (Ing a : addedIngredientsList) {
            totalVolume = totalVolume + a.getVolumeMagnitude();
            alcoholVolume = alcoholVolume + (a.getVolumeMagnitude() * a.getAlcoholPercentage() / 100);
        }
        totalAlcohol = (alcoholVolume * 100) / totalVolume;
        return totalAlcohol;
    }

    public void openPrintView() throws Exception {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/app/print-layout.fxml"));
        Scene print = new Scene(root);
        primaryStage.setTitle("Drinkle-Print to PDF");
        primaryStage.setResizable(false);
        primaryStage.setScene(print);
        primaryStage.show();
    }

    public void findSelected() {
        Ing sel = null;
        for (Ing e : choseIngredientsList) {
            if (e == selected) {
                sel = e;
            }

        }
        choseIngredientsList.remove(sel);
    }

    private void sliderProgressChange() {
        if (selected == null) {
            slider.setDisable(true);
            sliderIsDisabled = true;
        } else {
            slider.valueProperty().addListener((arg0, arg1, arg2) -> {
                try {
                    sliderDoubleValueAsString = String.format("%.2f", slider.getValue() - slidervalueset);
                    volume = Double.parseDouble(String.format("%.2f", slider.getValue()));
                    cost = String.format("%.2f", Double.parseDouble(sliderDoubleValueAsString) * selected.getPrice() / 1000);
                    progressGlass.setProgress(Double.parseDouble(String.format("%.2f", slider.getValue() / (glassVolume / 100) / 100)));
                    lblVolume.setText(String.valueOf(volume.intValue()));
                    lblCost.setText(cost);
                    if (slider.getValue() == slider.getMin()) {
                        btnAddIngredient.setDisable(true);
                    }else {
                        btnAddIngredient.setDisable(false);
                    }
                } catch (NumberFormatException ex) {
                    //throws exception only on MacOS.
                    System.out.println(ex);
                }
            });

        }
    }
    public void disbleAdd(){
        slider.valueProperty().addListener((arg0, arg1, arg2) -> {
            if (slider.getValue() == slider.getMin()) {
                btnAddIngredient.setDisable(true);
            }
        });
        if (selected == null){
            btnAddIngredient.setDisable(true);
        }
    }
    public void choseIngredient(Event event) {
        slider.setDisable(false);
        sliderIsDisabled=false;
        Label lbl = (Label) event.getSource();
        selected = (Ing) lbl.getUserData();
        lblChosenName.setText(lbl.getText() + " " + selected.getAlcoholPercentage());
        sliderProgressChange();
        disbleAdd();
    }

    //------------Creating labels to be represented in the ingredient list------
    public void choseIngredientListElement(Ing object) {
        Ing obj = object;

        Label choseIngredientName = new Label();
        Label choseIngredientPrice = new Label();
        Tooltip percentage = new Tooltip("Alcohol: " + obj.getAlcoholPercentage() + "%");
        choseIngredientName.setText(obj.getName());
        choseIngredientName.setOnMouseClicked((Event event) -> {
            choseIngredient(event);
        });
        choseIngredientName.setCursor(Cursor.HAND);
        choseIngredientName.setTooltip(percentage);
        choseIngredientPrice.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        choseIngredientPrice.setText(String.format("%.2f", obj.getPrice()) + " \u20ac" + "/l");
        choseIngredientPrice.setLayoutX(205.0);

        Group choseIngredient = new Group();
        choseIngredient.getChildren().add(choseIngredientName);
        choseIngredient.getChildren().add(choseIngredientPrice);
        choseIngredientName.setUserData(obj);
        vBoxListOfIngredients.getChildren().add(choseIngredient);
    }

    public void choseGlasswareListElement(Glassware object) {
        Glassware obj = object;

        Label choseGlasswareName = new Label();
        Label choseGlasswareVolume = new Label();
        choseGlasswareName.setText(obj.getName());
        choseGlasswareName.setOnMouseClicked((Event event) -> {

        });
        choseGlasswareName.setCursor(Cursor.HAND);
        choseGlasswareVolume.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        choseGlasswareVolume.setText(obj.getVolume().toString() + " ml");
        choseGlasswareVolume.setLayoutX(205.0);

        Group choseIngredient = new Group();
        choseIngredient.getChildren().add(choseGlasswareName);
        choseIngredient.getChildren().add(choseGlasswareVolume);
        choseGlasswareName.setUserData(obj);
        vBoxListOfIngredients.getChildren().add(choseIngredient);
    }

    public void choseGarnishListElement(Garnish object) {
        Garnish obj = object;

        Label choseGarnishName = new Label();
        choseGarnishName.setText(obj.getName());
        choseGarnishName.setOnMouseClicked((Event event) -> {

        });
        choseGarnishName.setCursor(Cursor.HAND);
        Group choseIngredient = new Group();
        choseIngredient.getChildren().add(choseGarnishName);
        choseGarnishName.setUserData(obj);
        vBoxListOfIngredients.getChildren().add(choseIngredient);
    }

    public void choseIceTypeListElement(IceType object) {
        IceType obj = object;

        Label choseIceTypeName = new Label();
        choseIceTypeName.setText(obj.getName());
        choseIceTypeName.setOnMouseClicked((Event event) -> {

        });
        choseIceTypeName.setCursor(Cursor.HAND);
        Group choseIngredient = new Group();
        choseIngredient.getChildren().add(choseIceTypeName);
        choseIceTypeName.setUserData(obj);
        vBoxListOfIngredients.getChildren().add(choseIngredient);
    }
    //------------Adding from array list to vBox (display)------

    public void dummyIngredientAddToList() {
        vBoxListOfIngredients.getChildren().clear();
        for (Ing a : choseIngredientsList) {
            choseIngredientListElement(a);
        }
    }

    public void dummyGlasswareAddToList() {
        vBoxListOfIngredients.getChildren().clear();
        for (Glassware a : choseGlasswareList) {
            choseGlasswareListElement(a);
        }
    }

    public void dummyGarnishAddToList() {
        vBoxListOfIngredients.getChildren().clear();
        for (Garnish a : choseGarnishList) {
            choseGarnishListElement(a);
        }
    }

    public void dummyIceTypeAddToList() {
        vBoxListOfIngredients.getChildren().clear();
        for (IceType a : choseIceTypeList) {
            choseIceTypeListElement(a);
        }
    }

    //-----------Creating objects-------------------------------
    public void dummyIngredientCreate() {//for Test Purpose Only!!!!
        // To be replaced with observable list of ingredient objects

        String[] names = new String[]{"Juice", "Vodka", "Whiskey", "Gin", "Beer", "Brandy", "Some other ingredient", "Water"};
        for (String a : names) {
            DecimalFormat df2 = new DecimalFormat("#.##");
            double rangeMin = 10.0;
            double rangeMax = 100.0;
            double randomValue = rangeMin + (rangeMax - rangeMin) * rand.nextDouble();
            Ing ingred = new Ing(a, rand.nextInt(100), Double.valueOf(df2.format(randomValue)));
            choseIngredientsList.add(ingred);
        }
    }

    public void dummyGlasswareCreate() {//for Test Purpose Only!!!!
        // To be replaced with observable list of Glassware objects

        Glassware shot = new Glassware("Shot", 44, "asd");
        Glassware highball = new Glassware("Highball", 270, "asd");
        Glassware margarita = new Glassware("Margarita", 350, "asd");
        Glassware martini = new Glassware("Martini", 250, "asd");

        choseGlasswareList.add(shot);
        choseGlasswareList.add(highball);
        choseGlasswareList.add(margarita);
        choseGlasswareList.add(martini);
    }

    public void addIngredientTOLIST(){
       for (Ing a : addedIngredientsList){

       }
    }
    public void addIngredientWidget() {

        if (selected != null) {

            // Test purposes only
            btnAddIngredient.setDisable(true);
            //-------------------Setting new min values----------------
            double diff = slider.getValue() - slider.getMin(); // slider value difference Current value - min value
            Integer setVolume = volume.intValue() - volumeSeparator; // available volume after adding previous ingredient
            Double setProgress = progressGlass.getProgress() - progressSeparator; // Progress bar in a glass/available volume
            //-------------------Creating components----------------
            Label ingredientName = new Label();
            Label ingredientVolume = new Label();
            ProgressBar addedIngredientPercentBar = new ProgressBar();
            Button overlay = new Button();
            ContextMenu removeMenu = new ContextMenu();
            MenuItem removeItem = new MenuItem("Remove Ingredient");
            //----------Adding functionality to components-----------
            overlay.setText("");
            //overlay.setStyle("-fx-background-color: transparent");
            overlay.setLayoutX(12.0);
            overlay.setPrefHeight(36.0);
            overlay.setPrefWidth(210.0);
            removeMenu.getItems().add(removeItem);
            //-------------------------------
            ingredientName.setText(selected.getName());
            ingredientName.setLayoutX(16.0);
            ingredientVolume.setText(setVolume + "ml");
            ingredientVolume.setLayoutX(195.0);
            addedIngredientPercentBar.setLayoutX(14.0);
            addedIngredientPercentBar.setLayoutY(29.0);
            addedIngredientPercentBar.setProgress(setProgress);
            addedIngredientPercentBar.setPrefWidth(204);
            addedIngredientPercentBar.setPrefHeight(8);
            //--------------------------------
            Group ingredient = new Group();
            ingredient.getChildren().addAll(ingredientName, ingredientVolume, addedIngredientPercentBar, overlay);
            addedIng added = new addedIng(lblChosenName.getText(), setVolume, setProgress);
            selected.setVolumeMagnitude(setVolume);
            addedIngredientsList.add(selected);
            overlay.setContextMenu(removeMenu);
            overlay.setUserData(selected);
            overlay.setOnMouseClicked(event -> {
                slider.setDisable(false);
                sliderIsDisabled=false;
                selected = (Ing) overlay.getUserData();
                lblChosenName.setText(selected.getName());
            });

            removeItem.setOnAction(new EventHandler<ActionEvent>() {// removig a widget
                @Override
                public void handle(ActionEvent event) {
                    vBoxChosenIngredients.getChildren().remove(ingredient);
                    addedIngredientsList.remove(selected);
                    slider.setMin(countVolume());// adding back to volume removed ingredient value
                    slider.setValue(slider.getMin() - setProgress);// adding back to slider removed ingredient value
                    volumeSeparator = volumeSeparator - added.getVolume(); // adding to volume and progress separator
                    progressSeparator = progressSeparator - added.getProgressBar();
                    choseIngredientsList.add(selected);
                    dummyIngredientAddToList();
                    selected = null;
                    lblChosenName.setText("Null");


                }
            });
            vBoxChosenIngredients.getChildren().add(ingredient);
            volumeSeparator = volume.intValue();
            progressSeparator = progressGlass.getProgress();
            slider.setMin(countVolume());
            alcoholPercent.setProgress(countPercentage());
            findSelected();
            dummyIngredientAddToList();
            selected = null;
            slider.setDisable(true);
            sliderIsDisabled = true;
            lblChosenName.setText("null");

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please chose an ingredient");
            alert.showAndWait();
        }

    }

    public int alcoholPercentage(Ing ingredient, int Volume) {
        int alcoholInMililiters = (ingredient.getAlcoholPercentage() * Volume) / 100;
        return alcoholInMililiters;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alcoholPercent.setProgress(totalAlcoholPercentage);  //for visual presentation only
        pourCostPercent.setProgress(36);
        alcoholPercentCircle.getChildren().add(alcoholPercent);
        pourCostCircle.getChildren().add(pourCostPercent);
        //dummyIngredientAddToList();// create generate and add to list mock ingredients
        dummyGlasswareCreate();
        dummyIngredientCreate();
        sliderProgressChange();
        lblTotalVolume.setText("100");
        glassVolume = 120.0;
        slider.setMax(120.0);
        disbleAdd();
    }


}


////////////////////simplified classes to test view -> to be deleted when Ingredients are available from database
class Ing {
    private String name;
    private Integer alcoholPercentage;
    private Double price;
    private int volumeMagnitude;

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

    public int getVolumeMagnitude() {
        return volumeMagnitude;
    }

    public void setVolumeMagnitude(int volumeMagnitude) {
        this.volumeMagnitude = volumeMagnitude;
    }
}

class addedIng {
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

class Glassware {

    private String name;
    private Integer volume;
    private String imageUrl;

    public Glassware(String name, Integer volume, String imageUrl) {
        this.name = name;
        this.volume = volume;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public Integer getVolume() {
        return volume;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

class Garnish {
    String name;

    public Garnish(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class IceType {
    String name;

    public IceType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
