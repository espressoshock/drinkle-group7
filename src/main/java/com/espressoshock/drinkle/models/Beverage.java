package com.espressoshock.drinkle.models;

import java.util.ArrayList;

public class Beverage {

  private String name;
  private int alcoholPercentage;
  private int cost;
  private int volumePerMilliliter;
  private ArrayList<Ingredient> ingredients;

  public Beverage(String name, int alcoholPercentage, int cost, int volumePerMilliliter,
      ArrayList<Ingredient> ingredients) {
    this.name = name;
    this.alcoholPercentage = alcoholPercentage;
    this.cost = cost;
    this.volumePerMilliliter = volumePerMilliliter;
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

  public int getVolumePerMilliliter() {
    return volumePerMilliliter;
  }

  public void setVolumePerMilliliter(int volumePerMilliliter) {
    this.volumePerMilliliter = volumePerMilliliter;
  }

  public ArrayList<Ingredient> getIngredients() {
    return ingredients;
  }

  public void setIngredients(ArrayList<Ingredient> ingredients) {
    this.ingredients = ingredients;
  }
}
