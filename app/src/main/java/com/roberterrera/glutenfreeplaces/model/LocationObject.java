package com.roberterrera.glutenfreeplaces.model;

/**
 * Created by Rob on 6/17/16.
 */
public class LocationObject {

    private String address;
    private String crossStreet;
    private String cc;
    private String city;
    private String state;
    private String country;
    private String[] formattedAddress;
    private double lat;
    private double lng;
    private int distance;
    private String postalCode;

    public String getAddress() {
        return address;
    }

    public String getCrossStreet() {
        return crossStreet;
    }

    public String getCc() {
        return cc;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String[] getFormattedAddress() {
        return formattedAddress;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public int getDistance() {
        return distance;
    }

    public String getPostalCode() {
        return postalCode;
    }
}
