package com.roberterrera.glutenfreeplaces.model;

import java.util.ArrayList;

/**
 * Created by Rob on 6/17/16.
 */
public class FoursquareResponse {

    ArrayList<Venues> venues;

    public ArrayList<Venues> getVenuesList() {
        return venues;
    }

    @Override
    public String toString() {
        return venues.toString();
    }

}
