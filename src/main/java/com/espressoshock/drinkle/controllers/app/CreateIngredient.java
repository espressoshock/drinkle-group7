package com.espressoshock.drinkle.controllers.app;

import com.espressoshock.drinkle.models.BrandsEnum;
import com.espressoshock.drinkle.models.IngredientCategory;
import com.espressoshock.drinkle.viewLoader.EventDispatcherAdapter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CreateIngredient extends EventDispatcherAdapter implements Initializable {

    private ArrayList<IngredientCategory> categories = new ArrayList<>();
    private ArrayList<BrandsEnum> brandsList = new ArrayList<>();

    @FXML
    private TextField txtName,txtDescription,txtCategory,txtPrice,txtAbv,txtBrand;
    @FXML
    private MenuButton menuButtonCategory, menuButtonBrand;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        createCategoryList();
        populateBrandsList();
        populateCategoryMenu(categories);
    }

    @FXML
    private void selectCategory(ActionEvent e) {

        menuButtonBrand.setText("Brand");
        MenuItem sel = (MenuItem) e.getSource();
        menuButtonCategory.setText(sel.getText());
        txtCategory.setText(sel.getText());
        menuButtonBrand.getItems().clear();
        for (BrandsEnum bE : brandsList) {
            if (sel.getText().equals(bE.getProductType().getName())){
                MenuItem button = new MenuItem();
                button.setText(bE.getBrandName());
                button.setOnAction(this::selectBrand);
                menuButtonBrand.getItems().add(button);
            }
        }
    }
    @FXML
    private void selectBrand(ActionEvent e){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(txtCategory.getText().isEmpty()){
                alert.setContentText("Please chose a Category");
                alert.setHeaderText("Empty Category!");
                alert.showAndWait();
        }else{
            MenuItem selection =(MenuItem) e.getSource();
            txtBrand.setText(selection.getText());
        }
    }
    @FXML
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
    @FXML
    private void selectAdd(ActionEvent e){
        Button add = (Button) e.getSource();
        Alert fieldEmpty = new Alert(Alert.AlertType.WARNING);
        if(txtCategory.getText().isEmpty()||txtBrand.getText().isEmpty()||txtAbv.getText().isEmpty()||txtPrice.getText().isEmpty()||
                txtName.getText().isEmpty()){
            fieldEmpty.setContentText("Please fill all the fields!");
            fieldEmpty.setHeaderText("Empty fields!");
            fieldEmpty.showAndWait();
        }else{
         /**Add the ingredient to DB********************************/
         fieldEmpty.setAlertType(Alert.AlertType.INFORMATION);
         fieldEmpty.setContentText("The ingredient "+txtName.getText()+" is now created");
         fieldEmpty.setHeaderText("New Ingredient Available");
         fieldEmpty.showAndWait();
        }
    }
    @FXML
    private void selectRestart(ActionEvent e){
        txtName.setText(" ");
        txtCategory.setText(" ");
        txtBrand.setText(" ");
        txtPrice.setText(" ");
        txtAbv.setText(" ");
        txtDescription.setText(" ");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("You can create a new Ingredient");
        alert.setHeaderText("Restart!");
        alert.showAndWait();
    }
    @FXML
    private void checkAbvInput(KeyEvent keyEvent){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        if(!"0123456789.".contains(keyEvent.getCharacter())){
            keyEvent.consume();
            alert.setHeaderText("Wrong Input!");
            alert.setContentText("Please enter a float");
            alert.showAndWait();
            txtAbv.setText(" ");
        }
    }
    @FXML
    private void checPriceInput(KeyEvent keyEvent){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        if(!"0123456789".contains(keyEvent.getCharacter())){
            keyEvent.consume();
            alert.setHeaderText("Wrong Input!");
            alert.setContentText("Please enter an integer");
            alert.showAndWait();
            txtPrice.setText(" ");
        }
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
        brandsList.add(BrandsEnum.OTHER_BRAND);

    }
    private void populateCategoryMenu(ArrayList<IngredientCategory> categoriesData) {
        menuButtonCategory.getItems().clear();
        for (IngredientCategory x : categoriesData) {
            MenuItem category = new MenuItem(x.getName());
            category.setOnAction(this::selectCategory);
            menuButtonCategory.getItems().add(category);
        }
    }

}
