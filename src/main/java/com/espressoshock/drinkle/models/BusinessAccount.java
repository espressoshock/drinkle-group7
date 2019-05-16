package com.espressoshock.drinkle.models;


import java.util.ArrayList;

public class BusinessAccount extends Account  {

    private String businessName;

    public BusinessAccount(String email, String password,
        ArrayList<Beverage> beverages, String businessName) {
        super(email, password, beverages);
        this.businessName = businessName;
    }
}
