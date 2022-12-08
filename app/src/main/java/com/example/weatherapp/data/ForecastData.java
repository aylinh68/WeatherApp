package com.example.weatherapp.data;

/**
 * Aylin Hosseinchi
 *
 * This is a data class used to store the data retrieved from API call
 */

public class ForecastData {

    City city;
    int cod;
    double message;
    int cnt;
    List[] list;

    public City getCity() {
        return city;
    }

    public int getCod() {
        return cod;
    }

    public double getMessage() {
        return message;
    }

    public int getCnt() {
        return cnt;
    }

    public List[] getList() {
        return list;
    }
}
