package com.example.weatherapp.data;

/**
 * Aylin Hosseinchi
 *
 * This is a data class used to store the data retrieved from API call
 */

public class Weather {

    private int id;
    private String main;
    private String description;
    private String icon;

    public int getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }
}