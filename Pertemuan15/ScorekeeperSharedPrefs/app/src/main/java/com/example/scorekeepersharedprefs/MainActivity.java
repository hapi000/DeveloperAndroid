package com.example.scorekeepersharedprefs;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.scorekeepersharedprefs.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mainBinding;
    private int score1 = 0, score2 = 0;

    private SharedPreferences mPreferences;
    private String sharedPrefFile = "com.example.scorekeepersharedprefs";

    private final String TEAM_1 = "team1";
    private final String TEAM_2 = "team2";

    static final String STATE_SCORE_1 = "Team 1 Score";
    static final String STATE_SCORE_2 = "Team 2 Score";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else {
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.night_mode) {
            int nightMode = AppCompatDelegate.getDefaultNightMode();

            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }

            recreate();
        } else if (item.getItemId() == R.id.reset_preferences) {
            SharedPreferences.Editor editor = mPreferences.edit();

            editor.putInt(TEAM_1, 0);
            editor.putInt(TEAM_2, 0);
            editor.apply();

            mainBinding.score1.setText(String.valueOf(0));
            mainBinding.score2.setText(String.valueOf(0));

            score1 = 0; score2 = 0;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        mainBinding.increaseTeam1.setOnClickListener(this::increaseScore);
        mainBinding.increaseTeam2.setOnClickListener(this::increaseScore);

        mainBinding.decreaseTeam1.setOnClickListener(this::decreaseScore);
        mainBinding.decreaseTeam2.setOnClickListener(this::decreaseScore);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        score1 = mPreferences.getInt(TEAM_1, 0);
        score2 = mPreferences.getInt(TEAM_2, 0);

        mainBinding.score1.setText(String.valueOf(score1));
        mainBinding.score2.setText(String.valueOf(score2));
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = mPreferences.edit();

        editor.putInt(TEAM_1, score1);
        editor.putInt(TEAM_2, score2);
        editor.apply();
    }

    public void increaseScore(View view) {
        int viewID = view.getId();
        switch (viewID) {
            case R.id.increaseTeam1:
                score1++;
                mainBinding.score1.setText(String.valueOf(score1));
                break;
            case R.id.increaseTeam2:
                score2++;
                mainBinding.score2.setText(String.valueOf(score2));
                break;
        }
    }

    public void decreaseScore(View view) {
        int viewID = view.getId();
        switch (viewID) {
            case R.id.decreaseTeam1:
                score1--;
                mainBinding.score1.setText(String.valueOf(score1));
                break;
            case R.id.decreaseTeam2:
                score2--;
                mainBinding.score2.setText(String.valueOf(score2));
                break;
        }
    }
}