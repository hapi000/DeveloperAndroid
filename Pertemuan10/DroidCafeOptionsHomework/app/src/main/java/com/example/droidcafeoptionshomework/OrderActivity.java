package com.example.droidcafeoptionshomework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.droidcafeoptionshomework.databinding.ActivityOrderBinding;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityOrderBinding binding;

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    public void processDatePickerResult(int y, int m, int d) {
        String year = Integer.toString(y);
        String month = Integer.toString(m);
        String day = Integer.toString(d);

        String message = "Date: " + day + "/" + month + "/" + year;

        displayToast(message);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String message = "Order: " + intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        binding.orderMessage.setText(message);

        binding.sameday.setOnClickListener(this);
        binding.nextday.setOnClickListener(this);
        binding.pickup.setOnClickListener(this);

        binding.button.setOnClickListener(v -> {
            DatePickerFragment fragment = new DatePickerFragment();
            fragment.show(getSupportFragmentManager(), getString(R.string.date_picker));
        });
    }


    @Override
    public void onClick(View v) {
        if (v == binding.sameday) {
            if (binding.sameday.isChecked())
                displayToast(getString(R.string.same_day));
        } else if (v == binding.nextday) {
            if (binding.nextday.isChecked())
                displayToast(getString(R.string.next_day));
        } else if (v == binding.pickup) {
            if (binding.pickup.isChecked())
                displayToast(getString(R.string.pick_up));
        }
    }
}