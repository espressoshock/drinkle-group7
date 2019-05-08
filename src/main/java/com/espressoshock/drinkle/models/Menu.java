package com.espressoshock.drinkle.models;

import java.sql.Timestamp;
import java.util.List;

public class Menu extends Blueprint {
    private List<Beverage> beverages;
    private String name;
    private String theme;
    private String pictureURL;

    public Menu(String metadata, Timestamp ts, int blueprintId, List<Permission> permissions, AccessLevel accessLevel, Statistic statistic, List<Beverage> beverages, String name, String theme, String pictureURL) {

        super(metadata, ts, blueprintId, permissions, accessLevel, statistic);
        this.beverages = beverages;
        this.name = name;
        this.theme = theme;
        this.pictureURL = pictureURL;
    }

    ////////////////***************** BEVERAGES MNG-M
    public void addBeverages(Beverage... beverages) {
        for (Beverage beverage : beverages)
            this.addBeverage(beverage);
    }

    public boolean addBeverage(Beverage beverage) {
        //might wanna check if beverage exists? -> wait for back-end implementation
        return this.beverages.add(beverage);
    }

    public boolean removeBeverage(Beverage beverage) {
        return this.beverages.remove(beverage);
    }

    public Beverage removeBeverage(int pos) {
        return this.beverages.remove(pos);
    }

    public void clearPermission() {
        this.beverages.clear();
    }

    ////////////////***************** BEVERAGES MNG-M END


    public List<Beverage> getBeverages() {
        return beverages;
    }

    //***NOT RECOMMENDED
    public void setBeverages(List<Beverage> beverages) {
        this.beverages = beverages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }
}
