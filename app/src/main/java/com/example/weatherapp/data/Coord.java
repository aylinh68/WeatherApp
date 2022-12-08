package com.example.weatherapp.data;

import com.google.gson.annotations.SerializedName;

/**
 * Aylin Hosseinchi
 *
 * This is a data class used to store the data retrieved from API call
 */

public class Coord {

    @SerializedName("lon")
    private String longitude;

    @SerializedName("lat")
    private String latitude;

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }
}
