package com.espressoshock.drinkle.controllers.auth;

import com.espressoshock.drinkle.models.Country;
import com.espressoshock.drinkle.viewLoader.EventDispatcherAdapter;
import com.espressoshock.drinkle.viewLoader.ViewLoader;
import com.espressoshock.drinkle.viewLoader.ViewMetadata;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;

public class AuthRegistrationCompany extends EventDispatcherAdapter {

  @FXML
  ComboBox countryComboBox;

  @FXML
  public void initialize() {
    setupComponents();
    setupUI();
  }

  private void setupUI() { }

  private void setupComponents() {
    countryComboBox.setItems(
        FXCollections.observableArrayList(Country.values())
    );
  }


  @FXML
  public void onSwitchAccountRegistrationTap(MouseEvent event) {
    super.dispatchViewChangeRequest(ViewMetadata.AUTH_REGISTRATION);
  }
  @FXML
  public void openLoginView(MouseEvent event) {
    super.dispatchViewChangeRequest(ViewMetadata.AUTH_LOGIN);
  }
  @FXML
  public void register(MouseEvent event){
    super.dispatchViewChangeRequest(ViewLoader.default_view);
  }
}
