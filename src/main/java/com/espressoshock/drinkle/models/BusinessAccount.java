package com.espressoshock.drinkle.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

//JPA DB ANNOTATIONS //***********
@Entity
@Table(name = "business_account")
//JPA DB ANNOTATIONS //***********
public class BusinessAccount extends Account implements IBusinessAccountAction {

    @Id
    @Column(name="vat_id")
    private String vatID;

    @Column(name="business_email")
    private String businessName;

    @Column(name="contact_email")
    private String contactEmail;

    @Column(name="country_country_code")
    private char[] countryCode;


    private Country registrationCountry;

    private Team team;

    public BusinessAccount(String email, String password, String pictureURL, Person registered, List<Blueprint> blueprints, String vatID, String businessName, String contactEmail, char[] countryCode, Country registrationCountry, Team team) {
        super(email, password, pictureURL, registered, blueprints);
        this.vatID = vatID;
        this.businessName = businessName;
        this.contactEmail = contactEmail;
        this.countryCode = countryCode;
        this.registrationCountry = registrationCountry;
        this.team = team;
    }

    public BusinessAccount(String email, String password, String pictureURL, Person registered, List<Blueprint> blueprints) {
        super(email, password, pictureURL, registered, blueprints);
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
