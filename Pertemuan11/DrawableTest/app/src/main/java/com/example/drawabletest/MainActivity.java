package com.example.drawabletest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.drawabletest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private int batteryLevel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        mainBinding.buttonIncrement.setOnClickListener(v -> {
            if (batteryLevel < 7) batteryLevel++;
            mainBinding.imageView.setImageLevel(batteryLevel);
        });

        mainBinding.buttonDecrease.setOnClickListener(v -> {
            if (batteryLevel > 0) batteryLevel--;
            mainBinding.imageView.setImageLevel(batteryLevel);
        });
    }
}