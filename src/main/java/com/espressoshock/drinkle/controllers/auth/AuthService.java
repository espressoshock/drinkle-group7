package com.espressoshock.drinkle.controllers.auth;

import com.espressoshock.drinkle.appState.AppStatePersistence;
import com.espressoshock.drinkle.appState.Current;
import com.espressoshock.drinkle.models.BusinessAccount;
import com.espressoshock.drinkle.models.PrivateAccount;

public class AuthService {


  //TODO: Post to db, receive answer.
  /*
  NOTE: Validation will be done based on the response from the db.
   */
  static void registerAsPrivateAccount(PrivateAccount account) {
    System.out.println("Create private acc");
    AppStatePersistence.saveObject(account, AppStatePersistence.userFilename);
  }

  static void registerAsCompanyAccount(BusinessAccount account) {
    System.out.println("Create company acc");
    AppStatePersistence.saveObject(account, AppStatePersistence.userFilename);
  }

}
