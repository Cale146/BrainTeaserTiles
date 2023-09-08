package com.sledgehammer.brainteasertiles;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if (isDarkModeEnabled()) {
            setTheme(R.style.AppTheme_Dark);
        } else {
            setTheme(R.style.AppTheme_Light);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        RelativeLayout rl = findViewById(R.id.relativeLayout);
        // rl.setBackgroundColor(Color.GRAY);

        Intent intent = getIntent();
        Integer totalClicks = intent.getIntExtra("clicks", 0);

        TextView tv = findViewById(R.id.scoretextview);
        tv.setText(totalClicks.toString());
    }

    public void startOver(View view){
        Intent intent = new Intent(ScoreActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private boolean isDarkModeEnabled() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getBoolean("dark_mode", false); // Default to false (light mode)
    }
}