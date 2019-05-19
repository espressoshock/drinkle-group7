package com.espressoshock.drinkle.models;

import java.util.ArrayList;

public class PrivateAccount extends Account {

  private String name;

  public PrivateAccount(Integer id, String email, String password,
      ArrayList<Beverage> beverages, String name) {
    super(id, email, password, beverages);
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
