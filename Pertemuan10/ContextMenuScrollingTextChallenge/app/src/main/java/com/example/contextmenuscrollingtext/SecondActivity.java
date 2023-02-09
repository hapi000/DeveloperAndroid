package com.example.contextmenuscrollingtext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.contextmenuscrollingtext.databinding.ActivityMainBinding;
import com.example.contextmenuscrollingtext.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {
    private ActivitySecondBinding secondBinding;

    private void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        secondBinding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(secondBinding.getRoot());

        Intent intent = getIntent();
        switch (intent.getIntExtra(MainActivity.EXTRA_ACTIVITY_MAIN, 0)) {
            case 0:
                secondBinding.textView.setText(R.string.text_one);
                break;
            case 1:
                secondBinding.textView.setText(R.string.text_two);
                break;
            case 2:
                secondBinding.textView.setText(R.string.text_three);
        }

        registerForContextMenu(secondBinding.textView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:
                displayToast("Edit choice clicked.");
                return true;
            case R.id.share:
                displayToast("Share choice clicked.");
                return true;
            case R.id.delete:
                displayToast("Delete choice clicked.");
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}