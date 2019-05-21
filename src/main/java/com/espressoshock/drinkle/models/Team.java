package com.espressoshock.drinkle.models;

import java.util.ArrayList;

public class Team {

    private String name;
    private String description;
    private ArrayList<Account> members;

    public Team(String name, String description, ArrayList<Account> members) {
        this.name = name;
        this.description = description;
        this.members = members;
    }

    //TODO  "-memberName" I didn't understood what this stands for

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Account> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Account> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", members=" + members +
                '}';
    }
}
