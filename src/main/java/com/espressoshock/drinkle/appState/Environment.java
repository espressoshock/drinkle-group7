package com.espressoshock.drinkle.appState;

import com.espressoshock.drinkle.models.Account;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


//NOTE: Will contain the user.
public class Environment {

  public UserState userStatus() {
    if (currentUser == null) {
      System.out.println("No user saved, show login");
      return UserState.loggedOut;
    }
    System.out.println("User available, show default");
    return UserState.loggedIn;
  }
  public Account currentUser = (Account) AppStatePersistence.retrieveObject(AppStatePersistence.userFilename);



  private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
  private Date date = new Date();

  public String currentDate = dateFormat.format(date);


  //private init.
  private static Environment instance = null;

  private Environment() {}

  static Environment getInstance() {
    if (instance == null)
      instance = new Environment();
    return instance;
  }
}
