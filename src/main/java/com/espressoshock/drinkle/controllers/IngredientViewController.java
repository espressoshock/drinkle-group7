package com.espressoshock.drinkle.controllers;

import com.espressoshock.drinkle.models.Action;
import com.espressoshock.drinkle.models.Brand;
import com.espressoshock.drinkle.models.Ingredient;
import com.espressoshock.drinkle.models.IngredientCategory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class IngredientViewController implements Initializable{

    private ArrayList<Brand> brandsList = new ArrayList<>();
    private ArrayList<IngredientCategory> typesList = new ArrayList<>();
    private ArrayList<Ingredient> ingredientsList = new ArrayList<>();

    @FXML
    private VBox vBoxIngredients;
    @FXML
    private Button  btnCreate, btnAddToInventory, btnInvisibleOne, btnInvisibleTwo, btnInvisibleThree, btnSearch;
    @FXML
    private MenuButton menuButtonGOTO, menuButtonSelectBrand, menuButtonSelectType;
    @FXML
    private CheckBox checkBoxAddFavorite, checkBoxLike;
    @FXML
    private TextArea txtAreaDescription;
    @FXML
    private ImageView imgRecipient, ImgAlternativeOne, imgAlternativeTwo, imgAlternativeThree;
    @FXML
    private TextField txtFieldSimilarWith, txtFieldVisualisations, txtFieldLikes, txtFieldProductName, txtFieldAlcohol, txtFieldPrice, txtFieldSearchOptions;
    @FXML
    private ProgressBar prgBarAlc, prgBarPrice;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setBrandsList();
        setTypesList();
        setIngredientsList();

        //Change Product with the one selected from the alternatives
        btnInvisibleOne.setOnAction(e -> {
        });
        btnInvisibleTwo.setOnAction(e -> {
        });
        btnInvisibleThree.setOnAction(e -> {
        });

        //Opens two scenes to chose from
        menuButtonGOTO.setOnAction(e -> {
        });

        //Will change the scene with Recipe Scene
        btnCreate.setOnAction(e -> {
        });

        //Will add the product to inventory
        btnAddToInventory.setOnAction(e -> {
        });

        //Will set the content of menuButtonSelectBrand
        menuButtonSelectType.setOnAction(e -> {
        });

        //Will set the content of the Vbox
        menuButtonSelectBrand.setOnAction(e -> {
        });

        //Will set the content of Vbox
        btnSearch.setOnAction(e -> {
        });

    }

    // TODO*********************Methods*************************************

    public void setTxtFieldSimilarWith() {
    }

    public void setImageRecipient() {
    }

    public void setImgAlternativeOne() {
    }

    public void setImgAlternativeTwo() {
    }

    public void setImgAlternativeThree() {
    }

    public void setTxtAreaDescription() {
    }

    public void setVBox() {
    }

    public void setBtnSearch() {
    }

    //TODO****************************************************************
    @FXML
    public void selectMenuItemType(ActionEvent e) {
        MenuItem selection = (MenuItem) e.getSource();
        menuButtonSelectBrand.getItems().clear();
        for (Brand x: brandsList){
            if (selection.getText().equals(x.getProductType().getName())) {
                MenuItem button = new MenuItem();
                button.setText(x.getBrandName());
                button.setOnAction(this::selectMenuItemBrand);
                txtAreaDescription.clear();
                txtAreaDescription.appendText(x.getDescription());
                txtFieldProductName.setText(x.getProductType().getName());
                menuButtonSelectBrand.getItems().add(button);
            }
        }
    }
    @FXML
    private void selectMenuItemBrand(ActionEvent e){
        MenuItem selection = (MenuItem) e.getSource();
        for (Ingredient x: ingredientsList){
            if (selection.getText().equals(x.getBrand().getBrandName())) {
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

        Button selectedButton = (Button) e.getSource();
        txtFieldProductName.setText(selectedButton.getText());
        for(Ingredient x: ingredientsList){
            if(x.getName().equals(selectedButton.getText())){
                System.out.println(x.getName());
                txtAreaDescription.clear();
                txtAreaDescription.appendText(x.getDescription());
                txtFieldAlcohol.setText(String.valueOf(x.getAlcohol()));
                txtFieldPrice.setText(String.valueOf(x.getPrice()));
            }
        }
    }

    public void setIngredientsList(){
        ingredientsList.add(new Ingredient("Jameson black","Collection Jameson",IngredientCategory.WHISKEY,Brand.JAMESON,445.45,44.5));ingredientsList.add(new Ingredient("Fortaleza xo","XO Tequila",IngredientCategory.TEQUILA,Brand.FORTALEZA,1280.90,42.5));
        ingredientsList.add(new Ingredient("Absolut Dry","Dry vodka",IngredientCategory.VODKA,Brand.ABSOLUTE,345.45,40));ingredientsList.add(new Ingredient("Smirnoff lemon","Lemon Vodka",IngredientCategory.VODKA,Brand.SMIRNOFF,280.90,38.5));ingredientsList.add(new Ingredient("Jack Daniel's black label", "12 XO Jack Daniel", IngredientCategory.WHISKEY,Brand.JACK_DANIELS,1045.5,40));
        ingredientsList.add(new Ingredient("Grey Goose XO", "12 XO Grey Goose", IngredientCategory.VODKA,Brand.GREY_GOOSE,1450.5,42.5));ingredientsList.add(new Ingredient("Tito's Extra","Collection Tequila",IngredientCategory.TEQUILA,Brand.TITOS,345.45,44));
        ingredientsList.add(new Ingredient("Carolans special","Extra Carolans",IngredientCategory.LIQUEUR,Brand.CAROLANS,230.90,28.5));ingredientsList.add(new Ingredient("Kahlua  x", "Kahlua special", IngredientCategory.LIQUEUR,Brand.KAHLUA,1450.5,36.5));
    }

    private void setTypesList() {
        typesList.add(IngredientCategory.WINE);typesList.add(IngredientCategory.BEER);typesList.add(IngredientCategory.BRANDY);typesList.add(IngredientCategory.CIDER);typesList.add(IngredientCategory.JUICE);typesList.add(IngredientCategory.ICE_TYPE);typesList.add(IngredientCategory.GLASSWARE);typesList.add(IngredientCategory.GARNISH);typesList.add(IngredientCategory.GIN);typesList.add(IngredientCategory.LIQUEUR);typesList.add(IngredientCategory.POWDER);
        typesList.add(IngredientCategory.RUM);typesList.add(IngredientCategory.SYRUP);typesList.add(IngredientCategory.TEQUILA);typesList.add(IngredientCategory.VERMOUTH);typesList.add(IngredientCategory.VODKA);typesList.add(IngredientCategory.WATER);typesList.add(IngredientCategory.WARM_DRINK);typesList.add(IngredientCategory.WHISKEY);typesList.add(IngredientCategory.BITTER);typesList.add(IngredientCategory.FRUIT);typesList.add(IngredientCategory.DAIRY_PRODUCT);
    }

    private void setBrandsList() {
        brandsList.add(Brand.ABSOLUTE);brandsList.add(Brand.GREY_GOOSE);brandsList.add(Brand.BLACK_COW);brandsList.add(Brand.SMIRNOFF);brandsList.add(Brand.STOLICHNAYA);brandsList.add(Brand.TITOS);brandsList.add(Brand.JEAN_MARC);brandsList.add(Brand.JACK_DANIELS);brandsList.add(Brand.JOHNNIE_WALKER);brandsList.add(Brand.JIM_BEAM);brandsList.add(Brand.GLENFARCLASS);brandsList.add(Brand.JAMESON);brandsList.add(Brand.GLENFIDDICH);brandsList.add(Brand.MARTINI);
        brandsList.add(Brand.BEEFEATER);brandsList.add(Brand.GORDONS);brandsList.add(Brand.FORTALEZA);brandsList.add(Brand.JOSE_CUERVO);brandsList.add(Brand.HERANCIA_DE_PLATA);brandsList.add(Brand.BACARDI);brandsList.add(Brand.HAVANA_CLUB);brandsList.add(Brand.SAINT_JAMES);brandsList.add(Brand.GREEN_ISLAND);brandsList.add(Brand.COGNAC);brandsList.add(Brand.ARMAGNAC);brandsList.add(Brand.AMARETTO);brandsList.add(Brand.BAILEYS);
        brandsList.add(Brand.TIA_MARIA);brandsList.add(Brand.MIDNIGHT_ESPRESSO);brandsList.add(Brand.AMARULA);brandsList.add(Brand.CAROLANS);brandsList.add(Brand.CRUZAN);brandsList.add(Brand.CREME_DE_CASSIS);brandsList.add(Brand.GUAVABERRY);brandsList.add(Brand.CURACAO);brandsList.add(Brand.ROSOLIO);brandsList.add(Brand.TRIPLE_SEC);brandsList.add(Brand.VISINATA);brandsList.add(Brand.ANISETTE);brandsList.add(Brand.CONTRATTO);brandsList.add(Brand.COCCHI);
        brandsList.add(Brand.MASATICA);brandsList.add(Brand.JAGERMEISTER);brandsList.add(Brand.UNICUM);brandsList.add(Brand.DRAMBUIE);brandsList.add(Brand.DISARONNO);brandsList.add(Brand.NOCELLO);brandsList.add(Brand.ADVOCAAT);brandsList.add(Brand.ROCK_RYE);brandsList.add(Brand.BANANAS);brandsList.add(Brand.CAMPARI);brandsList.add(Brand.GRAND_MARNIER);brandsList.add(Brand.LIMONCELLO);brandsList.add(Brand.MARASCHINO);
        brandsList.add(Brand.PAMA);brandsList.add(Brand.SAUVIGNON_BLANC);brandsList.add(Brand.CHARDONNAY);brandsList.add(Brand.PINOT_GRIS);brandsList.add(Brand.CABERNET_SAUVIGNON);brandsList.add(Brand.MERLOT);brandsList.add(Brand.SHIRAZ);brandsList.add(Brand.PINOT_NOIR);brandsList.add(Brand.GRENACHE);brandsList.add(Brand.DOM_PERIGNON);brandsList.add(Brand.MOET_CHANDON);brandsList.add(Brand.CRISTAL);brandsList.add(Brand.CINZANO);
        brandsList.add(Brand.PERRIER_JOUET);brandsList.add(Brand.PROSECCO);brandsList.add(Brand.LOUIS_BOUILOT);brandsList.add(Brand.DEPREVILLE);brandsList.add(Brand.MAGNERS);brandsList.add(Brand.SOMERSBY);brandsList.add(Brand.POMAGNE);brandsList.add(Brand.BULMERS);brandsList.add(Brand.FIZZ);brandsList.add(Brand.MANZANA_VERDE);brandsList.add(Brand.NOIAU_DE_POISSY);brandsList.add(Brand.MIDORI);brandsList.add(Brand.COINTREAU);
        brandsList.add(Brand.HEINEKEN);brandsList.add(Brand.CARLSBERG);brandsList.add(Brand.BUDWEISER);brandsList.add(Brand.CORONA);brandsList.add(Brand.PAULANER);brandsList.add(Brand.STELLA_ARTIOS);brandsList.add(Brand.BIRRA_MORETTI);brandsList.add(Brand.SKOL);brandsList.add(Brand.GALLIANO);brandsList.add(Brand.FERNET);brandsList.add(Brand.CREME_DE_MENTHE);brandsList.add(Brand.KAHLUA);brandsList.add(Brand.SAMBUCA);brandsList.add(Brand.SHERIDANS);


    }
}