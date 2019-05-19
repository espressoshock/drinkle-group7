package com.espressoshock.drinkle.controllers.app.beverageBuilder;

import com.espressoshock.drinkle.databaseLayer.ConnectionLayer;
import com.espressoshock.drinkle.models.Beverage;
import com.espressoshock.drinkle.models.BrandsEnum;
import com.espressoshock.drinkle.models.Ingredient;
import com.espressoshock.drinkle.progressIndicator.RingProgressIndicator;
import com.espressoshock.drinkle.viewLoader.EventDispatcherAdapter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;


public class BeverageBuilder extends EventDispatcherAdapter implements Initializable {
    private Connection connection = null;
    private PreparedStatement prepStatement = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    //-------------------------Test variables only!!!----------------

    private Integer volumeSeparator = 0; // Value has been set to current slider value when ingredient is added
    private Double progressSeparator = 0.0; // Value has been set to current progress bar value when ingredient is added
    private double costSerparator = 0.0;
    private String sliderDoubleValueAsString = null;
    private String cost = "0.0"; //initial cost label text
    private double slidervalueset = 0.0;
    private Double glassVolume = null;
    private Double volume = null;
    private double totalCost = 0.0;
    private double costTOTALLE= 0.0;
    public static Beverage bvg = null;
    int beverage_id = 0;
    public static Glassware glass = null;

    //------------------------End of Test variables------------------
    // -> to be implemented when ingridient list is ready
    private ArrayList<Ingredient> choseIngredientsList2 = new ArrayList<>();
    public static ArrayList<Ingredient> addedIngredientsList2 = new ArrayList<>();

    //private ArrayList<Ing> choseIngredientsList = new ArrayList<>();
    private ArrayList<Glassware> choseGlasswareList = new ArrayList<>();
//    private ArrayList<Garnish> choseGarnishList = new ArrayList<>();
//    private ArrayList<IceType> choseIceTypeList = new ArrayList<>();
    //private ArrayList<Ing> addedIngredientsList = new ArrayList<>();

    private Ingredient selected = null;// selected object of ingredient
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
    private Button btnAddIngredient, btnIngredients;
    @FXML
    TextField txtFieldBeverageName;
    @FXML
    TextArea txtAreaNotes;


//    private double countCost() {
//        double countedCost = 0.0;
//        for (Ingredient a : addedIngredientsList2) {
//            countedCost = countedCost + a.getMagnitude() * a.getPricePerLiter() / 1000.00;
//        }
//
//        return countedCost;
//    }

    private void printTest() {
        for (Ingredient a : choseIngredientsList2) {
            System.out.println(a);
        }
    }

    @FXML
    private void saveBeverageToDB() throws Exception {
        Beverage d = createObject();
        Thread.sleep(1000);
        createBeverage(d);
        Thread.sleep(1000);
        for (Ingredient a : d.getIngredients()){
            createBeverageHasIngredients(a);
        }
    }

    public Beverage createObject() {
        Beverage a = new Beverage(txtFieldBeverageName.getText(), countPercentage(), Double.valueOf(cost), countVolume(), addedIngredientsList2);
        a.setNotes(txtAreaNotes.getText());
        bvg = a;
        return a;
    }

