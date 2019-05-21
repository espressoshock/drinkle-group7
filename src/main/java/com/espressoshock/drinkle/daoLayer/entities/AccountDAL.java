package com.espressoshock.drinkle.daoLayer.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//JPA DB ANNOTATIONS //***********
@Entity
@Table(name = "account")
//JPA DB ANNOTATIONS //***********
public class AccountDAL {
    @Id
    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="picture_url")
    private String pictureURL;

    @Column(name="person_ssn")
    private String personSSN;

    public AccountDAL(){

    }

    public AccountDAL(String email, String password, String pictureURL, String personSSN) {
        this.email = email;
        this.password = password;
        this.pictureURL = pictureURL;
        this.personSSN = personSSN;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public String getPersonSSN() {
        return personSSN;
    }

    public void setPersonSSN(String personSSN) {
        this.personSSN = personSSN;
    }
}
