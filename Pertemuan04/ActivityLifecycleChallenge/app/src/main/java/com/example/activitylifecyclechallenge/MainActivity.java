package com.example.activitylifecyclechallenge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final int TEXT_REQUEST = 1;
    private TextView replyTextView;
    private TextView replyTextView2;
    private TextView replyTextView3;
    private TextView replyTextView4;
    private int mCount = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replyTextView = findViewById(R.id.textView);
        replyTextView2 = findViewById(R.id.textView2);
        replyTextView3 = findViewById(R.id.textView3);
        replyTextView4 = findViewById(R.id.textView4);

        if (savedInstanceState != null) {
            String[] data = savedInstanceState.getStringArray("item_list");
            mCount = savedInstanceState.getInt("total_item");

            for (int i = 1; i <= data.length; i++) {
                String reply = data[i-1];

                if (i == 1) {
                    replyTextView.setText(reply);
                } else if (i == 2) {
                    replyTextView2.setText(reply);
                } else if (i == 3) {
                    replyTextView3.setText(reply);
                } else if (i == 4) {
                    replyTextView4.setText(reply);
                }
            }
        }
    }

    public void launcher(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        String[] data = new String[4];
        data[0] = replyTextView.getText().toString();
        data[1] = replyTextView2.getText().toString();
        data[2] = replyTextView3.getText().toString();
        data[3] = replyTextView4.getText().toString();

        outState.putStringArray("item_list", data);
        outState.putInt("total_item", mCount);
    }

    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST){
            if(resultCode == RESULT_OK){
                String reply =
                        data.getStringExtra(SecondActivity.EXTRA_REPLY);
                mCount++;

                if (mCount == 1) {
                    replyTextView.setText(reply);
                } else if (mCount == 2) {
                    replyTextView2.setText(reply);
                } else if (mCount == 3) {
                    replyTextView3.setText(reply);
                } else if (mCount == 4) {
                    replyTextView4.setText(reply);
                } else {
                    mCount = 4;
                }
            }
        }
    }
}