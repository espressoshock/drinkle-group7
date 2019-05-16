package com.espressoshock.drinkle.models;

import java.util.ArrayList;

public class Beverage {

  private String name;
  private int alcoholPercentage;
  private int cost;
  private int volumePerMililiter;
  private ArrayList<Ingredient> ingredients;

  public Beverage(String name, int alcoholPercentage, int cost, int volumePerMililiter,
      ArrayList<Ingredient> ingredients) {
    this.name = name;
    this.alcoholPercentage = alcoholPercentage;
    this.cost = cost;
    this.volumePerMililiter = volumePerMililiter;
    this.ingredients = ingredients;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAlcoholPercentage() {
    return alcoholPercentage;
  }

  public void setAlcoholPercentage(int alcoholPercentage) {
    this.alcoholPercentage = alcoholPercentage;
  }

  public int getCost() {
    return cost;
  }

  public void setCost(int cost) {
    this.cost = cost;
  }

  public int getVolumePerMililiter() {
    return volumePerMililiter;
  }

  public void setVolumePerMililiter(int volumePerMililiter) {
    this.volumePerMililiter = volumePerMililiter;
  }

  public ArrayList<Ingredient> getIngredients() {
    return ingredients;
  }

  public void setIngredients(ArrayList<Ingredient> ingredients) {
    this.ingredients = ingredients;
  }
}
