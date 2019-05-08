package com.espressoshock.drinkle.models;

public class BusinessAccount extends Account implements IBusinessAccountAction {

    private String vatID;
    private String businessName;
    private Country registrationCountry;
    private Team team;

    public BusinessAccount(String accountID, String accountName, String accountPassword,
                           Person registered, Blueprint blueprint, String vatID,
                           String businessName, Country registrationCountry, Team team) {
        super(accountID, accountName, accountPassword, registered, blueprint);
        this.vatID = vatID;
        this.businessName = businessName;
        this.registrationCountry = registrationCountry;
        this.team = team;
    }

    public String getVatID() {
        return vatID;
    }

    public void setVatID(String vatID) {
        this.vatID = vatID;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Country getRegistrationCountry() {
        return registrationCountry;
    }

    public void setRegistrationCountry(Country registrationCountry) {
        this.registrationCountry = registrationCountry;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }


    @Override
    public boolean addTeam(Team team) {
        return false;
    }

    @Override
    public boolean addMember(Team team, Account member) {
        return false;
    }

    @Override
    public boolean addMemberUsingIndex(int pos, Account member) {
        return false;
    }

    @Override
    public boolean removeMember(Team team, Account member) {
        return false;
    }

    @Override
    public boolean removeMemberUsingIndex(int pos, Team team) {
        return false;
    }
}
