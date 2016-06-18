package com.roberterrera.glutenfreeplaces.model;

/**
 * Created by Rob on 6/17/16.
 */
public class CategoryObject {
    private int id;
    private String name;
    private String pluralName;
    private String shortName;
    private boolean primary;
    private IconObject icon;

    public IconObject getIcon() {
        return icon;
    }

    public void setIcon(IconObject icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPluralName() {
        return pluralName;
    }

    public void setPluralName(String pluralName) {
        this.pluralName = pluralName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

}
