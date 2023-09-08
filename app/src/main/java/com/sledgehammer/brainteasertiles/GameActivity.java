package com.sledgehammer.brainteasertiles;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.security.interfaces.DSAKey;
import java.security.interfaces.DSAKeyPairGenerator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameActivity extends AppCompatActivity {
    Integer totalClicks = 0;
    List<Integer> cards = new ArrayList<Integer>();
    List<Integer> matchedNumbers = new ArrayList<Integer>();
    List<Button> buttons = new ArrayList<Button>();
    Button b;
    Integer cardCounter = 0;
    List<Integer> activeCards = new ArrayList<Integer>();
    List<Button> activeButtons = new ArrayList<Button>();

    private TextView scoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if (isDarkModeEnabled()) {
            setTheme(R.style.AppTheme_Dark);
        } else {
            setTheme(R.style.AppTheme_Light);
        }

        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String gridsize = intent.getStringExtra("gridsize");

        RelativeLayout rl = new RelativeLayout(this);

        if(gridsize.equals("three")){
            setContentView(R.layout.activity_game_three);
            rl = findViewById(R.id.relativeLayoutWithButtons);
            assignCards(9);
            assignGrid(rl, 9);
        }
        else if(gridsize.equals("four")){
            setContentView(R.layout.activity_game_four);
            rl = findViewById(R.id.relativeLayoutWithButtons);
            assignCards(16);
            assignGrid(rl, 16);
        }
        else if(gridsize.equals("five")){
            setContentView(R.layout.activity_game_five);
            rl = findViewById(R.id.relativeLayoutWithButtons);
            assignCards(25);
            assignGrid(rl, 25);
        }

        scoreTextView = findViewById(R.id.gamescoretextview);
    }

    public void assignGrid(RelativeLayout rl, Integer size){
        for(int i = 0; i < size; i++){
            View view = rl.getChildAt(i);
            Button button = (Button)view;
            button.setTextColor(Color.BLACK);
            buttons.add(button);
            button.setText(cards.get(i).toString());
        }
    }


    public void buttonPressed(View v) {
        Button button;

        if(v.getId() == R.id.button1){
            setColorOfButton(buttons.get(0));
        } else if (v.getId() == R.id.button2){
            setColorOfButton(buttons.get(1));
        } else if (v.getId() == R.id.button3) {
            setColorOfButton(buttons.get(2));
        }  else if (v.getId() == R.id.button4) {
            setColorOfButton(buttons.get(3));
        } else if (v.getId() == R.id.button5) {
            setColorOfButton(buttons.get(4));
        } else if (v.getId() == R.id.button6) {
            setColorOfButton(buttons.get(5));
        } else if (v.getId() == R.id.button7) {
            setColorOfButton(buttons.get(6));
        } else if (v.getId() == R.id.button8) {
            setColorOfButton(buttons.get(7));
        } else if (v.getId() == R.id.button9) {
            setColorOfButton(buttons.get(8));
        } else if (v.getId() == R.id.button10) {
            setColorOfButton(buttons.get(9));
        } else if (v.getId() == R.id.button11) {
            setColorOfButton(buttons.get(10));
        } else if (v.getId() == R.id.button12) {
            setColorOfButton(buttons.get(11));
        } else if (v.getId() == R.id.button13) {
            setColorOfButton(buttons.get(12));
        } else if (v.getId() == R.id.button14) {
            setColorOfButton(buttons.get(13));
        } else if (v.getId() == R.id.button15) {
            setColorOfButton(buttons.get(14));
        } else if (v.getId() == R.id.button16) {
            setColorOfButton(buttons.get(15));
        } else if (v.getId() == R.id.button17) {
            setColorOfButton(buttons.get(16));
        } else if (v.getId() == R.id.button18) {
            setColorOfButton(buttons.get(17));
        } else if (v.getId() == R.id.button19) {
            setColorOfButton(buttons.get(18));
        } else if (v.getId() == R.id.button20) {
            setColorOfButton(buttons.get(19));
        } else if (v.getId() == R.id.button21) {
            setColorOfButton(buttons.get(20));
        } else if (v.getId() == R.id.button22) {
            setColorOfButton(buttons.get(21));
        } else if (v.getId() == R.id.button23) {
            setColorOfButton(buttons.get(22));
        } else if (v.getId() == R.id.button24) {
            setColorOfButton(buttons.get(23));
        } else if (v.getId() == R.id.button25) {
            setColorOfButton(buttons.get(24));
        }
    }

    private void setColorOfButton(Button button){
        ColorDrawable buttonColor = (ColorDrawable)button.getBackground();

        if(buttonColor.getColor() == Color.BLACK){
            totalClicks++;
            scoreTextView.setText("Score: " + totalClicks.toString());

            if(cardCounter < 2){

                if (isDarkModeEnabled()) {
                    button.setBackgroundColor(Color.LTGRAY);
                } else {
                    button.setBackgroundColor(Color.WHITE);
                }

                Integer tempcard = Integer.parseInt((String)button.getText());
                Integer tempIndex = activeCards.indexOf(tempcard);

                if(activeCards.contains(tempcard) && button.getId() != activeButtons.get(tempIndex).getId()){
                    Integer tempIndice = activeCards.indexOf(tempcard);
                    Button tempButton = activeButtons.get(tempIndice);
                    tempButton.setEnabled(false);
                    button.setEnabled(false);

                    if (isDarkModeEnabled()) {
                        tempButton.setBackgroundColor(Color.DKGRAY);
                        button.setBackgroundColor(Color.DKGRAY);
                    }

                    tempButton.setTextColor(Color.LTGRAY);
                    button.setTextColor(Color.LTGRAY);

                    activeButtons = new ArrayList<Button>();
                    activeCards = new ArrayList<Integer>();
                    matchedNumbers.add(tempcard);
                    cardCounter = 0;
                }
                else if(matchedNumbers.contains(tempcard) && activeCards.size() < 1){
                    button.setEnabled(false);

                    if(isDarkModeEnabled()){
                        button.setTextColor(Color.LTGRAY);
                        button.setBackgroundColor(Color.DKGRAY);
                    }
                    else
                        button.setTextColor(Color.LTGRAY);

                    activeButtons = new ArrayList<Button>();
                    activeCards = new ArrayList<Integer>();
                    matchedNumbers.add(tempcard);
                    cardCounter = 0;
                }
                else{
                    if(cardCounter <=  1){
                        activeCards.add(tempcard);
                        activeButtons.add(button);
                        cardCounter++;
                    }
                }
            }
        }
        else{
            if(cardCounter > 0){
                if(activeButtons.size() <= 1){
                    button.setBackgroundColor(Color.BLACK);
                    activeButtons = new ArrayList<Button>();
                    activeCards = new ArrayList<Integer>();
                    cardCounter = 0;
                }
                else if(activeButtons.size() == 2){
                    activeButtons.get(0).setBackgroundColor(Color.BLACK);
                    activeButtons.get(1).setBackgroundColor(Color.BLACK);
                    activeButtons = new ArrayList<Button>();
                    activeCards = new ArrayList<Integer>();
                    cardCounter = 0;
                }
            }
        }

        try{
            if(!checkActiveButtons()){
                Intent intent = new Intent(GameActivity.this, ScoreActivity.class);
                intent.putExtra("clicks", totalClicks);
                startActivity(intent);
            }
        }
        catch(Exception e){
            String exception = e.getMessage();
        }
    }

    private Boolean checkActiveButtons(){
        RelativeLayout rl = findViewById(R.id.relativeLayoutWithButtons);

        for(int i = 0; i < rl.getChildCount(); i++){
            Button b = (Button)rl.getChildAt(i);

            if(b.isEnabled() == true){
                return true;
            }
        }
        return false;
    }

    public void assignCards(Integer size){
        int helperCount = 1;

        if(size == 9){
            for(int i = 1; i < 9; i+=2){
                cards.add(helperCount);
                cards.add(helperCount);
                helperCount++;
            }
            cards.add(helperCount - 1);
        }
        else if(size == 16){
            for(int i = 0; i < 16; i+=2){

                cards.add(helperCount);
                cards.add(helperCount);

                helperCount++;
            }
        }
        else if (size == 25){
            for(int i = 1; i < 25; i+=2){
                cards.add(helperCount);
                cards.add(helperCount);
                helperCount++;
            }
            cards.add(helperCount - 1);
        }

        Collections.shuffle(cards);
    }

    private boolean isDarkModeEnabled() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        return preferences.getBoolean("dark_mode", false); // Default to false (light mode)
    }
}
