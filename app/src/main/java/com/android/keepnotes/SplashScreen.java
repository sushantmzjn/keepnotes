package com.android.keepnotes;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Objects.requireNonNull(getSupportActionBar()).hide();


        final Intent intent = new Intent(SplashScreen.this, MainActivity.class);
        new Handler().postDelayed(() -> {
            startActivity(intent);
            finish();
        }, 2000);
    }
}