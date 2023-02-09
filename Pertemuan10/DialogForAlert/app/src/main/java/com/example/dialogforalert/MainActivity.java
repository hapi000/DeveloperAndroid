package com.example.dialogforalert;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.dialogforalert.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        binding.button.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Alert");
            builder.setMessage("Click OK to continue, or Cancel to stop:");

            builder.setPositiveButton("Ok", (dialog, which) -> {
                Toast.makeText(this, "Pressed OK", Toast.LENGTH_SHORT).show();
            });
            builder.setNegativeButton("Cancle", (dialog, which) -> {
                Toast.makeText(this, "Pressed Cancle", Toast.LENGTH_SHORT).show();
            });

            builder.show();
        });
    }
}