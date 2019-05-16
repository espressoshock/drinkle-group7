package com.espressoshock.drinkle.models;

import java.util.ArrayList;

public abstract class Account  {

    private String email;
    private String password;
    private ArrayList<Beverage> beverages;

    public Account(String email, String password,
        ArrayList<Beverage> beverages) {
        this.email = email;
        this.password = password;
        this.beverages = beverages;
    }
}
