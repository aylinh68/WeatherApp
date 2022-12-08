package com.example.weatherapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.weatherapp.data.WeatherData;
import com.google.android.material.button.MaterialButton;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Aylin Hosseinchi
 *
 * This is the class for checking and updating the current weather of any city in the world
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //default city name
    String CITY_NAME = "London";

    //APP ID obtained from OpenWeatherMapAPI
    String APP_ID = "ebfcac32bda131ed5a160f2757938396";

    private static DecimalFormat df = new DecimalFormat("0");
    boolean isCelcius = true;
    int hours;

    private ConstraintLayout mRootLayout;
    private TextView mCityName, mTempValue, mTempDescription, mWindSpeed, mWindDirection, mPressure, mHumidity, mDate;
    private ImageView mTempImage;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialButton mForecastButton;
        mForecastButton = findViewById(R.id.forecast_button);
        mForecastButton.setOnClickListener(this);

        callingAPI(CITY_NAME);
    }

    public void callingAPI(String cityName) {
        mCityName = findViewById(R.id.city_name);
        mTempValue = findViewById(R.id.temperature_value);
        mTempDescription = findViewById(R.id.temperature_description);
        mWindSpeed = findViewById(R.id.wind_speed_value);
        mHumidity = findViewById(R.id.humidity_value);
        mTempImage = findViewById(R.id.temparature_image);
        mDate = findViewById(R.id.date);
        mRootLayout = findViewById(R.id.main_root_layout);
        mWindDirection = findViewById(R.id.wind_direction_value);
        mPressure = findViewById(R.id.pressure_value);

        progressBar = findViewById(R.id.progress_bar);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.url))
                .addConverterFactory(GsonConverterFactory.create())     //GSON CONVERTER
                .build();

        OpenWeatherMapApi openWeatherMapApi = retrofit.create(OpenWeatherMapApi.class);

        Call<WeatherData> call = openWeatherMapApi.getWeather(cityName, APP_ID);

        call.enqueue(new Callback<WeatherData>() {
            private final String TAG = getString(R.string.tag);

            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {

                if (!response.isSuccessful()) {
                    mTempDescription.setText(getString(R.string.errode_code) + response.code());
                    return;
                }

                WeatherData weatherData = response.body();

                if (weatherData != null) {
                    progressBar.setVisibility(View.INVISIBLE);
                }

                //SET CITYNAME
                assert weatherData != null;
                if (weatherData.getName() != null) {
                    mCityName.setText(weatherData.getName());
                }

                //SET DATE
                Date date = new Date((weatherData.getDt()) * 1000);
                DateFormat dateFormat = new SimpleDateFormat(getString(R.string.simple_date_format1));
                mDate.setText(dateFormat.format(date));

                //SET BACKGROUND BASED ON TIME
                DateFormat hourFormat = new SimpleDateFormat(getString(R.string.hour_format));
                int hour = Integer.parseInt(hourFormat.format(date));

                if (hour >= 7 && hour <= 10) {
                    mRootLayout.setBackgroundResource(R.drawable.morning);
                } else if (hour >= 11 && hour <= 16) {
                    mRootLayout.setBackgroundResource(R.drawable.noon);
                } else if (hour >= 17 && hour <= 18) {
                    mRootLayout.setBackgroundResource(R.drawable.evening);
                } else if (hour >= 19 && hour <= 22) {
                    mRootLayout.setBackgroundResource(R.drawable.night);
                } else if ((hour == 23 || (hour >= 0 && hour <= 6))) {
                    mRootLayout.setBackgroundResource(R.drawable.midnight);
                }
                hours = hour;

                //SET TEMPERATURE
                double tempC = convertToCelcius(weatherData.getMain().getTemp());    //CONVERTING KELVIN TO CELCIUS
                double tempF = convertToFahrenheit(weatherData.getMain().getTemp());    //CONVERTING KELVIN TO FAHRENHEIT
                String tempCValue = (df.format(tempC)) + "°C";  //ROUNDING OFF TO 1 DECIMAL PLACE
                String tempFValue = (df.format(tempF)) + "F";    //ROUNDING OFF TO 1 DECIMAL PLACE
                mTempValue.setText(tempCValue);

                //TO TOGGLE DISPLAY IN FAHRENHEIT
                mTempValue.setOnClickListener(v -> {
                    if (isCelcius) {
                        mTempValue.setText(tempCValue);
                        isCelcius = false;
                    } else {
                        mTempValue.setText(tempFValue);
                        isCelcius = true;
                    }
                });

                //GET WEATHER DESCRIPTION
                String description = weatherData.getWeather()[0].getDescription();
                //GET WEATHER ID
                int weatherId = weatherData.getWeather()[0].getId();
                //SET WEATHER ICON
                if (weatherId >= 200 && weatherId <= 299) {
                    mTempDescription.setText(description);
                    mTempImage.setImageResource(R.drawable.thunderstorm);
                } else if (weatherId >= 300 && weatherId <= 399) {
                    mTempDescription.setText(description);
                    mTempImage.setImageResource(R.drawable.shower_rain);
                } else if (weatherId >= 500 && weatherId <= 599) {
                    mTempDescription.setText(description);
                    mTempImage.setImageResource(R.drawable.shower_rain);
                } else if (weatherId >= 600 && weatherId <= 699) {
                    mTempDescription.setText(description);
                    mTempImage.setImageResource(R.drawable.snow);
                } else if (weatherId >= 700 && weatherId <= 799) {
                    mTempDescription.setText(description);
                    mTempImage.setImageResource(R.drawable.mist);
                } else if (weatherId == 800) {
                    mTempDescription.setText(description);
                    mTempImage.setImageResource(R.drawable.d_clear_sky);
                } else if (weatherId == 801) {
                    mTempDescription.setText(description);
                    mTempImage.setImageResource(R.drawable.d_few_clouds);
                } else if (weatherId == 802) {
                    mTempDescription.setText(description);
                    mTempImage.setImageResource(R.drawable.scattered_clouds);
                } else if (weatherId == 803 || weatherId == 804) {
                    mTempDescription.setText(description);
                    mTempImage.setImageResource(R.drawable.broken_clouds);
                }

                //SET WIND VALUE
                String windValue = weatherData.getWind().getSpeed() + getString(R.string.wind_speed);
                mWindSpeed.setText(windValue);

                //SET WIND DIRECTION
                double degree = weatherData.getWind().getDegree();
                if ((degree >= 337.6 && degree <= 360) || (degree >= 0 && degree <= 22.5)) {
                    mWindDirection.setText(R.string.n);     //NORTH
                } else if (degree > 22.6 && degree <= 67.5) {
                    mWindDirection.setText(R.string.ne);    //NORTH EAST
                } else if (degree >= 67.6 && degree <= 112.5) {
                    mWindDirection.setText(R.string.e);     //EAST
                } else if (degree >= 112.6 && degree <= 157.5) {
                    mWindDirection.setText(R.string.se);    //SOUTH EAST
                } else if (degree >= 157.6 && degree <= 202.5) {
                    mWindDirection.setText(R.string.s);     //SOUTH
                } else if (degree >= 202.6 && degree <= 247.5) {
                    mWindDirection.setText(R.string.sw);    //SOUTH WEST
                } else if (degree >= 247.6 && degree <= 292.5) {
                    mWindDirection.setText(R.string.w);     //WEST
                } else if (degree > 292.6 && degree <= 337.5) {
                    mWindDirection.setText(R.string.nw);    //NORTH WEST
                }

                //SET HUMIDITY VALUE
                String humidityValue = weatherData.getMain().getHumidity() + getString(R.string.humidity_unit);
                mHumidity.setText(humidityValue);

                //SET PRESSURE VALUE
                String pressureValue = weatherData.getMain().getPressure() + getString(R.string.pressure_unit);
                mPressure.setText(pressureValue);
            }


            private double convertToCelcius(double temp) {
                return temp - 273.15;
            }

            private double convertToFahrenheit(double temp) {
                return (((temp - 273) * 9 / 5) + 32);
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                mTempDescription.setText(t.getMessage());
            }
        });
    }

    @Override
    public void onClick(View v) {

        if ((v.getId() == R.id.forecast_button)) {
            Intent forecastIntent = new Intent(MainActivity.this, ForecastActivity.class);
            forecastIntent.putExtra("hour", hours);
            startActivity(forecastIntent);
        }
    }

    public void search(View view) {
        EditText editText = findViewById(R.id.edt_cityname);
        String cityName = editText.getText().toString();

        callingAPI(cityName);
        mCityName.setText(cityName);

        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}