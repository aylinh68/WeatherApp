package com.example.weatherapp.data;

import com.google.gson.annotations.SerializedName;

/**
 * Aylin Hosseinchi
 *
 * This is a data class used to store the data retrieved from API call
 */

public class List {

    long dt;
    long sunrise;
    long sunset;
    Temp temp;
    FeelsLike feelsLike;
    int pressure;
    int humidity;
    Weather[] weather;
    double speed;

    @SerializedName("deg")
    int degree;
    int clouds;
    double rain;

    public long getDt() {
        return dt;
    }

    public long getSunrise() {
        return sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public Temp getTemp() {
        return temp;
    }

    public FeelsLike getFeelsLike() {
        return feelsLike;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public Weather[] getWeather() {
        return weather;
    }

    public double getSpeed() {
        return speed;
    }

    public int getDegree() {
        return degree;
    }

    public int getClouds() {
        return clouds;
    }

    public double getRain() {
        return rain;
    }
}
