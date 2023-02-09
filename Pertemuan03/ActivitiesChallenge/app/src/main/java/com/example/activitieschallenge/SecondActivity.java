package com.example.activitieschallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private String nama;
    private TextView view1, view2, view3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView textView = findViewById(R.id.textView);

        Intent intent = getIntent();
        Bundle extras = getIntent().getExtras();
        nama = extras.getString("bt");

        view1 = findViewById(R.id.text1_title);
        view2 = findViewById(R.id.text2_title);

        view1.setText(nama);
        view2.setText(nama);


    }
}