package com.example.escapingthenet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.maps.MapView;
import com.google.android.material.textview.MaterialTextView;

import java.util.Objects;

public class ScoreActivity extends AppCompatActivity {
    public static final String KEY_SCORE = "KEY_SCORE";
    public static final String KEY_SECONDS = "KEY_SECONDS";
    public static final String KEY_MINUTES = "KEY_MINUTES";
    private MaterialTextView score_LBL_score;
    private MaterialTextView score_LBL_time;
    MapView mapView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Objects.requireNonNull(getSupportActionBar()).hide();

        findViews();
        Intent previousIntent = getIntent();
        int score = previousIntent.getExtras().getInt(KEY_SCORE);
        int minutes = previousIntent.getExtras().getInt(KEY_MINUTES);
        int seconds = previousIntent.getExtras().getInt(KEY_SECONDS);
        String newSeconds = setTime(seconds);
        String newMinutes = setTime(minutes);
        score_LBL_time.setText(newMinutes+":"+newSeconds);
        score_LBL_score.setText("You collected "+score+" jams");

    }

    private void findViews() {
        score_LBL_score = findViewById(R.id.score_LBL_score);
        score_LBL_time = findViewById(R.id.score_LBL_time);
    }

    private String setTime(int time){
        String newTime;
        if (time<9){
            newTime= "0"+time;
        }else{
            newTime= ""+time;
        }
        return newTime;
    }






}