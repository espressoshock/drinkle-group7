package com.espressoshock.drinkle.models;

public class PrivateAccount extends Account {

    private String email;
    private String username;

    public PrivateAccount(String accountID, String accountName, String accountPassword, Person registered, Blueprint blueprint,
                          String email, String username) {
        super(accountID, accountName, accountPassword, registered, blueprint);
        this.email = email;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "PrivateAccount{" +
                "email='" + email + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
