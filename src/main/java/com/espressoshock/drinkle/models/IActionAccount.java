package com.espressoshock.drinkle.models;

public interface IActionAccount {

    boolean addBlueprint(Blueprint blueprint);
    boolean deleteBlueprint(Blueprint blueprint);
    boolean deleteBlueprintUsingIndex(int pos);
    boolean editBlueprint(Blueprint source, Blueprint destination);
    boolean editBlueprintUsingIndex(int pos, Blueprint destination);

}
