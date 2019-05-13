package com.espressoshock.drinkle.controllers.app;

import com.espressoshock.drinkle.models.*;
import com.espressoshock.drinkle.viewLoader.EventDispatcherAdapter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;


public class IngredientList extends EventDispatcherAdapter implements Initializable {

    private ArrayList<Ingredient> ingredientsList = new ArrayList<>();
    private ArrayList<IngredientCategory> categories = new ArrayList<>();
    private ArrayList<Brand> brands = new ArrayList<>();

    @FXML
    private VBox vBoxIngredients;
    @FXML
    private Button btnCreate, btnAddToInventory, btnSearch, btnChoose1, btnChoose2;
    @FXML
    private CheckBox checkBoxAddToList, checkBoxLike;
    @FXML
    private MenuButton menuButtonCategory, menuButtonBrands;
    @FXML
    private ProgressBar progressBarAlcohol, progressBarPrice;
    @FXML
    private TextArea txtAreaDescription;
    @FXML
    private TextField txtFieldPrice, txtFieldSearchOption, txtFieldVisualisations, txtFieldLike, txtFieldProductName, txtFieldSimilarWithProduct, txtFieldProductOneName, txtFieldProductTwoName, txtFieldAlcohol;
    @FXML
    private ImageView imgViewProduct, imgViewSimilarProduct1, imgViewSimilarProduct2;

    public void changeView() {/*super.dispatchViewChangeRequest(ViewMetadata.)*/}

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //       ingredientsList.addAll(createIngredientsList());
        createIngredientsList();
        createCategoryList();
        populateCategoryMenu(categories);
        populateBrandsList();

    }

    // TODO*********************Methods*************************************
    @FXML
    private void selectSimilarProductPicture() {
    }

    @FXML
    private void selectLike() {
    }

    @FXML
    private void selectAddToFavorit() {
    }

    @FXML
    private void selectCreate() {
    }

    @FXML
    private void selectGoToMenuButton() {
    }

    @FXML
    private void selectBtnSearch() {
    }

    @FXML
    private void selectMenuItemBrand() {
    }

    @FXML
    private void selectMenuItemCategory() {
    }

    private ArrayList<Ingredient> setIngredientsList(IngredientCategory selection) {
        return null;
    }


    @FXML
    private void selectCategory(ActionEvent e) {
        MenuItem selection = (MenuItem) e.getSource();
        menuButtonCategory.setText(selection.getText());
        menuButtonBrands.getItems().clear();

        for (Ingredient x : ingredientsList) {
            if (x.getCategory().getName().equals(selection.getText())) {

            }
        }
        for (Brand b : brands) {
            System.out.println(b.getName());
        }

/*            if (selection.getText().equals(x.getProductType().getName())) {
                MenuItem button = new MenuItem();
                button.setText(x.getBrandName());
                button.setOnAction(this::selectMenuItemBrand);
                txtAreaDescription.clear();
                txtAreaDescription.appendText(x.getDescription());
                txtFieldProductName.setText(x.getProductType().getName());
                menuButtonSelectBrand.getItems().add(button);*//*
        }*/
    }

    /*   private ArrayList<Ingredient> createIngredientsList() {


     */

    /**
     * fetch&add all the ingredients from DB Drinkle, table Ingredient, to ingredientsList
     *//*

        return ingredientsList;
}*/
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

    }

    private void populateCategoryMenu(ArrayList<IngredientCategory> categoriesData) {
        menuButtonCategory.getItems().clear();
        for (IngredientCategory x : categoriesData) {
            MenuItem category = new MenuItem(x.getName());
            category.setOnAction(this::selectCategory);
            menuButtonCategory.getItems().add(category);
        }
    }

    private void createIngredientsList() {
        ingredientsList.add(new Ingredient(null, null, 1, null, AccessLevel.PUBLIC, null,
                "Absolut Dry", "Absolute Dry description", null, 42, null,
                IngredientCategory.VODKA, null, new Brand("Absolut", "Absolut brand description")));
        ingredientsList.add(new Ingredient(null, null, 2, null, AccessLevel.PUBLIC, null,
                "Absolut Lemon", "Absolute Lemon description", null, 40, null,
                IngredientCategory.VODKA, null, new Brand("Absolut", "Absolut brand description")));
        ingredientsList.add(new Ingredient(null, null, 3, null, AccessLevel.PUBLIC, null,
                "Absolut X", "Absolute X description", null, 45, null,
                IngredientCategory.VODKA, null, new Brand("Absolut", "Absolut brand description")));
        ingredientsList.add(new Ingredient(null, null, 4, null, AccessLevel.PUBLIC, null,
                "Jack Daniel's Black Label", "Jack Daniel's description", null, 42, null,
                IngredientCategory.WHISKEY, null, new Brand("Jack Daniel's", "Jack DAniel's brand description")));
        ingredientsList.add(new Ingredient(null, null, 5, null, AccessLevel.PUBLIC, null,
                "Jack Daniel's XO", "Jack Daniel's XO description", null, 42, null,
                IngredientCategory.WHISKEY, null, new Brand("Jack Daniel's", "Jack Daniel's brand description")));
        ingredientsList.add(new Ingredient(null, null, 6, null, AccessLevel.PUBLIC, null,
                "Jack Daniel's ABC", "Absolute Dry description", null, 42, null,
                IngredientCategory.WHISKEY, null, new Brand("Jack Daniel's", "Jack Daniel'st brand description")));


    }

    private void populateBrandsList(){

    }
}
