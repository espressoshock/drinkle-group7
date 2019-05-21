package com.espressoshock.drinkle.models;

import java.sql.Timestamp;
import java.util.List;

public abstract class Blueprint {
    private String metadata;
    private Timestamp ts;
    private int blueprintId;
    private List<Permission>permissions;
    private AccessLevel accessLevel;
    private Statistic statistic;

    public Blueprint(String metadata, Timestamp ts, int blueprintId, List<Permission> permissions, AccessLevel accessLevel, Statistic statistic) {
        this.metadata = metadata;
        this.ts = ts;
        this.blueprintId = blueprintId;
        this.permissions = permissions;
        this.accessLevel = accessLevel;
        this.statistic = statistic;
    }

    ////////////////***************** PERMISSION MNG-M
    public void addPermissions(Permission ...permissions){
        for (Permission permission : permissions)
            this.addPermission(permission);
    }

    public boolean addPermission(Permission permission){
        //might wanna check if permission exists? -> wait for back-end implementation
        return this.permissions.add(permission);
    }

    public boolean removePermission(Permission permission){
        return this.permissions.remove(permission);
    }

    public Permission removePermission(int pos) {
        return this.permissions.remove(pos);
    }

    public void clearPermission(){
        this.permissions.clear();
    }

    ////////////////***************** PERMISSION MNG-M END

    public String getMetadata() {
        return metadata;
    }

    public Timestamp getTs() {
        return ts;
    }

    public int getBlueprintId() {
        return blueprintId;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public Statistic getStatistic() {
        return statistic;
    }

    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public void setStatistic(Statistic statistic) {
        this.statistic = statistic;
    }
}
