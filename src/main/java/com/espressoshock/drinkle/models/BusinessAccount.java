package com.espressoshock.drinkle.models;


public class BusinessAccount extends Account  {

    private String vatID;
    private String businessName;

    public BusinessAccount(String email, String password, String vatID, String businessName) {
        super(email, password);
        this.vatID = vatID;
        this.businessName = businessName;
    }
}
