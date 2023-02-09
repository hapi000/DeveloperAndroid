package com.example.homework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.homework.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.button.setOnClickListener(v -> {
            String message = "Toppings: ";

            if (binding.checkBox.isChecked())
                message += binding.checkBox.getText().toString() + " ";
            if (binding.checkBox2.isChecked())
                message += binding.checkBox2.getText().toString() + " ";
            if (binding.checkBox3.isChecked())
                message += binding.checkBox3.getText().toString() + " ";
            if (binding.checkBox4.isChecked())
                message += binding.checkBox4.getText().toString() + " ";
            if (binding.checkBox5.isChecked())
                message += binding.checkBox5.getText().toString() + " ";

            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        });
    }
}