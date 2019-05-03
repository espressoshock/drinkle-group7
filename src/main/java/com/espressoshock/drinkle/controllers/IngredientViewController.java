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
               menuButtonSelectBrand.getItems().add(button);
            }
        }
    }
    @FXML
    public void selectMenuItemBrand(ActionEvent e){
        MenuItem selection = (MenuItem) e.getSource();
        System.out.println(selection.getText());
        for (Brand x: brandsList){
            if (selection.getText().equals(x.getBrandName())) {
                Button button = new Button(x.getBrandName());
                vBoxIngredients.getChildren();
            }
        }
    }

    @FXML
    public void selectVboxButton(ActionEvent e) {

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

/*    REPLACED WITH ENUM BRAND

        brandsList.add(new Brand("Absolute", "Vodka", IngredientCategory.VODKA));
        brandsList.add(new Brand("Grey Goose", "Vodka", IngredientCategory.VODKA));
        brandsList.add(new Brand("Black Cow", "Vodka", IngredientCategory.VODKA));
        brandsList.add(new Brand("Smirnoff", "Vodka", IngredientCategory.VODKA));
        brandsList.add(new Brand("Stolichnaya", "Vodka", IngredientCategory.VODKA));
        brandsList.add(new Brand("Tito's", "Vodka", IngredientCategory.VODKA));
        brandsList.add(new Brand("Jean-Marc", "Vodka", IngredientCategory.VODKA));
        brandsList.add(new Brand("Jack Daniel's", "Whiskey", IngredientCategory.WHISKEY));
        brandsList.add(new Brand("Johnnie Walker", "Whiskey", IngredientCategory.WHISKEY));
        brandsList.add(new Brand("Jim Beam", "Whiskey", IngredientCategory.WHISKEY));
        brandsList.add(new Brand("Glenfarclass", "Whiskey", IngredientCategory.WHISKEY));
        brandsList.add(new Brand("Jameson", "Whiskey", IngredientCategory.WHISKEY));
        brandsList.add(new Brand("Glenfiddich", "Whiskey", IngredientCategory.WHISKEY));
        brandsList.add(new Brand("Martini", "Vermouth", IngredientCategory.VERMOUTH));
        brandsList.add(new Brand("Cinzano", "Vermouth", IngredientCategory.VERMOUTH));
        brandsList.add(new Brand("Contratto", "Vermouth", IngredientCategory.VERMOUTH));
        brandsList.add(new Brand("Cocchi", "Vermouth", IngredientCategory.VERMOUTH));
        brandsList.add(new Brand("Beefeater", "Gin", IngredientCategory.GIN));
        brandsList.add(new Brand("Gordon's", "Gin", IngredientCategory.GIN));
        brandsList.add(new Brand("Fortaleza", "Tequila", IngredientCategory.TEQUILA));
        brandsList.add(new Brand("Jose Cuervo", "Tequila", IngredientCategory.TEQUILA));
        brandsList.add(new Brand("Herencia de Plata", "Tequila", IngredientCategory.TEQUILA));
        brandsList.add(new Brand("Bacardi", "Rum", IngredientCategory.RUM));
        brandsList.add(new Brand("Havana Club", "Rum", IngredientCategory.RUM));
        brandsList.add(new Brand("Saint James", "Rum", IngredientCategory.RUM));
        brandsList.add(new Brand("Green Island", "Rum", IngredientCategory.RUM));
        brandsList.add(new Brand("Cognac", "Cognac", IngredientCategory.BRANDY));
        brandsList.add(new Brand("Armagnac", "Armagnac", IngredientCategory.BRANDY));
        brandsList.add(new Brand("Amaretto", "Cream", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Bailey's", "Cream", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Cointreau", "Orange", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Kahlua", "Coffee", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Sambuca", "Anise", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Sheridan's", "Coffee", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Tia Maria", "Coffee", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Midnight Espresso", "Coffee", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Amarula", "Marula", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Carolans", "Cream", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Cruzan", "Cream", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Creme de cassis", "Blackcurrant", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Guavaberry", "Berry", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Curacao", "Bitter Orange", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Rosolio", "Rose", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Triple Sec", "Orange", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Visinata", "Sour Cherry", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Anisette", "Anise", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Creme de menthe", "Mint", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Fernet", "Herbs", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Galliano", "Herbs", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Masatica", "Mastic resin", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Jagermaster", "Herbs", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Unicum", "Herbs", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Drambuie", "Honey&Scotch", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Amaretto", "Nuts", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Disaronno", "Apricot kernel oil", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Nocello", "Nuts", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Advocaat", "Vanilla & eggyolk", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Rock&Rye", "Whiskey&Citrus", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("99 bananas", "Bananas", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Campari", "Bitter orange", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Grand Marnier", "Orange", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Limoncello", "Lemon", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Maraschino", "Cherry", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Midori", "Melone", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Manzana Verde", "Green Apple", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Noiau de Poissy", "Apricot", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Pama", "Pomegranate", IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Sauvignon Blanc", "White Wine", IngredientCategory.WINE));
        brandsList.add(new Brand("Chardonnay", "White Wine", IngredientCategory.WINE));
        brandsList.add(new Brand("Pinot Gris", "White Wine", IngredientCategory.WINE));
        brandsList.add(new Brand("Cabernet Sauvignon", "Red Wine", IngredientCategory.WINE));
        brandsList.add(new Brand("Merlot", "Red Wine", IngredientCategory.WINE));
        brandsList.add(new Brand("Shiraz", "Red Wine", IngredientCategory.WINE));
        brandsList.add(new Brand("Pinot Noir", "Red Wine", IngredientCategory.WINE));
        brandsList.add(new Brand("Grenache", "Red Wine", IngredientCategory.WINE));
        brandsList.add(new Brand("Dom Perignon", "Champagne", IngredientCategory.WINE));
        brandsList.add(new Brand("Moet&Chandon", "Champagne", IngredientCategory.WINE));
        brandsList.add(new Brand("Cristal", "Champagne", IngredientCategory.WINE));
        brandsList.add(new Brand("Perrier Jouet", "Champagne", IngredientCategory.WINE));
        brandsList.add(new Brand("Prosecco", "Sparkling Wine", IngredientCategory.WINE));
        brandsList.add(new Brand("Louis Bouilot", "Sparkling Wine", IngredientCategory.WINE));
        brandsList.add(new Brand("Depreville", "Sparkling Wine", IngredientCategory.WINE));
        brandsList.add(new Brand("Magners", "Pear^Apple", IngredientCategory.CIDER));
        brandsList.add(new Brand("Somersby", "Apple", IngredientCategory.CIDER));
        brandsList.add(new Brand("Pomagne", "Apple", IngredientCategory.CIDER));
        brandsList.add(new Brand("Bulmers", "Pear", IngredientCategory.CIDER));
        brandsList.add(new Brand("Fizz", "Fruits", IngredientCategory.CIDER));
        brandsList.add(new Brand("Heineken", "Beer", IngredientCategory.BEER));
        brandsList.add(new Brand("Carlsberg", "Beer", IngredientCategory.BEER));
        brandsList.add(new Brand("Budweiser", "Beer", IngredientCategory.BEER));
        brandsList.add(new Brand("Corona", "Beer", IngredientCategory.BEER));
        brandsList.add(new Brand("Paulaner", "Beer", IngredientCategory.BEER));
        brandsList.add(new Brand("Stella Artios", "Beer", IngredientCategory.BEER));
        brandsList.add(new Brand("Birra Moretti", "Beer", IngredientCategory.BEER));
        brandsList.add(new Brand("Skol", "Beer", IngredientCategory.BEER));*/
/* This are not brands, but ingredients
        brandsList.add(new Brand("Ice Cubes","Ice cubes",IngredientCategory.ICE_TYPE));
        brandsList.add(new Brand("Smashed Ice","Smashed Ice",IngredientCategory.ICE_TYPE));
        brandsList.add(new Brand("Frozen berries","Frozen Fruits",IngredientCategory.ICE_TYPE));
        brandsList.add(new Brand("Frozen Grapes","Frozen cube",IngredientCategory.ICE_TYPE));
        brandsList.add(new Brand("Green Olives","Garnish",IngredientCategory.GARNISH));
        brandsList.add(new Brand("Sliced lime","Garnish",IngredientCategory.GARNISH));
        brandsList.add(new Brand("Sliced orange","Garnish",IngredientCategory.GARNISH));
        brandsList.add(new Brand("Sliced tomato","Garnish",IngredientCategory.GARNISH));
        brandsList.add(new Brand("Celery stick","Garnish",IngredientCategory.GARNISH));
        brandsList.add(new Brand("Sliced lemon ","Garnish",IngredientCategory.GARNISH));
        brandsList.add(new Brand("Sliced cucumber","Garnish",IngredientCategory.GARNISH));
        brandsList.add(new Brand("Cherry confit","Garnish",IngredientCategory.GARNISH));
        brandsList.add(new Brand("Parsley leif","Garnish",IngredientCategory.GARNISH));
        brandsList.add(new Brand("Mint leaf","Garnish",IngredientCategory.GARNISH));
        brandsList.add(new Brand("Rosemary","Garnish",IngredientCategory.GARNISH));
        brandsList.add(new Brand("Sugar","Garnish",IngredientCategory.GARNISH));
        brandsList.add(new Brand("Salt","Garnish",IngredientCategory.GARNISH));
        brandsList.add(new Brand("Tabasco","Garnish",IngredientCategory.GARNISH));
        brandsList.add(new Brand("Soy sous","Garnish",IngredientCategory.GARNISH));
        brandsList.add(new Brand("Cinnamon","Garnish",IngredientCategory.GARNISH));
        brandsList.add(new Brand("Martini glass","Martini glass",IngredientCategory.GLASSWARE));
        brandsList.add(new Brand("Rocks glass","Whiskey glass",IngredientCategory.GLASSWARE));
        brandsList.add(new Brand("Shot glass","Shot glass",IngredientCategory.GLASSWARE));
        brandsList.add(new Brand("Cognac glass","Cognac glass",IngredientCategory.GLASSWARE));
        brandsList.add(new Brand("Highball glass","Highball glass",IngredientCategory.GLASSWARE));
        brandsList.add(new Brand("Champagne flute","Champagne glass",IngredientCategory.GLASSWARE));
        brandsList.add(new Brand("Margarita glass","Martini glass",IngredientCategory.GLASSWARE));
        brandsList.add(new Brand("Hurricane glass","Cocktail glass",IngredientCategory.GLASSWARE));
        brandsList.add(new Brand("Espresso","Espresso coffee",IngredientCategory.WARM_DRINK));
        brandsList.add(new Brand("Infusion","Tea",IngredientCategory.WARM_DRINK));
        brandsList.add(new Brand("Hot chocolate","Chocolate",IngredientCategory.WARM_DRINK));
        brandsList.add(new Brand("Still watter","Watter",IngredientCategory.WATER));
        brandsList.add(new Brand("Sparkling watter","Watter",IngredientCategory.WATER));
        brandsList.add(new Brand("Flavoured watter","Watter",IngredientCategory.WATER));
        brandsList.add(new Brand("Tomatoes essence","Watter",IngredientCategory.WATER));
        brandsList.add(new Brand("Sugar syrup","Syrup",IngredientCategory.SYRUP));
        brandsList.add(new Brand("Berries syrup","Syrup",IngredientCategory.SYRUP));
        brandsList.add(new Brand("Milk","Milk",IngredientCategory.DAIRY_PRODUCT));
        brandsList.add(new Brand("Cream","Cream",IngredientCategory.GARNISH));
        brandsList.add(new Brand("Egg whites","Garnish",IngredientCategory.GARNISH));
        brandsList.add(new Brand("Cinnamon","Powder",IngredientCategory.POWDER));
        brandsList.add(new Brand("Sugar","Powder",IngredientCategory.POWDER));
        brandsList.add(new Brand("Cocoa","Powder",IngredientCategory.POWDER));*/
    }
}