    @FXML
    private void createBeverage(Beverage beverage) throws SQLException {


        try {
            connection = ConnectionLayer.getConnection();
            statement = connection.createStatement();
            //resultSet = statement.executeQuery("SELECT ingredient.id,ingredient.name,ingredient.alcohol,ingredient.price_per_litre,ingredient.brand_id,brand.name " +
            //"FROM ingredient,brand WHERE ingredient.brand_id=brand.id");
            prepStatement = connection.prepareStatement("INSERT INTO beverage (name, alcohol, cost, notes) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            prepStatement.setString(1, beverage.getName());
            prepStatement.setInt(2, beverage.getAlcoholPercentage());
            prepStatement.setDouble(3, beverage.getCost());
            prepStatement.setString(4, "NOTES TEST");


            int rowAffected = prepStatement.executeUpdate();
            if (rowAffected == 1) {
                System.out.println("Beverage successfully added");
            }

            resultSet = prepStatement.getGeneratedKeys();
            if (resultSet.next()) {
                beverage_id = resultSet.getInt(1);
            }

        } catch (SQLException ex) {
            System.out.println("Exception: ");
            ex.printStackTrace();
        } finally {
            ConnectionLayer.cleanUp(statement, resultSet);
        }
        connection.close();
    }
    @FXML
    private void createBeverageHasIngredients(Ingredient ing) throws SQLException{
        try {
            connection = ConnectionLayer.getConnection();
            statement = connection.createStatement();
            //resultSet = statement.executeQuery("SELECT ingredient.id,ingredient.name,ingredient.alcohol,ingredient.price_per_litre,ingredient.brand_id,brand.name " +
            //"FROM ingredient,brand WHERE ingredient.brand_id=brand.id");
            prepStatement = connection.prepareStatement("INSERT INTO beverage_has_ingredient (beverage_id, ingredient_id, magnitude) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            prepStatement.setInt(1, beverage_id);
            prepStatement.setInt(2, ing.getId());
            prepStatement.setInt(3, ing.getMagnitude());


            int rowAffected = prepStatement.executeUpdate();
            if (rowAffected == 1) {
                System.out.println("Ingredient successfully added to beverage");
            }


        } catch (SQLException ex) {
            System.out.println("Exception: ");
            ex.printStackTrace();
        } finally {
            ConnectionLayer.cleanUp(statement, resultSet);
        }
        connection.close();
    }
    private void loadIngredientsFromDB() throws SQLException {
        int id_ing;
        String name;
        int alcohol;
        int price_per_litre;
        int brand_id;
        String brand;
        try {
            connection = ConnectionLayer.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT ingredient.id,ingredient.name,ingredient.alcohol,ingredient.price_per_litre,ingredient.brand_id,brand.name " +
                    "FROM ingredient,brand WHERE ingredient.brand_id=brand.id");


            while (resultSet.next()) {
                id_ing = resultSet.getInt(1);
                name = resultSet.getString(2);
                alcohol = resultSet.getInt(3);
                price_per_litre = resultSet.getInt(4);
                brand = resultSet.getString(6);

                BrandsEnum brandEnum = BrandsEnum.fromString(brand);

//            System.out.println(resultSet.getString("id") +
//                    resultSet.getString("name")+
//                    resultSet.getString("alcohol")+
//                    resultSet.getString("price_per_litre"));
                //System.out.printf("id: %d, name: %s, alcohol: %d, price: %d\n", id, name, alcohol, price_per_litre);
                Ingredient i = new Ingredient(name, alcohol, price_per_litre / 10, brandEnum, 0);
                i.setId(id_ing);
                //System.out.println(i);
                choseIngredientsList2.add(i);
            }
        } catch (SQLException ex) {
            System.out.println("Exception: ");
            ex.printStackTrace();
        } finally {
            ConnectionLayer.cleanUp(statement, resultSet);
        }
        connection.close();

    }

    private int countVolume() {
        int totalVolume = 0;
        for (Ingredient a : addedIngredientsList2) {
            totalVolume = totalVolume + a.getMagnitude();

        }
        return totalVolume;
    }

    private int countPercentage() {

        double preciseValueAlc;
        double preciseVolumeAlc = 0.0;
        int totalVolume = 0;

        int totalAlcohol;
        if (!addedIngredientsList2.isEmpty()) {
            for (Ingredient a : addedIngredientsList2) {
                totalVolume = totalVolume + a.getMagnitude();
                preciseVolumeAlc = preciseVolumeAlc + (a.getMagnitude() * a.getAlcoholPercentage() / 100.00);
            }
            preciseValueAlc = ((preciseVolumeAlc * 100) / totalVolume);
//            preciseValueAlc = Math.round(preciseValueAlc);
            if (preciseValueAlc - (int) preciseValueAlc > 0) {
                totalAlcohol = (int) preciseValueAlc + 1;
            } else
                totalAlcohol = (int) preciseValueAlc;

            return totalAlcohol;
        } else
            return 0;
    }

    public void openPrintView() throws Exception {
        createObject();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/app/print-layout.fxml"));
        Scene print = new Scene(root);
        primaryStage.setTitle("Drinkle-Print to PDF");
        primaryStage.setResizable(false);
        primaryStage.setScene(print);
        primaryStage.show();
    }

    private void findSelected() {
        Ingredient sel = null;
        for (Ingredient e : choseIngredientsList2) {
            if (e == selected) {
                sel = e;
            }

        }
        choseIngredientsList2.remove(sel);
    }

    private void sliderProgressChange() {
        if (selected == null) {
            slider.setDisable(true);

        } else {


            slider.valueProperty().addListener((arg0, arg1, arg2) -> {
                try {
                    slider.setValue(Math.round(arg2.doubleValue()));
                    sliderDoubleValueAsString = String.format("%.2f", slider.getValue() - slidervalueset);
                    volume = Double.parseDouble(String.format("%.2f", slider.getValue()));

                    cost = String.format("%.2f", ((Double.parseDouble(sliderDoubleValueAsString)-slider.getMin())* selected.getPricePerLiter() / 1000.00)+costSerparator);

                    progressGlass.setProgress(Double.parseDouble(String.format("%.2f", slider.getValue() / (glass.getVolume() / 100.00) / 100.00)));
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

    private void disableAdd() {
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
        if(slider.getValue()>slider.getMin()) {
            slider.setValue(slider.getMin());
        }
        Label lbl = (Label) event.getSource();
        selected = (Ingredient) lbl.getUserData();
        lblChosenName.setText(lbl.getText() + " " + selected.getAlcoholPercentage());
        sliderProgressChange();
        disableAdd();
    }
    private void choseGlassware(Event event) {

        Label lbl = (Label) event.getSource();
        glass = (Glassware) lbl.getUserData();
        lblTotalVolume.setText(String.valueOf(glass.getVolume()));
        slider.setMax(glass.getVolume());
        btnIngredients.setDisable(false);


    }

    //------------Creating labels to be represented in the ingredient list------
    private void choseIngredientListElement(Ingredient object) {

        Label choseIngName = new Label();
        Label choseIngPrice = new Label();
        Tooltip percentage = new Tooltip("Alcohol: " + object.getAlcoholPercentage() + "%");
        choseIngName.setText(object.getName());
        choseIngName.setOnMouseClicked((EventHandler<Event>) this::choseIngredient);
        choseIngName.setCursor(Cursor.HAND);
        choseIngName.setTooltip(percentage);
        choseIngPrice.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        choseIngPrice.setText(object.getPricePerLiter() + " \u20ac" + "/l");
        choseIngPrice.setLayoutX(205.0);

        Group choseIngredient = new Group();
        choseIngredient.getChildren().add(choseIngName);
        choseIngredient.getChildren().add(choseIngPrice);
        choseIngName.setUserData(object);
        vBoxListOfIngredients.getChildren().add(choseIngredient);
    }

    private void choseGlasswareListElement(Glassware object) {

        Label choseGlasswareName = new Label();
        Label choseGlasswareVolume = new Label();

        choseGlasswareName.setText(object.getName());
        choseGlasswareName.setOnMouseClicked((EventHandler<Event>) this::choseGlassware);
        choseGlasswareName.setCursor(Cursor.HAND);
        choseGlasswareVolume.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        choseGlasswareVolume.setText(String.valueOf(object.getVolume()));
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
        Collections.sort(choseIngredientsList2, Ingredient.IngredientNameComparator);
        for (Ingredient a : choseIngredientsList2) {
            choseIngredientListElement(a);
        }
    }

    public void dummyGlasswareAddToList() {
        vBoxListOfIngredients.getChildren().clear();
        btnIngredients.setDisable(true);
        selected = null;
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
            costSerparator=Double.valueOf(cost);
            System.out.println(costSerparator);
            // Test purposes only
            btnAddIngredient.setDisable(true);
            //-------------------Setting new min values----------------
//            double diff = slider.getValue() - slider.getMin(); // slider value difference Current value - min value
            int setVolume = volume.intValue() - volumeSeparator; // available volume after adding previous ingredient
            double setProgress = progressGlass.getProgress() - progressSeparator; // Progress bar in a glass/available volume
            double setCost = Double.valueOf(cost) - costSerparator;
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
            //addedIng added = new addedIng(lblChosenName.getText(), setVolume, setProgress);
            selected.setMagnitude(setVolume);
            addedIngredientsList2.add(selected);
            overlay.setContextMenu(removeMenu);
            overlay.setUserData(selected);
            overlay.setOnMouseClicked(event -> {
                selected = (Ingredient) overlay.getUserData();
                lblChosenName.setText(selected.getName());
            });


            removeItem.setOnAction(event -> {// removig a widget
                vBoxChosenIngredients.getChildren().remove(ingredient);
                addedIngredientsList2.remove(selected);
                slider.setMin(countVolume());// adding back to volume removed ingredient value
                slider.setValue(slider.getMin() - setProgress);// adding back to slider removed ingredient value
                volumeSeparator = volumeSeparator - selected.getMagnitude(); // adding to volume and progress separator
                progressSeparator = progressSeparator - (120 / selected.getMagnitude());
                costSerparator = costSerparator - (selected.getPricePerLiter() * selected.getMagnitude()/1000.00);
                System.out.println(costSerparator);
                choseIngredientsList2.add(selected);
                alcoholPercent.setProgress(countPercentage());
                dummyIngredientAddToList();
                selected = null;
                lblChosenName.setText("Null");
                lblCost.setText(String.format("%.2f", costSerparator));


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
    private static void limitText(TextField tf, int maxLength){
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
            }
        });
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int totalAlcoholPercentage = 0;
        alcoholPercent.setProgress(totalAlcoholPercentage);  //for visual presentation only
        alcoholPercentCircle.getChildren().add(alcoholPercent);
        //dummyIngredientAddToList();// create generate and add to list mock ingredients
        dummyGlasswareCreate();
        //dummyIngredientCreate();
        sliderProgressChange();
        lblTotalVolume.setText("100");
        btnIngredients.setDisable(true);
        slider.setBlockIncrement(1);
//        progressGlass.setStyle("-fx-accent: )");
        limitText(txtFieldBeverageName,45);// <--- limit name to varchar(45)
        disableAdd();
        try {
            loadIngredientsFromDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //printTest();
    }


}


////////////////////simplified classes to test view -> to be deleted when Ingredients are available from database



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
