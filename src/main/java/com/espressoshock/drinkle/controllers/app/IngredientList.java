package com.espressoshock.drinkle.controllers.app;

import com.espressoshock.drinkle.models.*;
import com.espressoshock.drinkle.viewLoader.EventDispatcherAdapter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.net.URL;
import com.espressoshock.drinkle.progressIndicator.RingProgressIndicator;
import com.espressoshock.drinkle.databaseLayer.ConnectionLayer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class IngredientList extends EventDispatcherAdapter implements Initializable {

    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    private ArrayList<Ingredient> ingredientsList = new ArrayList<>();
    private ArrayList<IngredientCategory> categoriesAlc = new ArrayList<>();
    private ArrayList<BrandsEnum> brandsList = new ArrayList<>();
    private ArrayList<IngredientCategory> categoryNonAlc = new ArrayList<>();


    @FXML
    private VBox vBoxIngredients;

    @FXML
    private Button btnSimilarProduct /*, btnAddIngredient*/;

    @FXML
    private MenuButton menuBtnCategory, menuBtnBrand, menuBtnAlcoholOption;

    @FXML
    private TextField txtSearchOption, txtSimilarWith;

    @FXML
    private ProgressBar progressBarAlcohol, progressBarPrice;

    @FXML
    private Label lblAlcohol;
    @FXML
    private Label lblPrice;
    @FXML
    private Label lblSelectedIngredientName;
    @FXML
    private Label lblIngredientCategory;
    @FXML
    private Label lblIngredientBrand;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        createIngredientsList();
        createCategoryList();
        populateNonAlcCategories();
        populateBrandsList();

    }

    @FXML
    private void selectBtnSearch() {
        vBoxIngredients.getChildren().clear();
        String text = txtSearchOption.getText().toLowerCase();
        for (Ingredient x : ingredientsList) {
            if (text.length() != 0) {
                if (x.getName().toLowerCase().contains(text)) {
                    Button button = new Button();
                    button.setOnAction(this::selectVboxButton);
                    button.setMinWidth(280);
                    button.setMinHeight(40);
                    button.setText(x.getName());
                    vBoxIngredients.getChildren().add(button);
                }
            }
        }
        menuBtnBrand.setText("Brand");
        menuBtnCategory.setText("Category");
    }
    @FXML
    private void selectAlcoholSelection(ActionEvent event){
        MenuItem selection = (MenuItem) event.getSource();
        if (selection.getText().equals("Alcoholic")){
            menuBtnAlcoholOption.setText("Alcohol");
            populateCategoryMenu(categoriesAlc);
            menuBtnCategory.setText("Categories");
            menuBtnBrand.setText("Brand");
        }else{
            menuBtnAlcoholOption.setText("Non Alcoholic");
            populateCategoryMenu((categoryNonAlc));
            menuBtnCategory.setText("Categories");
            menuBtnBrand.setText("Brand");
            menuBtnBrand.getItems().clear();
            vBoxIngredients.getChildren().clear();
        }
    }

    @FXML
    private void selectCategory(ActionEvent e) {

            vBoxIngredients.getChildren().clear();
            menuBtnBrand.setText("Brands");
            MenuItem selection = (MenuItem) e.getSource();
            menuBtnCategory.setText(selection.getText());
            menuBtnBrand.getItems().clear();
            for (BrandsEnum brandsEnum : brandsList) {
                if (selection.getText().equals(brandsEnum.getProductType().getName())) {
                    MenuItem button = new MenuItem();
                    button.setText(brandsEnum.getBrandName());
                    button.setOnAction(this::selectBrand);
                    menuBtnBrand.getItems().add(button);
                }
            }

    }

    @FXML
    private void selectBrand(ActionEvent e) {
        MenuItem selection = (MenuItem) e.getSource();
        menuBtnBrand.setText(selection.getText());
        vBoxIngredients.getChildren().clear();
        for (Ingredient x : ingredientsList) {
            if (selection.getText().equals(x.getBrand().getBrandName())) {
                Button button = new Button();
                button.setOnAction(this::selectVboxButton);
                button.setMinWidth(405);
                button.setMinHeight(40);
                button.setText(x.getName());
                vBoxIngredients.getChildren().add(button);
            }
        }
    }

    @FXML
    private void selectVboxButton(ActionEvent e) {
       try{
           Button selection = (Button) e.getSource();
           for (Ingredient x : ingredientsList) {
               if (x.getName().equals(selection.getText())) {
                   lblSelectedIngredientName.setText(x.getName());
                   txtSimilarWith.setText(x.getName());
                   lblAlcohol.setText(Integer.toString(x.getAlcoholPercentage()));
                   progressBarAlcohol.setProgress(Double.valueOf(x.getAlcoholPercentage()) / 100);
                   lblPrice.setText(Integer.toString(x.getPricePerLiter()));
                   progressBarPrice.setProgress(Double.valueOf(x.getPricePerLiter()) / 1000);
                   lblIngredientBrand.setText(x.getBrand().getBrandName());
                   lblIngredientCategory.setText(x.getBrand().getProductType().getName());
                   for (Ingredient y : ingredientsList) {
                       if (x.getBrand().getBrandName().equals(y.getBrand().getBrandName())) {
                           if (!x.getName().equals(y.getName())) {
                               btnSimilarProduct.setText(y.getName());
                           }
                       }
                   }
               }
           }
           selection.setDisable(false);
       }catch(Exception exc){
           exc.printStackTrace();
        }

    }

    /*TODO****when account implemented****/
    @FXML
    private void selectAddIngredient(){}


    /** Initialize methods
     *
     * The DB is queried when the view is initiated, and the collections are populated with corresponding objects
     *
     */
    private void createIngredientsList() {
        String [] names ={"Absolute Dry","Absolute Lemon","Absolute Dark","Smirnoff Ice","Grey Goose xo","Grey Goose Ice","Jack Daniel's XO"};
        int[] alcoholPercentage={40,36,42,41,43,41,42,};
        int[] pricePerLiter={420,340,560,360,780,680,720,};
        BrandsEnum[] brand={BrandsEnum.ABSOLUTE,BrandsEnum.ABSOLUTE,BrandsEnum.ABSOLUTE,BrandsEnum.SMIRNOFF,BrandsEnum.GREY_GOOSE,BrandsEnum.GREY_GOOSE,BrandsEnum.JACK_DANIELS,};
        int magnitude=0;
        for(int i = 0; i<7;i++){
            ingredientsList.add(new Ingredient(names[i],alcoholPercentage[i],pricePerLiter[i],brand[i],magnitude));
        }
    }

    private void createCategoryList() {

        IngredientCategory[] category = {IngredientCategory.WHISKEY,IngredientCategory.VODKA,IngredientCategory.VERMOUTH,IngredientCategory.BITTER,IngredientCategory.TEQUILA,IngredientCategory.GIN,IngredientCategory.RUM,IngredientCategory.LIQUEUR,
                IngredientCategory.BRANDY,IngredientCategory.CIDER,IngredientCategory.WINE,IngredientCategory.BEER,IngredientCategory.OTHER };
        Collections.addAll(categoriesAlc, category);
    }

    private void populateCategoryMenu(ArrayList<IngredientCategory> categoriesData) {
        menuBtnCategory.getItems().clear();
        for (IngredientCategory x : categoriesData) {
            MenuItem category = new MenuItem(x.getName());
            category.setOnAction(this::selectCategory);
            menuBtnCategory.getItems().add(category);
        }
    }

    private void populateBrandsList() {
        BrandsEnum[] brands = {BrandsEnum.DOM_PERIGNON,BrandsEnum.GRENACHE,BrandsEnum.PINOT_NOIR,BrandsEnum.SHIRAZ,BrandsEnum.MERLOT,BrandsEnum.CABERNET_SAUVIGNON,BrandsEnum.PINOT_GRIS,BrandsEnum.CHARDONNAY,BrandsEnum.SAUVIGNON_BLANC,BrandsEnum.PAMA,BrandsEnum.MARASCHINO,
                BrandsEnum.LIMONCELLO,BrandsEnum.GRAND_MARNIER,BrandsEnum.CAMPARI,BrandsEnum.BANANAS,BrandsEnum.ROCK_RYE,BrandsEnum.ADVOCAAT,BrandsEnum.NOCELLO,BrandsEnum.DISARONNO,BrandsEnum.DRAMBUIE,BrandsEnum.UNICUM,BrandsEnum.JAGERMEISTER,BrandsEnum.MASATICA,BrandsEnum.COCCHI,
                BrandsEnum.CONTRATTO,BrandsEnum.ANISETTE,BrandsEnum.VISINATA,BrandsEnum.TRIPLE_SEC,BrandsEnum.ROSOLIO,BrandsEnum.CURACAO,BrandsEnum.GUAVABERRY,BrandsEnum.CREME_DE_CASSIS,BrandsEnum.CRUZAN,BrandsEnum.CAROLANS,BrandsEnum.AMARULA,BrandsEnum.MIDNIGHT_ESPRESSO,BrandsEnum.TIA_MARIA,
                BrandsEnum.BAILEYS,BrandsEnum.AMARETTO,BrandsEnum.ARMAGNAC,BrandsEnum.COGNAC,BrandsEnum.GREEN_ISLAND,BrandsEnum.SAINT_JAMES,BrandsEnum.HAVANA_CLUB,BrandsEnum.BACARDI,BrandsEnum.HERANCIA_DE_PLATA,BrandsEnum.JOSE_CUERVO,BrandsEnum.FORTALEZA,BrandsEnum.GORDONS,BrandsEnum.BEEFEATER,
                BrandsEnum.MARTINI,BrandsEnum.GLENFIDDICH,BrandsEnum.JAMESON,BrandsEnum.GLENFARCLASS,BrandsEnum.JIM_BEAM,BrandsEnum.JOHNNIE_WALKER,BrandsEnum.JACK_DANIELS,BrandsEnum.JEAN_MARC,BrandsEnum.TITOS,BrandsEnum.STOLICHNAYA,BrandsEnum.SMIRNOFF,BrandsEnum.BLACK_COW,BrandsEnum.GREY_GOOSE,
                BrandsEnum.OTHER_BRAND ,BrandsEnum.SHERIDANS ,BrandsEnum.SAMBUCA ,BrandsEnum.KAHLUA, BrandsEnum.CREME_DE_MENTHE , BrandsEnum.FERNET , BrandsEnum.GALLIANO , BrandsEnum.SKOL, BrandsEnum.BIRRA_MORETTI, BrandsEnum.STELLA_ARTIOS ,BrandsEnum.PAULANER, BrandsEnum.CORONA, BrandsEnum.BUDWEISER,
                BrandsEnum.CARLSBERG ,BrandsEnum.HEINEKEN , BrandsEnum.COINTREAU ,BrandsEnum.MIDORI, BrandsEnum.NOIAU_DE_POISSY ,BrandsEnum.MANZANA_VERDE, BrandsEnum.FIZZ, BrandsEnum.BULMERS, BrandsEnum.POMAGNE ,BrandsEnum.SOMERSBY,BrandsEnum.MAGNERS,BrandsEnum.DEPREVILLE,
                BrandsEnum.PROSECCO,BrandsEnum.PERRIER_JOUET,BrandsEnum.CINZANO,BrandsEnum.CRISTAL,BrandsEnum.MOET_CHANDON,BrandsEnum.ABSOLUTE};
        Collections.addAll(brandsList, brands);

    }

    private void populateNonAlcCategories() {
        IngredientCategory[] nonAlcCategories = {IngredientCategory.GARNISH, IngredientCategory.ICE_TYPE, IngredientCategory.WATER,IngredientCategory.POWDER,IngredientCategory.OTHER,
                IngredientCategory.DAIRY_PRODUCT, IngredientCategory.JUICE, IngredientCategory.SYRUP, IngredientCategory.FRUIT, IngredientCategory.WARM_DRINK};
        Collections.addAll(categoryNonAlc, nonAlcCategories);
    }

    public void retrieveDataFromDB()throws Exception{
          {

            //int id_ing;

            String name;

            int alcohol;

            int price_per_litre;

            //int brand_id;

            String brand;

            String category;

            try {

                connection = ConnectionLayer.getConnection();

                statement = connection.createStatement();

                resultSet = statement.executeQuery("SELECT ingredient.id,ingredient.name,ingredient.alcohol,ingredient.price_per_litre,ingredient.brand_id,brand.name " +

                        "FROM ingredient,brand WHERE ingredient.brand_id=brand.id");





                while (resultSet.next()) {

                   // id_ing = resultSet.getInt(1);

                    name = resultSet.getString(2);

                    alcohol = resultSet.getInt(3);

                    price_per_litre = resultSet.getInt(4);

                    brand = resultSet.getString(6);



//            System.out.println(resultSet.getString("id") +

//                    resultSet.getString("name")+

//                    resultSet.getString("alcohol")+

//                    resultSet.getString("price_per_litre"));

                    //System.out.printf("id: %d, name: %s, alcohol: %d, price: %d\n", id, name, alcohol, price_per_litre);

                    //Ingredient i = new Ingredient(name, alcohol, price_per_litre / 10, brand, 0);

                    //System.out.println(i);

                   // choseIngredientsList2.add(i);

                }

            } catch (SQLException ex) {

                System.out.println("Exception: ");

                ex.printStackTrace();

            } finally {

                ConnectionLayer.cleanUp(statement, resultSet);

            }

            connection.close();
            }


        }




}
