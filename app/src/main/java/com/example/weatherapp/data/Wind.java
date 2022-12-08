package com.example.weatherapp.data;

import com.google.gson.annotations.SerializedName;

/**
 * Aylin Hosseinchi
 *
 * This is a data class used to store the data retrieved from API call
 */

public class Wind {

    private double speed;

    @SerializedName("deg")
    private double degree;

    public double getSpeed() {
        return speed;
    }

    public double getDegree() {
        return degree;
    }
}