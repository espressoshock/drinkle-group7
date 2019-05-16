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


}
