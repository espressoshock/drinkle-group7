package com.espressoshock.drinkle.models;

import java.util.ArrayList;

public abstract class Account  {

    private String email;
    private String password;
    private ArrayList<Beverage> beverages;

    Account(String email, String password,
        ArrayList<Beverage> beverages) {
        this.email = email;
        this.password = password;
        this.beverages = beverages;
    }


    public String getEmail(){return email;}

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Beverage> getBeverages() {
        return beverages;
    }

    public void setBeverages(ArrayList<Beverage> beverages) {
        this.beverages = beverages;
    }
}
