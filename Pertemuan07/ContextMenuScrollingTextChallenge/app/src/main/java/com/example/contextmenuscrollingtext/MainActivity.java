package com.example.contextmenuscrollingtext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.contextmenuscrollingtext.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mainBinding;
    private final static String LOG_TAG = MainActivity.class.getSimpleName();
    public final static String EXTRA_ACTIVITY_MAIN = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setButtonOnClickListener();

        setContentView(mainBinding.getRoot());
        setTitle("Hello from MainActivity");
    }

    private void setButtonOnClickListener() {
        mainBinding.textOne.setOnClickListener(view -> {
            Log.d(LOG_TAG,"Text One button clicked.");

            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra(EXTRA_ACTIVITY_MAIN, 0);
            startActivity(intent);
        });
        mainBinding.textTwo.setOnClickListener(view -> {
            Log.d(LOG_TAG,"Text Two button clicked.");

            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra(EXTRA_ACTIVITY_MAIN, 1);
            startActivity(intent);
        });
        mainBinding.textThree.setOnClickListener(view -> {
            Log.d(LOG_TAG,"Text Three button clicked.");

            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra(EXTRA_ACTIVITY_MAIN, 2);
            startActivity(intent);
        });
    }
}