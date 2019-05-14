package com.espressoshock.drinkle.controllers.auth;

import com.espressoshock.drinkle.daoLayer.JPADaoManager;
import com.espressoshock.drinkle.models.Gender;
import com.espressoshock.drinkle.viewLoader.EventDispatcherAdapter;
import com.espressoshock.drinkle.viewLoader.ViewLoader;
import com.espressoshock.drinkle.viewLoader.ViewMetadata;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;

public class AuthRegistration extends EventDispatcherAdapter {


  @FXML
  ComboBox genderComboBox;

  @FXML
  public void initialize() {
    setupComponents();
    setupUI();
  }

  private void setupUI() {}

  private void setupComponents() {
    genderComboBox.setItems(
        FXCollections.observableArrayList(Gender.values())
    );
  }

  @FXML
  public void onSwitchAccountRegistrationTap(MouseEvent event) {
    super.dispatchViewChangeRequest(ViewMetadata.AUTH_REGISTRATION_COMPANY);
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
