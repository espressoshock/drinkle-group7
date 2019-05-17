package com.espressoshock.drinkle.controllers.app;

import com.espressoshock.drinkle.progressIndicator.RingProgressIndicator;
import com.espressoshock.drinkle.viewLoader.EventDispatcherAdapter;
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

    private Integer volumeSeparator = 0; // Value has been set to current slider value when ingredient is added
    private Double progressSeparator = 0.0; // Value has been set to current progress bar value when ingredient is added
    private String sliderDoubleValueAsString = null;
    private String cost = "0.0"; //initial cost label text
    // double costIncrease = 0.0; <-- not sure what i used this for
    private double slidervalueset = 0.0;
    private Double glassVolume = null;
    private Double volume = null;
    private Random rand = new Random();


    //------------------------End of Test variables------------------
    /* -> to be implemented when ingridient list is ready
    ArrayList<Ingredient> choseIngredientsList = new ArrayList<>();
    ArrayList<Ingredient> addedIngredientsList = new ArrayList<>();
    */
    private ArrayList<Ing> choseIngredientsList = new ArrayList<>();
    private ArrayList<Glassware> choseGlasswareList = new ArrayList<>();
//    private ArrayList<Garnish> choseGarnishList = new ArrayList<>();
//    private ArrayList<IceType> choseIceTypeList = new ArrayList<>();
    private ArrayList<Ing> addedIngredientsList = new ArrayList<>();

    private Ing selected = null;// selected object of ingredient
    private RingProgressIndicator alcoholPercent = new RingProgressIndicator();
    @FXML
    private Label lblChosenName, lblVolume, lblCost, lblTotalVolume;
    @FXML
    private AnchorPane alcoholPercentCircle;
    @FXML
    private VBox vBoxChosenIngredients, vBoxListOfIngredients;
    @FXML
    private Slider slider;
    @FXML
    private ProgressBar progressGlass;
    @FXML
    private Button btnAddIngredient;


    private int countVolume() {
        int totalVolume = 0;
        for (Ing a : addedIngredientsList) {
            totalVolume = totalVolume + a.getVolumeMagnitude();

        }
        return totalVolume;
    }

    private int countPercentage() {
        double preciseValueAlc;
        double preciseVolumeAlc = 0.0;
        int totalVolume = 0;

        int totalAlcohol;
        if (!addedIngredientsList.isEmpty()) {
            for (Ing a : addedIngredientsList) {
                totalVolume = totalVolume + a.getVolumeMagnitude();
                preciseVolumeAlc = preciseVolumeAlc + (a.getVolumeMagnitude() * a.getAlcoholPercentage() / 100);
            }
            preciseValueAlc = ((preciseVolumeAlc * 100) / totalVolume);
//            preciseValueAlc = Math.round(preciseValueAlc);
            totalAlcohol = (int) preciseValueAlc;

            return totalAlcohol;
        } else
            return 0;
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

    private void findSelected() {
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
                    } else {
                        btnAddIngredient.setDisable(false);
                    }
                } catch (NumberFormatException ex) {
                    //throws exception only on MacOS.
                    ex.printStackTrace();
                }
            });

        }
    }

    private void disbleAdd() {
        slider.valueProperty().addListener((arg0, arg1, arg2) -> {
            if (slider.getValue() == slider.getMin()) {
                btnAddIngredient.setDisable(true);
            }
        });
        if (selected == null) {
            btnAddIngredient.setDisable(true);
        }
    }

    private void choseIngredient(Event event) {
        slider.setDisable(false);

        Label lbl = (Label) event.getSource();
        selected = (Ing) lbl.getUserData();
        lblChosenName.setText(lbl.getText() + " " + selected.getAlcoholPercentage());
        sliderProgressChange();
        disbleAdd();
    }

    //------------Creating labels to be represented in the ingredient list------
    private void choseIngredientListElement(Ing object) {

        Label choseIngredientName = new Label();
        Label choseIngredientPrice = new Label();
        Tooltip percentage = new Tooltip("Alcohol: " + object.getAlcoholPercentage() + "%");
        choseIngredientName.setText(object.getName());
        choseIngredientName.setOnMouseClicked((EventHandler<Event>) this::choseIngredient);
        choseIngredientName.setCursor(Cursor.HAND);
        choseIngredientName.setTooltip(percentage);
        choseIngredientPrice.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        choseIngredientPrice.setText(String.format("%.2f", object.getPrice()) + " \u20ac" + "/l");
        choseIngredientPrice.setLayoutX(205.0);

        Group choseIngredient = new Group();
        choseIngredient.getChildren().add(choseIngredientName);
        choseIngredient.getChildren().add(choseIngredientPrice);
        choseIngredientName.setUserData(object);
        vBoxListOfIngredients.getChildren().add(choseIngredient);
    }

    private void choseGlasswareListElement(Glassware object) {

        Label choseGlasswareName = new Label();
        Label choseGlasswareVolume = new Label();
        choseGlasswareName.setText(object.getName());
        choseGlasswareName.setOnMouseClicked((Event event) -> {

        });
        choseGlasswareName.setCursor(Cursor.HAND);
        choseGlasswareVolume.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        choseGlasswareVolume.setText(object.getVolume().toString() + " ml");
        choseGlasswareVolume.setLayoutX(205.0);

        Group choseIngredient = new Group();
        choseIngredient.getChildren().add(choseGlasswareName);
        choseIngredient.getChildren().add(choseGlasswareVolume);
        choseGlasswareName.setUserData(object);
        vBoxListOfIngredients.getChildren().add(choseIngredient);
    }

    //    private void choseGarnishListElement(Garnish object) {
