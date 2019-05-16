package com.espressoshock.drinkle.models;

import java.util.ArrayList;

public class PrivateAccount extends Account {

  private String name;

  public PrivateAccount(String email, String password,
      ArrayList<Beverage> beverages, String name) {
    super(email, password, beverages);
    this.name = name;
  }
}
