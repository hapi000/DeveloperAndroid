package com.example.droidcafeinputchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.droidcafeinputchallenge.databinding.ActivityOrderBinding;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
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
        binding.labelSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.labels_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.labelSpinner.setAdapter(adapter);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String spinnerLabel = parent.getItemAtPosition(position).toString();
        displayToast(spinnerLabel);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}