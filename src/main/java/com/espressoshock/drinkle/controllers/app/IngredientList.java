package com.espressoshock.drinkle.controllers.app;

import com.espressoshock.drinkle.models.*;
import com.espressoshock.drinkle.viewLoader.EventDispatcherAdapter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.*;

public class IngredientList extends EventDispatcherAdapter implements Initializable {

    private ArrayList<Ingredient> ingredientsList = new ArrayList<>();
    private ArrayList<IngredientCategory> categories = new ArrayList<>();
    private ArrayList<BrandsEnum> brandsList = new ArrayList<>();


    @FXML
    private VBox vBoxIngredients;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnSimilarProduct;

    @FXML
    private Button btnAddIngredient;

    @FXML
    private MenuButton menuBtnCategory;

    @FXML
    private MenuButton menuBtnBrand;

    @FXML
    private MenuButton menuBtnAlcoholOption;

    @FXML
    private TextField txtSearchOption;

    @FXML
    private TextField txtSimilarWith;

    @FXML
    private ProgressBar progressBarAlcohol;

    @FXML
    private ProgressBar progressBarPrice;

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

        //       ingredientsList.addAll(createIngredientsList());
        createIngredientsList();
        createCategoryList();
        populateCategoryMenu(categories);
        populateBrandsList();
    }

    @FXML
    private void selectBtnSearch(ActionEvent e) {
        Button search = (Button) e.getSource();
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
            if (selection.getText().equals(x.getBrandsEnum().getBrandName())) {
                Button button = new Button();
                button.setOnAction(this::selectVboxButton);
                button.setMinWidth(280);
                button.setMinHeight(40);
                button.setText(x.getName());
                vBoxIngredients.getChildren().add(button);
            }
        }
    }

    @FXML
    private void selectVboxButton(ActionEvent e) {
        Button selection = (Button) e.getSource();
        for (Ingredient x : ingredientsList) {
            if (x.getName().equals(selection.getText())) {
                lblSelectedIngredientName.setText(x.getName());
                txtSimilarWith.setText(x.getName());
                for (Ingredient y : ingredientsList) {
                    if (x.getBrandsEnum().getBrandName().equals(y.getBrandsEnum().getBrandName())) {
                        if (!x.getName().equals(y.getName())) {
                            btnSimilarProduct.setText(y.getName());
                        }
                    }
                }
            }
        }
    }

    @FXML
    private void selectBtnSimilarProduct(ActionEvent e) {
        Button alternative = (Button) e.getSource();
        for (Ingredient x : ingredientsList) {
            if (alternative.getText().equals(x.getName())) {
                txtSimilarWith.setText(x.getName());
                lblSelectedIngredientName.setText(x.getName());
                for (Ingredient z : ingredientsList) {
                    if (x.getBrandsEnum().getBrandName().equals(z.getBrandsEnum().getBrandName())) {
                        if (!x.getName().equals(z.getName())) {
                            btnSimilarProduct.setText(z.getName());
                        }
                    }
                }
            }
        }
    }

    private void createCategoryList() {

        /** fetch&add all the ingredients from DB Drinkle, table Category, to categories*/

        /** categories added for view development use ******/
        categories.add(IngredientCategory.WHISKEY);
        categories.add(IngredientCategory.VODKA);
        categories.add(IngredientCategory.VERMOUTH);
        categories.add(IngredientCategory.BITTER);
        categories.add(IngredientCategory.TEQUILA);
        categories.add(IngredientCategory.GIN);
        categories.add(IngredientCategory.RUM);
        categories.add(IngredientCategory.LIQUEUR);
        categories.add(IngredientCategory.BRANDY);
        categories.add(IngredientCategory.CIDER);
        categories.add(IngredientCategory.WINE);
        categories.add(IngredientCategory.BEER);
        categories.add(IngredientCategory.FRUIT);
        categories.add(IngredientCategory.SYRUP);
        categories.add(IngredientCategory.WATER);
        categories.add(IngredientCategory.WARM_DRINK);
        categories.add(IngredientCategory.DAIRY_PRODUCT);
        categories.add(IngredientCategory.GARNISH);
        categories.add(IngredientCategory.POWDER);
        categories.add(IngredientCategory.OTHER);

    }

    private void populateCategoryMenu(ArrayList<IngredientCategory> categoriesData) {
        menuBtnBrand.getItems().clear();
        for (IngredientCategory x : categoriesData) {
            MenuItem category = new MenuItem(x.getName());
            category.setOnAction(this::selectCategory);
            menuBtnBrand.getItems().add(category);
        }
    }

    private void createIngredientsList() {
    }

    private void populateBrandsList() {
        brandsList.add(BrandsEnum.ABSOLUTE);
        brandsList.add(BrandsEnum.GREY_GOOSE);
        brandsList.add(BrandsEnum.BLACK_COW);
        brandsList.add(BrandsEnum.SMIRNOFF);
        brandsList.add(BrandsEnum.STOLICHNAYA);
        brandsList.add(BrandsEnum.TITOS);
        brandsList.add(BrandsEnum.JEAN_MARC);
        brandsList.add(BrandsEnum.JACK_DANIELS);
        brandsList.add(BrandsEnum.JOHNNIE_WALKER);
        brandsList.add(BrandsEnum.JIM_BEAM);
        brandsList.add(BrandsEnum.GLENFARCLASS);
        brandsList.add(BrandsEnum.JAMESON);
        brandsList.add(BrandsEnum.GLENFIDDICH);
        brandsList.add(BrandsEnum.MARTINI);
        brandsList.add(BrandsEnum.BEEFEATER);
        brandsList.add(BrandsEnum.GORDONS);
        brandsList.add(BrandsEnum.FORTALEZA);
        brandsList.add(BrandsEnum.JOSE_CUERVO);
        brandsList.add(BrandsEnum.HERANCIA_DE_PLATA);
        brandsList.add(BrandsEnum.BACARDI);
        brandsList.add(BrandsEnum.HAVANA_CLUB);
        brandsList.add(BrandsEnum.SAINT_JAMES);
        brandsList.add(BrandsEnum.GREEN_ISLAND);
        brandsList.add(BrandsEnum.COGNAC);
        brandsList.add(BrandsEnum.ARMAGNAC);
        brandsList.add(BrandsEnum.AMARETTO);
        brandsList.add(BrandsEnum.BAILEYS);
        brandsList.add(BrandsEnum.TIA_MARIA);
        brandsList.add(BrandsEnum.MIDNIGHT_ESPRESSO);
        brandsList.add(BrandsEnum.AMARULA);
        brandsList.add(BrandsEnum.CAROLANS);
        brandsList.add(BrandsEnum.CRUZAN);
        brandsList.add(BrandsEnum.CREME_DE_CASSIS);
        brandsList.add(BrandsEnum.GUAVABERRY);
        brandsList.add(BrandsEnum.CURACAO);
        brandsList.add(BrandsEnum.ROSOLIO);
        brandsList.add(BrandsEnum.TRIPLE_SEC);
        brandsList.add(BrandsEnum.VISINATA);
        brandsList.add(BrandsEnum.ANISETTE);
        brandsList.add(BrandsEnum.CONTRATTO);
        brandsList.add(BrandsEnum.COCCHI);
        brandsList.add(BrandsEnum.MASATICA);
        brandsList.add(BrandsEnum.JAGERMEISTER);
        brandsList.add(BrandsEnum.UNICUM);
        brandsList.add(BrandsEnum.DRAMBUIE);
        brandsList.add(BrandsEnum.DISARONNO);
        brandsList.add(BrandsEnum.NOCELLO);
        brandsList.add(BrandsEnum.ADVOCAAT);
        brandsList.add(BrandsEnum.ROCK_RYE);
        brandsList.add(BrandsEnum.BANANAS);
        brandsList.add(BrandsEnum.CAMPARI);
        brandsList.add(BrandsEnum.GRAND_MARNIER);
        brandsList.add(BrandsEnum.LIMONCELLO);
        brandsList.add(BrandsEnum.MARASCHINO);
        brandsList.add(BrandsEnum.PAMA);
        brandsList.add(BrandsEnum.SAUVIGNON_BLANC);
        brandsList.add(BrandsEnum.CHARDONNAY);
        brandsList.add(BrandsEnum.PINOT_GRIS);
        brandsList.add(BrandsEnum.CABERNET_SAUVIGNON);
        brandsList.add(BrandsEnum.MERLOT);
        brandsList.add(BrandsEnum.SHIRAZ);
        brandsList.add(BrandsEnum.PINOT_NOIR);
        brandsList.add(BrandsEnum.GRENACHE);
        brandsList.add(BrandsEnum.DOM_PERIGNON);
        brandsList.add(BrandsEnum.MOET_CHANDON);
        brandsList.add(BrandsEnum.CRISTAL);
        brandsList.add(BrandsEnum.CINZANO);
        brandsList.add(BrandsEnum.PERRIER_JOUET);
        brandsList.add(BrandsEnum.PROSECCO);
        brandsList.add(BrandsEnum.LOUIS_BOUILOT);
        brandsList.add(BrandsEnum.DEPREVILLE);
        brandsList.add(BrandsEnum.MAGNERS);
        brandsList.add(BrandsEnum.SOMERSBY);
        brandsList.add(BrandsEnum.POMAGNE);
        brandsList.add(BrandsEnum.BULMERS);
        brandsList.add(BrandsEnum.FIZZ);
        brandsList.add(BrandsEnum.MANZANA_VERDE);
        brandsList.add(BrandsEnum.NOIAU_DE_POISSY);
        brandsList.add(BrandsEnum.MIDORI);
        brandsList.add(BrandsEnum.COINTREAU);
        brandsList.add(BrandsEnum.HEINEKEN);
        brandsList.add(BrandsEnum.CARLSBERG);
        brandsList.add(BrandsEnum.BUDWEISER);
        brandsList.add(BrandsEnum.CORONA);
        brandsList.add(BrandsEnum.PAULANER);
        brandsList.add(BrandsEnum.STELLA_ARTIOS);
        brandsList.add(BrandsEnum.BIRRA_MORETTI);
        brandsList.add(BrandsEnum.SKOL);
        brandsList.add(BrandsEnum.GALLIANO);
        brandsList.add(BrandsEnum.FERNET);
        brandsList.add(BrandsEnum.CREME_DE_MENTHE);
        brandsList.add(BrandsEnum.KAHLUA);
        brandsList.add(BrandsEnum.SAMBUCA);
        brandsList.add(BrandsEnum.SHERIDANS);
        brandsList.add(BrandsEnum.OTHER_BRAND);

    }
}
