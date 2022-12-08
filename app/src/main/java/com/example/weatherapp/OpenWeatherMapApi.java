package com.example.weatherapp;

import com.example.weatherapp.data.ForecastData;
import com.example.weatherapp.data.WeatherData;
import com.example.weatherapp.data.ForecastData;
import com.example.weatherapp.data.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Aylin Hosseinchi
 *
 * This is the interface configured for making the API calls to OpenWeatherMapAPI
 */

public interface OpenWeatherMapApi {

    //CURRENT WEATHER
    @GET("data/2.5/weather?")
    Call<WeatherData> getWeather(@Query("q") String city, @Query("appid") String appId);


    //WEATHER FORECAST
    @GET("data/2.5/forecast/daily?")
    Call<ForecastData> getForecast(@Query("q") String city, @Query("cnt") int days, @Query("appid") String appId);

}




