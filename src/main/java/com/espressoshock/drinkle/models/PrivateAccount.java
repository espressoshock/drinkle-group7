package com.espressoshock.drinkle.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

//JPA DB ANNOTATIONS //***********
@Entity
@Table(name = "private_account")
//JPA DB ANNOTATIONS //***********
public class PrivateAccount extends Account{


    @Column(name="dob")
    private Date dob;


    @Column(name="username")
    private String username;

    @Column(name="gender")
    private String gender;


    public PrivateAccount(String email, String password, String pictureURL, Person registered, List<Blueprint> blueprints, Date dob, String username, String gender) {
        super(email, password, pictureURL, registered, blueprints);
        this.dob = dob;
        this.username = username;
        this.gender = gender;
    }

    public PrivateAccount(String email, String password, String pictureURL, Person registered, List<Blueprint> blueprints) {
        super(email, password, pictureURL, registered, blueprints);
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
