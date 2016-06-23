package com.roberterrera.glutenfreeplaces.model;

import java.util.List;

/**
 * Created by Rob on 6/17/16.
 */
public class Venues {

    private String id;
    private String name;
    private String url;
    private LocationObject location;
    private ContactObject contact;
    private CategoryObject categories;
    private MenuObject menu;
    private VenuePageObject venuepage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocationObject getLocation() {
        return location;
    }

    public void setLocation(LocationObject location) {
        this.location = location;
    }

    public ContactObject getContact() {
        return contact;
    }

    public void setContact(ContactObject contact) {
        this.contact = contact;
    }

    public CategoryObject getCategories() {
        return categories;
    }

    public void setCategories(CategoryObject categories) {
        this.categories = categories;
    }

    public MenuObject getMenu() {
        return menu;
    }

    public void setMenu(MenuObject menu) {
        this.menu = menu;
    }

    public VenuePageObject getVenuepage() {
        return venuepage;
    }

    public void setVenuepage(VenuePageObject venuepage) {
        this.venuepage = venuepage;
    }

    @Override
    public String toString() {
        return name;
    }

}
