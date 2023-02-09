package com.example.pickerfortimer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.Toast;

import com.example.pickerfortimer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mainBinding;

    public void processTimePickerResult(int h, int m) {
        String hour = Integer.toString(h);
        String minute = Integer.toString(m);

        String message = "Time " + hour + ":" + minute;
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(mainBinding.getRoot());

        mainBinding.button.setOnClickListener(v -> {
            TimePickerFragment fragment = new TimePickerFragment();
            fragment.show(getSupportFragmentManager(), getString(R.string.time_picker));
        });
    }
}