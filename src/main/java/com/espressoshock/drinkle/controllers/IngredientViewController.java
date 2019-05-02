package com.espressoshock.drinkle.controllers;

import com.espressoshock.drinkle.models.Brand;
import com.espressoshock.drinkle.models.IngredientCategory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class IngredientViewController implements Initializable {

    private ArrayList<Brand> brandsList = new ArrayList<>();
    private ArrayList<IngredientCategory> typesList = new ArrayList<>();


    ObservableList<Brand> ingredientsBrand = FXCollections.observableArrayList(brandsList);
    ObservableList<IngredientCategory> ingredientsType = FXCollections.observableArrayList(typesList);


    @FXML
    private Button button1, button2,button3,button4,button5,button6,button7,button8,button9,button10,
            button11, button12,button13,button14,button15,button16,button17,button18,button19,button20,
            button21, button22,button23,button24,button25,button26,button27,button28,button29,button30,
            button31, button32,button33,button34,button35,button36,button37,button38,button39,button40,
            button41, button42,button43,button44,button45,button46,button47,button48, btnCreate,
            btnAddToInventory, btnInvisibleOne, btnInvisibleTwo, btnInvisibleThree, btnSearch;
    @FXML
    private AnchorPane anchorPaneIngredients;
    @FXML
    private VBox vBoxIngredients;
    @FXML
    private ScrollPane scrollPaneIngredients;
    @FXML
    private ComboBox comboBoxBrands, comboBoxTypes;
    @FXML
    private TextArea txtAreaDescription;
    @FXML
    private ImageView imgRecipient,ImgAlternativeOne,imgAlternativTwo,imgAlternativeThree;
    @FXML
    private TextField txtFieldSimilarWith, txtFieldVisualisations, txtFieldLikes, txtFieldProductName,txtFieldAlcohol,txtFieldPrice,txtFieldSearchOptions;
    @FXML
    private ProgressBar prgBarAlc, prgBarPrice;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setTypesList();
        setBrandsList();

        btnInvisibleOne.setOnAction(e->{});
        btnInvisibleTwo.setOnAction(e->{});
        btnInvisibleThree.setOnAction(e->{});

        comboBoxTypes.setItems(ingredientsType);
        comboBoxTypes.getItems().addAll();
        comboBoxBrands.getItems().addAll();

        txtFieldSearchOptions.setOnAction(e->{});
        txtFieldSimilarWith.setOnAction(e->{});

        btnCreate.setOnAction(e->{});
        btnAddToInventory.setOnAction(e->{});

    }

   // TODO*********************Methods*************************************

    public void setTxtFieldMoreFrom(){}
    public void setImageRecipient(){}
    public void setImageCornerUp(){}
    public void setImageCornerDown(){}
    public void setTxtAreaDescription(){}
    public void setVBox(){}
    public String readComboBoxBrands(ActionEvent event){String brand="product brand"; return brand;}
    public String readComboBoxTypes(ActionEvent event){String type="product type"; return type;}
    public char[] readTxtFieldSearchOptions(){char[] text = new char[1]; return text;}

    //TODO****************************************************************

    public void setBrandsList(){
        brandsList.add(new Brand("Absolute","Vodka",IngredientCategory.VODKA));
        brandsList.add(new Brand("Grey Goose","Vodka",IngredientCategory.VODKA));
        brandsList.add(new Brand("Black Cow","Vodka",IngredientCategory.VODKA));
        brandsList.add(new Brand("Smirnoff","Vodka",IngredientCategory.VODKA));
        brandsList.add(new Brand("Stolichnaya","Vodka",IngredientCategory.VODKA));
        brandsList.add(new Brand("Tito's","Vodka",IngredientCategory.VODKA));
        brandsList.add(new Brand("Jean-Marc","Vodka",IngredientCategory.VODKA));
        brandsList.add(new Brand("Jack Daniel's","Whiskey",IngredientCategory.WHISKEY));
        brandsList.add(new Brand("Johnnie Walker","Whiskey",IngredientCategory.WHISKEY));
        brandsList.add(new Brand("Jim Beam","Whiskey",IngredientCategory.WHISKEY));
        brandsList.add(new Brand("Glenfarclass","Whiskey",IngredientCategory.WHISKEY));
        brandsList.add(new Brand("Jameson","Whiskey",IngredientCategory.WHISKEY));
        brandsList.add(new Brand("Glenfiddich","Whiskey",IngredientCategory.WHISKEY));
        brandsList.add(new Brand("Martini","Vermouth",IngredientCategory.VERMOUTH));
        brandsList.add(new Brand("Cinzano","Vermouth",IngredientCategory.VERMOUTH));
        brandsList.add(new Brand("Contratto","Vermouth",IngredientCategory.VERMOUTH));
        brandsList.add(new Brand("Cocchi","Vermouth",IngredientCategory.VERMOUTH));
        brandsList.add(new Brand("Beefeater","Gin",IngredientCategory.GIN));
        brandsList.add(new Brand("Gordon's","Gin",IngredientCategory.GIN));
        brandsList.add(new Brand("Fortaleza","Tequila",IngredientCategory.TEQUILA));
        brandsList.add(new Brand("Jose Cuervo","Tequila",IngredientCategory.TEQUILA));
        brandsList.add(new Brand("Herencia de Plata","Tequila",IngredientCategory.TEQUILA));
        brandsList.add(new Brand("Bacardi","Rum",IngredientCategory.RUM));
        brandsList.add(new Brand("Havana Club","Rum",IngredientCategory.RUM));
        brandsList.add(new Brand("Saint James","Rum",IngredientCategory.RUM));
        brandsList.add(new Brand("Green Island","Rum",IngredientCategory.RUM));
        brandsList.add(new Brand("Cognac","Cognac",IngredientCategory.BRANDY));
        brandsList.add(new Brand("Armagnac","Armagnac",IngredientCategory.BRANDY));
        brandsList.add(new Brand("Amaretto","Cream",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Bailey's","Cream",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Cointreau","Orange",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Kahlua","Coffee",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Sambuca","Anise",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Sheridan's","Coffee",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Tia Maria","Coffee",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Midnight Espresso","Coffee",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Amarula","Marula",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Carolans","Cream",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Cruzan","Cream",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Creme de cassis","Blackcurrant",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Guavaberry","Berry",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Curacao","Bitter Orange",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Rosolio","Rose",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Triple Sec","Orange",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Visinata","Sour Cherry",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Anisette","Anise",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Creme de menthe","Mint",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Fernet","Herbs",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Galliano","Herbs",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Masatica","Mastic resin",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Jagermaster","Herbs",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Unicum","Herbs",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Drambuie","Honey&Scotch",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Amaretto","Nuts",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Disaronno","Apricot kernel oil",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Nocello","Nuts",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Advocaat","Vanilla & eggyolk",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Rock&Rye","Whiskey&Citrus",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("99 bananas","Bananas",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Campari","Bitter orange",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Grand Marnier","Orange",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Limoncello","Lemon",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Maraschino","Cherry",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Midori","Melone",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Manzana Verde","Green Apple",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Noiau de Poissy","Apricot",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Pama","Pomegranate",IngredientCategory.LIQUEUR));
        brandsList.add(new Brand("Sauvignon Blanc","White Wine",IngredientCategory.WINE));
        brandsList.add(new Brand("Chardonnay","White Wine",IngredientCategory.WINE));
        brandsList.add(new Brand("Pinot Gris","White Wine",IngredientCategory.WINE));
        brandsList.add(new Brand("Cabernet Sauvignon","Red Wine",IngredientCategory.WINE));
        brandsList.add(new Brand("Merlot","Red Wine",IngredientCategory.WINE));
        brandsList.add(new Brand("Shiraz","Red Wine",IngredientCategory.WINE));
        brandsList.add(new Brand("Pinot Noir","Red Wine",IngredientCategory.WINE));
        brandsList.add(new Brand("Grenache","Red Wine",IngredientCategory.WINE));
        brandsList.add(new Brand("Dom Perignon","Champagne",IngredientCategory.WINE));
        brandsList.add(new Brand("Moet&Chandon","Champagne",IngredientCategory.WINE));
        brandsList.add(new Brand("Cristal","Champagne",IngredientCategory.WINE));
        brandsList.add(new Brand("Perrier Jouet","Champagne",IngredientCategory.WINE));
        brandsList.add(new Brand("Prosecco","Sparkling Wine",IngredientCategory.WINE));
        brandsList.add(new Brand("Louis Bouilot","Sparkling Wine",IngredientCategory.WINE));
        brandsList.add(new Brand("Depreville","Sparkling Wine",IngredientCategory.WINE));
        brandsList.add(new Brand("Magners","Pear^Apple",IngredientCategory.CIDER));
        brandsList.add(new Brand("Somersby","Apple",IngredientCategory.CIDER));
        brandsList.add(new Brand("Pomagne","Apple",IngredientCategory.CIDER));
        brandsList.add(new Brand("Bulmers","Pear",IngredientCategory.CIDER));
        brandsList.add(new Brand("Fizz","Fruits",IngredientCategory.CIDER));
        brandsList.add(new Brand("Heineken","Beer",IngredientCategory.BEER));
        brandsList.add(new Brand("Carlsberg","Beer",IngredientCategory.BEER));
        brandsList.add(new Brand("Budweiser","Beer",IngredientCategory.BEER));
        brandsList.add(new Brand("Corona","Beer",IngredientCategory.BEER));
        brandsList.add(new Brand("Paulaner","Beer",IngredientCategory.BEER));
        brandsList.add(new Brand("Stella Artios","Beer",IngredientCategory.BEER));
        brandsList.add(new Brand("Birra Moretti","Beer",IngredientCategory.BEER));
        brandsList.add(new Brand("Skol","Beer",IngredientCategory.BEER));
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

    public void setTypesList(){
        typesList.add(IngredientCategory.WINE);
        typesList.add(IngredientCategory.BEER);
        typesList.add(IngredientCategory.BRANDY);
        typesList.add(IngredientCategory.CIDER);
        typesList.add(IngredientCategory.JUICE);
        typesList.add(IngredientCategory.ICE_TYPE);
        typesList.add(IngredientCategory.GLASSWARE);
        typesList.add(IngredientCategory.GARNISH);
        typesList.add(IngredientCategory.GIN);
        typesList.add(IngredientCategory.LIQUEUR);
        typesList.add(IngredientCategory.POWDER);
        typesList.add(IngredientCategory.RUM);
        typesList.add(IngredientCategory.SYRUP);
        typesList.add(IngredientCategory.TEQUILA);
        typesList.add(IngredientCategory.VERMOUTH);
        typesList.add(IngredientCategory.VODKA);
        typesList.add(IngredientCategory.WATER);
        typesList.add(IngredientCategory.WARM_DRINK);
        typesList.add(IngredientCategory.WHISKEY);
        typesList.add(IngredientCategory.BITTER);
        typesList.add(IngredientCategory.FRUIT);
        typesList.add(IngredientCategory.DAIRY_PRODUCT);
    }

    public void setOnActionIngredientsVBox(){
        button1.setOnAction(e->{});
        button2.setOnAction(e->{});
        button3.setOnAction(e->{});
        button4.setOnAction(e->{});
        button5.setOnAction(e->{});
        button6.setOnAction(e->{});
        button7.setOnAction(e->{});
        button8.setOnAction(e->{});
        button9.setOnAction(e->{});
        button10.setOnAction(e->{});
        button11.setOnAction(e->{});
        button12.setOnAction(e->{});
        button13.setOnAction(e->{});
        button14.setOnAction(e->{});
        button15.setOnAction(e->{});
        button16.setOnAction(e->{});
        button17.setOnAction(e->{});
        button18.setOnAction(e->{});
        button19.setOnAction(e->{});
        button20.setOnAction(e->{});
        button21.setOnAction(e->{});
        button22.setOnAction(e->{});
        button23.setOnAction(e->{});
        button24.setOnAction(e->{});
        button25.setOnAction(e->{});
        button26.setOnAction(e->{});
        button27.setOnAction(e->{});
        button28.setOnAction(e->{});
        button29.setOnAction(e->{});
        button30.setOnAction(e->{});
        button31.setOnAction(e->{});
        button32.setOnAction(e->{});
        button33.setOnAction(e->{});
        button34.setOnAction(e->{});
        button35.setOnAction(e->{});
        button36.setOnAction(e->{});
        button37.setOnAction(e->{});
        button38.setOnAction(e->{});
        button39.setOnAction(e->{});
        button40.setOnAction(e->{});
        button41.setOnAction(e->{});
        button42.setOnAction(e->{});
        button43.setOnAction(e->{});
        button44.setOnAction(e->{});
        button45.setOnAction(e->{});
        button46.setOnAction(e->{});
        button47.setOnAction(e->{});
        button48.setOnAction(e->{});
    }
}