//
//        Label choseGarnishName = new Label();
//        choseGarnishName.setText(object.getName());
////        choseGarnishName.setOnMouseClicked((Event event) -> {
////
////        });
////        choseGarnishName.setCursor(Cursor.HAND);
////        Group choseIngredient = new Group();
////        choseIngredient.getChildren().add(choseGarnishName);
////        choseGarnishName.setUserData(object);
////        vBoxListOfIngredients.getChildren().add(choseIngredient);
//    }
//
//    private void choseIceTypeListElement(IceType object) {
//        Label choseIceTypeName = new Label();
//        choseIceTypeName.setText(object.getName());
////        choseIceTypeName.setOnMouseClicked((Event event) -> {
////
////        });
////        choseIceTypeName.setCursor(Cursor.HAND);
////        Group choseIngredient = new Group();
////        choseIngredient.getChildren().add(choseIceTypeName);
////        choseIceTypeName.setUserData(object);
////        vBoxListOfIngredients.getChildren().add(choseIngredient);
//    }
    //------------Adding from array list to vBox (display)------
    @FXML
    private void dummyIngredientAddToList() {
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

//    private void dummyGarnishAddToList() {
//        vBoxListOfIngredients.getChildren().clear();
//        for (Garnish a : choseGarnishList) {
//            choseGarnishListElement(a);
//        }
//    }
//
//    private void dummyIceTypeAddToList() {
//        vBoxListOfIngredients.getChildren().clear();
//        for (IceType a : choseIceTypeList) {
//            choseIceTypeListElement(a);
//        }
//    }

    //-----------Creating objects-------------------------------
    @FXML
    private void dummyIngredientCreate() {//for Test Purpose Only!!!!
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

    private void dummyGlasswareCreate() {//for Test Purpose Only!!!!
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

//    private void addIngredientTOLIST() {
//        for (Ing a : addedIngredientsList) {
//
//        }
//    }

    @FXML
    private void addIngredientWidget() {

        if (selected != null) {

            // Test purposes only
            btnAddIngredient.setDisable(true);
            //-------------------Setting new min values----------------
//            double diff = slider.getValue() - slider.getMin(); // slider value difference Current value - min value
            int setVolume = volume.intValue() - volumeSeparator; // available volume after adding previous ingredient
            double setProgress = progressGlass.getProgress() - progressSeparator; // Progress bar in a glass/available volume
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
                selected = (Ing) overlay.getUserData();
                lblChosenName.setText(selected.getName());
            });


            removeItem.setOnAction(event -> {// removig a widget
                vBoxChosenIngredients.getChildren().remove(ingredient);
                addedIngredientsList.remove(selected);
                slider.setMin(countVolume());// adding back to volume removed ingredient value
                slider.setValue(slider.getMin() - setProgress);// adding back to slider removed ingredient value
                volumeSeparator = volumeSeparator - added.getVolume(); // adding to volume and progress separator
               progressSeparator = progressSeparator - added.getProgressBar();
                choseIngredientsList.add(selected);
                alcoholPercent.setProgress(countPercentage());
                dummyIngredientAddToList();
                selected = null;
                lblChosenName.setText("Null");


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
            lblChosenName.setText("null");

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please chose an ingredient");
            alert.showAndWait();
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int totalAlcoholPercentage = 0;
        alcoholPercent.setProgress(totalAlcoholPercentage);  //for visual presentation only
        alcoholPercentCircle.getChildren().add(alcoholPercent);
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

    Ing(String name, Integer alcoholPercentage, Double price) {
        this.name = name;
        this.alcoholPercentage = alcoholPercentage;
        this.price = price;
    }

    String getName() {
        return name;
    }

    Integer getAlcoholPercentage() {
        return alcoholPercentage;
    }

    Double getPrice() {
        return price;
    }

    int getVolumeMagnitude() {
        return volumeMagnitude;
    }

    void setVolumeMagnitude(int volumeMagnitude) {
        this.volumeMagnitude = volumeMagnitude;
    }

}

class addedIng {
    private String name;
    private Integer Volume;
    private Double progressBar;

    addedIng(String name, Integer volume, Double progressBar) {
        this.name = name;
        Volume = volume;
        this.progressBar = progressBar;
    }

    String getName() {
        return name;
    }

    Integer getVolume() {
        return Volume;
    }

    Double getProgressBar() {
        return progressBar;
    }
}

class Glassware {

    private String name;
    private Integer volume;
    private String imageUrl;

    Glassware(String name, Integer volume, String imageUrl) {
        this.name = name;
        this.volume = volume;
        this.imageUrl = imageUrl;
    }

    String getName() {
        return name;
    }

    Integer getVolume() {
        return volume;
    }

//    String getImageUrl() {
//        return imageUrl;
//    }
}

//class Garnish {
//    String name;
//
//    Garnish(String name) {
//        this.name = name;
//    }
//
////    String getName() {
////        return name;
////    }
//}

//class IceType {
//    String name;
//
//    IceType(String name) {
//        this.name = name;
//    }
//
////    String getName() {
////        return name;
////    }
//}
