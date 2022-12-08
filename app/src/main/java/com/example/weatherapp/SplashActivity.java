package com.example.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Aylin Hosseinchi
 *
 * This is the class for displaying a simple splash screen
 */

public class SplashActivity extends AppCompatActivity {

    int splashScreenTimeout = 1500;
    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        imageView = findViewById(R.id.app_icon);
        textView = findViewById(R.id.app_title);

        textView.animate().alpha(1).setDuration(1200).start();
        imageView.animate().alpha(1).setDuration(1200).start();

        new Handler().postDelayed(() -> {

            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);

            //WILL STOP GOING BACK TO WELCOME SCREEN, WHEN BACK BUTTON IS PRESSED
            finish();
        }, splashScreenTimeout);
    }
}
