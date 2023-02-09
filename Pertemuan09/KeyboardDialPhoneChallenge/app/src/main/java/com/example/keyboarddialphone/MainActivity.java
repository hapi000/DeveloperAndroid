package com.example.keyboarddialphone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    private EditText editText;
    private final String TAG = MainActivity.class.getSimpleName();

    private void dialNumber() {
        String phoneNum = null;
        if (editText != null) phoneNum = "tel:" +
                editText.getText().toString();
        Log.d(TAG, "dialNumber: " + phoneNum);
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse(phoneNum));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this!");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextPhone);
        editText.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_SEND) {
                dialNumber();
                return true;
            }
            return false;
        });
    }
}