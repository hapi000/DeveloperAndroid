package com.example.droidcafeoptionsup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.droidcafeoptionsup.databinding.ActivityOrderBinding;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityOrderBinding binding;

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
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