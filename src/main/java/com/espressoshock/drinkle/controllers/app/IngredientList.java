package com.espressoshock.drinkle.controllers.app;

import com.espressoshock.drinkle.models.*;
import com.espressoshock.drinkle.models.Package;
import com.espressoshock.drinkle.viewLoader.EventDispatcherAdapter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;


public class IngredientList extends EventDispatcherAdapter implements Initializable {

    private ArrayList<Ingredient> ingredientsList = new ArrayList<>();
    private ArrayList<IngredientCategory> categories = new ArrayList<>();
    private ArrayList<BrandsEnum> brandsList = new ArrayList<>();

    @FXML
    private VBox vBoxIngredients;
    @FXML
    private Button btnCreateIngredient, btnAddIngredient,btnChoose, btnSearch;
    @FXML
    private CheckBox checkBoxAddToList, checkBoxLike;
    @FXML
    private MenuButton menuButtonCategory, menuButtonBrands;
    @FXML
    private TextArea txtAreaDescription;
    @FXML
    private TextField txtFieldSearchOption, txtFieldProductName, txtFieldSimilarWithProduct;
    @FXML
    private ImageView imgViewProduct, imgViewSimilarProduct1;

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
    private void selectBtnSearch() {
    }
    @FXML
    private void selectCategory(ActionEvent e) {
        vBoxIngredients.getChildren().clear();
        menuButtonBrands.setText("Brands");
        MenuItem selection = (MenuItem) e.getSource();
        menuButtonCategory.setText(selection.getText());
        menuButtonBrands.getItems().clear();

        for (BrandsEnum brandsEnum : brandsList) {
            if (selection.getText().equals(brandsEnum.getProductType().getName())){
                MenuItem button = new MenuItem();
                button.setText(brandsEnum.getBrandName());
                button.setOnAction(this::selectBrand);
                menuButtonBrands.getItems().add(button);
            }
        }
    }
    @FXML
    private void selectBrand(ActionEvent e){
        MenuItem selection = (MenuItem) e.getSource();
        menuButtonBrands.setText(selection.getText());
        vBoxIngredients.getChildren().clear();
        for(Ingredient x: ingredientsList){
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
        Button selection= (Button) e.getSource();
        for (Ingredient x: ingredientsList) {
            if(x.getName().equals(selection.getText())){
                txtAreaDescription.setText(x.getDescription());
                txtFieldProductName.setText(x.getName());
                txtFieldSimilarWithProduct.setText(x.getName());
                for(Ingredient y: ingredientsList){
                    if(x.getBrandsEnum().getBrandName().equals(y.getBrandsEnum().getBrandName())){
                        if(!x.getName().equals(y.getName())){
                            btnChoose.setText(y.getName());
                        }
                    }
                }
            }
        }
    }
    @FXML
    private void selectButtonChoose(ActionEvent e){
        Button alternative= (Button) e.getSource();
        for (Ingredient x: ingredientsList) {
            if(alternative.getText().equals(x.getName())){
                txtFieldSimilarWithProduct.setText(x.getName());
                txtAreaDescription.setText(x.getDescription());
                txtFieldProductName.setText(x.getName());
                for(Ingredient z: ingredientsList){
                    if(x.getBrandsEnum().getBrandName().equals(z.getBrandsEnum().getBrandName())){
                        if(!x.getName().equals(z.getName())){
                            btnChoose.setText(z.getName());
                        }
                    }
                }
            }
        }
    }
    @FXML
    private void createNewIngredient(ActionEvent e)throws Exception{
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/app/create-ingredient.fxml"));
            Scene newMenu = new Scene(root);
            primaryStage.setTitle("Drinkle - Create new Ingredient");
            primaryStage.setResizable(false);
            primaryStage.setScene(newMenu);
            primaryStage.show();

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
                IngredientCategory.VODKA, new Package(750,Unit.MILLILITER,new BigDecimal("0.03")), BrandsEnum.ABSOLUTE));
        ingredientsList.add(new Ingredient(null, null, 2, null, AccessLevel.PUBLIC, null,
                "Absolut Lemon", "Absolute Lemon description", null, 40, null,
                IngredientCategory.VODKA, null, BrandsEnum.ABSOLUTE));
        ingredientsList.add(new Ingredient(null, null, 3, null, AccessLevel.PUBLIC, null,
                "Absolut X", "Absolute X description", null, 45, null,
                IngredientCategory.VODKA, null, BrandsEnum.ABSOLUTE));
        ingredientsList.add(new Ingredient(null, null, 4, null, AccessLevel.PUBLIC, null,
                "Jack Daniel's Black Label", "Jack Daniel's description", null, 42, null,
                IngredientCategory.WHISKEY, null,BrandsEnum.JACK_DANIELS));
        ingredientsList.add(new Ingredient(null, null, 5, null, AccessLevel.PUBLIC, null,
                "Jack Daniel's XO", "Jack Daniel's XO description", null, 42, null,
                IngredientCategory.WHISKEY, null, BrandsEnum.JACK_DANIELS));
        ingredientsList.add(new Ingredient(null, null, 6, null, AccessLevel.PUBLIC, null,
                "Jack Daniel's ABC", "Absolute Dry description", null, 42, null,
                IngredientCategory.WHISKEY, null, BrandsEnum.JACK_DANIELS));
    }
    private void populateBrandsList(){
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

    }
}
