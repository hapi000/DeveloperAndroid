package com.example.hellosharedprefschallenge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

public class PreferencesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner bgSpinner;
    private ToggleButton tbSaveCount;

    private int spinnerItem;
    private int spinnerSelectedColor;

    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.example.android.hellosharedprefschallenge";


    private final String COLOR_KEY = "color";
    private final String COUNT_SAVE_KEY = "count_save";

    public void resetPreferences(View view) {
        SharedPreferences.Editor editor = mPreferences.edit();

        editor.putBoolean(COUNT_SAVE_KEY, true);
        tbSaveCount.setChecked(true);

        bgSpinner.setSelection(0);
        editor.putInt(COLOR_KEY, ContextCompat.getColor(this, R.color.default_background));
        editor.apply();
        Toast.makeText(this, "Settings were reset!", Toast.LENGTH_LONG).show();
    }

    public void savePreferences(View view) {
        SharedPreferences.Editor editor = mPreferences.edit();

        editor.putBoolean(COUNT_SAVE_KEY, tbSaveCount.isChecked());
        switch (spinnerItem) {
            case 1:
                spinnerSelectedColor = ContextCompat.getColor(this, R.color.black);
                break;
            case 2:
                spinnerSelectedColor = ContextCompat.getColor(this, R.color.red_background);
                break;
            case 3:
                spinnerSelectedColor = ContextCompat.getColor(this, R.color.blue_background);
                break;
            case 4:
                spinnerSelectedColor = ContextCompat.getColor(this, R.color.green_background);
                break;
            default:
                spinnerSelectedColor = ContextCompat.getColor(this, R.color.default_background);
                break;
        }
        editor.putInt(COLOR_KEY, spinnerSelectedColor);
        editor.apply();
        Toast.makeText(this, "Settings have been saved!", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.color_array, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        bgSpinner = findViewById(R.id.spinner_bg_color);
        bgSpinner.setOnItemSelectedListener(this);
        bgSpinner.setAdapter(adapter);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        spinnerSelectedColor = mPreferences.getInt(COLOR_KEY, 0);

        tbSaveCount = findViewById(R.id.tb_save_count);
        tbSaveCount.setChecked(mPreferences.getBoolean(COUNT_SAVE_KEY, true));

        if (spinnerSelectedColor == this.getResources().getColor(R.color.black)) {
            bgSpinner.setSelection(1);
            spinnerItem = 1;
        } else if (spinnerSelectedColor == this.getResources().getColor(R.color.red_background)) {
            bgSpinner.setSelection(2);
            spinnerItem = 2;
        } else if (spinnerSelectedColor == this.getResources().getColor(R.color.blue_background)) {
            bgSpinner.setSelection(3);
            spinnerItem = 3;
        } else if (spinnerSelectedColor == this.getResources().getColor(R.color.green_background)) {
            bgSpinner.setSelection(4);
            spinnerItem = 4;
        } else {
            bgSpinner.setSelection(0);
            spinnerItem = 0;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        spinnerItem = i;
        switch (i) {
            case 1:
                spinnerSelectedColor = ContextCompat.getColor(this, R.color.black);
                break;
            case 2:
                spinnerSelectedColor = ContextCompat.getColor(this, R.color.red_background);
                break;
            case 3:
                spinnerSelectedColor = ContextCompat.getColor(this, R.color.blue_background);
                break;
            case 4:
                spinnerSelectedColor = ContextCompat.getColor(this, R.color.green_background);
                break;
            default:
                spinnerSelectedColor = ContextCompat.getColor(this, R.color.default_background);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}