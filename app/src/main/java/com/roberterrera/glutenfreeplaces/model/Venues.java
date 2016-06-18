package com.roberterrera.glutenfreeplaces.model;

/**
 * Created by Rob on 6/17/16.
 */
public class Venues {

    private int id;
    private String name;
    private String url;
    private LocationObject location;
    private ContactObject contact;
    private CategoryObject categories;
    private MenuObject menu;
    private VenuePageObject venuepage;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public LocationObject getLocation() {
        return location;
    }

    public ContactObject getContact() {
        return contact;
    }

    public CategoryObject getCategories() {
        return categories;
    }

    public MenuObject getMenu() {
        return menu;
    }

    public VenuePageObject getVenuepage() {
        return venuepage;
    }

    @Override
    public String toString() {
        return name;
    }

}
