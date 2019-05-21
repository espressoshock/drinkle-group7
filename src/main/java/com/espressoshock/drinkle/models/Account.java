package com.espressoshock.drinkle.models;


import javax.persistence.*;
import java.util.List;

//JPA DB ANNOTATIONS //***********
@Entity
@Table(name = "account")
//JPA DB ANNOTATIONS //***********
public class Account implements IActionAccount{
    @Id
    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="picture_url")
    private String pictureURL;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_ssn")
    private Person registered;

    @Transient
    private List<Blueprint> blueprints;

    public Account(String email, String password, String pictureURL, Person registered, List<Blueprint> blueprints) {
        this.email = email;
        this.password = password;
        this.pictureURL = pictureURL;
        this.registered = registered;
        this.blueprints = blueprints;
    }

    public void setBlueprints(List<Blueprint> blueprints) {
        this.blueprints = blueprints;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Person getRegistered() {
        return registered;
    }

    public List<Blueprint> getBlueprints() {
        return blueprints;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    @Override
    public boolean addBlueprint(Blueprint blueprint) {
        return this.blueprints.add(blueprint);
    }

    @Override
    public boolean deleteBlueprint(Blueprint blueprint) {
        return this.blueprints.remove(blueprint);
    }

    @Override
    public boolean deleteBlueprintUsingIndex(int pos) {
        try{
            this.blueprints.remove(pos);
        } catch(IndexOutOfBoundsException|UnsupportedOperationException e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean editBlueprint(Blueprint source, Blueprint destination) {
        try{
            this.blueprints.set(this.blueprints.indexOf(source), destination);
        } catch(IndexOutOfBoundsException|UnsupportedOperationException|ClassCastException|NullPointerException|IllegalArgumentException e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean editBlueprintUsingIndex(int pos, Blueprint destination) {
        return false;
    }
}
