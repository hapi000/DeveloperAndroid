package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "Hello World");
        setContentView(R.layout.activity_main);

    }
}