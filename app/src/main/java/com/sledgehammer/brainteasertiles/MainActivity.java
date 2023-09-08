package com.sledgehammer.brainteasertiles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.textView);

        if (isDarkModeEnabled()) {
            setTheme(R.style.AppTheme_Dark);
            textView.setTextColor(Color.BLACK);
        } else {
            setTheme(R.style.AppTheme_Light);
            textView.setTextColor(Color.WHITE);
        }

        Button easyButton = findViewById(R.id.easyButton);
        easyButton.setTextColor(Color.BLACK);
        Button mediumButton = findViewById(R.id.mediumButton);
        mediumButton.setTextColor(Color.BLACK);
        Button hardButton = findViewById(R.id.hardButton);
        hardButton.setTextColor(Color.BLACK);

        ToggleButton toggleButton = findViewById(R.id.toggleDarkMode);

        // Set an OnCheckedChangeListener for the ToggleButton
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Save the user's preference
                saveDarkModePreference(isChecked);

                if(isChecked){
                    toggleButton.setTextOn("Light Mode");
                    toggleButton.setBackgroundColor(Color.DKGRAY);
                    toggleButton.setTextColor(Color.LTGRAY);
                    textView.setTextColor(Color.LTGRAY);
                }
                else{
                    toggleButton.setTextOff("Dark Mode");
                    toggleButton.setBackgroundColor(Color.LTGRAY);
                    toggleButton.setTextColor(Color.BLACK);
                    textView.setTextColor(Color.BLACK);
                }

                updateTextViewAppearance();
            }
        });
    }

    public void startEasyActivity(View view){
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        intent.putExtra("gridsize", "three");
        startActivity(intent);
    }

    public void startMediumActivity(View view){
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        intent.putExtra("gridsize", "four");
        startActivity(intent);
    }

    public void startHardActivity(View view){
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        intent.putExtra("gridsize", "five");
        startActivity(intent);
    }

    private boolean isDarkModeEnabled() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean test = preferences.getBoolean("dark_mode", false);
        return preferences.getBoolean("dark_mode", false); // Default to false (light mode)
    }

    private void saveDarkModePreference(boolean isEnabled) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("dark_mode", isEnabled);
        editor.apply();
    }

    private void updateTextViewAppearance() {
        ConstraintLayout mainLayout = findViewById(R.id.main_layout);

        if (isDarkModeEnabled()) {
            mainLayout.setBackgroundColor(getResources().getColor(R.color.dark_gray));
        } else {
            mainLayout.setBackgroundColor(getResources().getColor(R.color.white));
        }
    }
}