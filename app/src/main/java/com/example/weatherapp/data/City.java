package com.example.weatherapp.data;

/**
 * Aylin Hosseinchi
 *
 * This is a data class used to store the data retrieved from API call
 */

public class City {

    int id;
    String name;
    Coord coord;
    String country;
    long population;
    int timezone;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coord getCoord() {
        return coord;
    }

    public String getCountry() {
        return country;
    }

    public long getPopulation() {
        return population;
    }

    public int getTimezone() {
        return timezone;
    }
}
