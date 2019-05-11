package com.espressoshock.drinkle.controllers.app;

import com.espressoshock.drinkle.models.Brand;
import com.espressoshock.drinkle.models.Ingredient;
import com.espressoshock.drinkle.models.IngredientCategory;
import com.espressoshock.drinkle.viewLoader.EventDispatcherAdapter;
//import com.espressoshock.drinkle.viewLoader.ViewMetadata;
//import javafx.event.ActionEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import java.util.ArrayList;


public class IngredientList extends EventDispatcherAdapter {

    private ArrayList<Brand> brandsList = new ArrayList<>();
    private ArrayList<Ingredient> ingredientsList = new ArrayList<>();
    @FXML
    private VBox vBoxIngredients;
    @FXML
    private Button btnCreate, btnAddToInventory, btnSearch, btnChoose1, btnChoose2;
    @FXML
    private CheckBox checkBoxAddToList, checkBoxLike;
    @FXML
    private MenuButton menuButtonBrands, menuButtonProducts, menuBtnGoTo;
    @FXML
    private ProgressBar progressBarAlcohol, progressBarPrice;
    @FXML
    private TextArea txtAreaDescription;
    @FXML
    private TextField  txtFieldPrice, txtFieldSearchOption, txtFieldVisualisations, txtFieldLike, txtFieldProductName, txtFieldSimilarWithProduct, txtFieldProductOneName, txtFieldProductTwoName, txtFieldAlcohol;
    @FXML
    private ImageView imgViewProduct, imgViewSimilarProduct1, imgViewSimilarProduct2;

    public void changeView(){/*super.dispatchViewChangeRequest(ViewMetadata.)*/}


    // TODO*********************Methods*************************************
    @FXML
    private void selectSimilarProductPicture(){}
    @FXML
    private void selectLike(){}
    @FXML
    private void selectAddToFavorit(){}
    @FXML
    private void selectCreate(){}
    @FXML
    private void selectGoToMenuButton() {}
    @FXML
    private void selectBtnSearch() {}
    @FXML
    private void selectMenuItemBrand() {}
    @FXML
    private void selectMenuItemCategory() {}
    private ArrayList<Ingredient> setIngredientsList(IngredientCategory selection){return null;}





}

