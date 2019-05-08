package com.espressoshock.drinkle.models;

public class Account implements IActionAccount {
    private String accountID;
    private String accountName;
    private String accountPassword;
    private Person registered;
    private Blueprint blueprint;

    public Account(String accountID, String accountName, String accountPassword, Person registered,
                   Blueprint blueprint) {
        this.accountID = accountID;
        this.accountName = accountName;
        this.accountPassword = accountPassword;
        this.registered = registered;
        this.blueprint = blueprint;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public Person getRegistered() {
        return registered;
    }

    public void setRegistered(Person registered) {
        this.registered = registered;
    }

    public Blueprint getBlueprint() {
        return blueprint;
    }

    public void setBlueprint(Blueprint blueprint) {
        this.blueprint = blueprint;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountID='" + accountID + '\'' +
                ", accountName='" + accountName + '\'' +
                ", accountPassword='" + accountPassword + '\'' +
                ", registered=" + registered +
                ", blueprint=" + blueprint +
                '}';
    }

    @Override
    public boolean addBlueprint(Blueprint blueprint) {
        return false;
    }

    @Override
    public boolean deleteBlueprint(Blueprint blueprint) {
        return false;
    }

    @Override
    public boolean deleteBlueprintUsingIndex(int pos) {
        return false;
    }

    @Override
    public boolean editBlueprint(Blueprint source, Blueprint destination) {
        return false;
    }

    @Override
    public boolean editBlueprintUsingIndex(int pos, Blueprint destination) {
        return false;
    }
}
