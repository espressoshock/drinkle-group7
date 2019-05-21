package com.espressoshock.drinkle.models;

public enum PermissionType {
    VIEW("View"), EDIT("Edit");

    private final String permissionTextual;

    PermissionType(String permissionTextual){
        this.permissionTextual = permissionTextual;
    }

    public String getPermissionTextual() {
        return permissionTextual;
    }
}
