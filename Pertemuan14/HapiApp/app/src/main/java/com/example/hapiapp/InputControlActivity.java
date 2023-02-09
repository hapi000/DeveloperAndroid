package com.example.hapiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class InputControlActivity extends AppCompatActivity {
    TextView tvValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_control);

        SeekBar seekBar = findViewById(R.id.seekBar);
        tvValues = findViewById(R.id.tv_values);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                String values = String.format("%d%%", i);
                tvValues.setText(values);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                tvValues.setText("Started!");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tvValues.setText("Stopped!");
            }
        });
    }
}