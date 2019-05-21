package com.espressoshock.drinkle.models;

public class Permission {
    private PermissionType permissionType;
    private Account account;

    public Permission(PermissionType permissionType, Account account) {
        this.permissionType = permissionType;
        this.account = account;
    }

    public PermissionType getPermissionType() {
        return permissionType;
    }

    public Account getAccount() {
        return account;
    }

    public void setPermissionType(PermissionType permissionType) {
        this.permissionType = permissionType;
    }
    
}
