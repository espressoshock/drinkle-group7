package com.espressoshock.drinkle.appState;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


//NOTE: Will contain the user.
public class Environment {

  //TODO: Check for data on disk, query db for existing user.
  public UserState userStatus = UserState.loggedOut;


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
