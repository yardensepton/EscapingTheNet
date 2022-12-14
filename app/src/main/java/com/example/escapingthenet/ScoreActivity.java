package com.example.escapingthenet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.textview.MaterialTextView;

import java.util.Objects;

public class ScoreActivity extends AppCompatActivity  {
    public static final String KEY_SCORE = "KEY_SCORE";
    public static final String KEY_SECONDS = "KEY_SECONDS";
    public static final String KEY_NAME = "KEY_NAME";
    public static final String KEY_MINUTES = "KEY_MINUTES";
    private MaterialTextView score_LBL_score;
    private MaterialTextView score_LBL_time;
    private Fragment_List fragment_list;
    private MapFragment fragment_map;

    CallBack_UserProtocol callBack_userProtocol = new CallBack_UserProtocol() {
        @Override
        public void user(String user) {
            showUserLocation(user);
        }

    };

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Objects.requireNonNull(getSupportActionBar()).hide();

        findViews();
        lastIntent();

        fragment_list = new Fragment_List();
        fragment_map = new MapFragment();


        fragment_list.setCallBack_userProtocol(callBack_userProtocol);

        getSupportFragmentManager().beginTransaction().add(R.id.panel_LAY_list, fragment_list).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.panel_LAY_map, fragment_map).commit();
    }

    @SuppressLint("SetTextI18n")
    private void lastIntent(){
        Intent previousIntent = getIntent();
        int score = previousIntent.getExtras().getInt(KEY_SCORE);
        int minutes = previousIntent.getExtras().getInt(KEY_MINUTES);
        int seconds = previousIntent.getExtras().getInt(KEY_SECONDS);
        String name = previousIntent.getExtras().getString(KEY_NAME);
        boolean sec = true;
        String newSeconds = setTime(seconds, sec);
        String newMinutes = setTime(minutes, !sec);
        score_LBL_time.setText(printTime(newMinutes,newSeconds));
        score_LBL_score.setText(" "+name + " collected " + jams(score));
    }

    private String jams(int score){
        if (score < 1){
            return "no jams";
        }
        if (score == 1){
            return score+" jam";
        }
        else{
            return score+" jams";
        }
    }

    private void findViews() {
        score_LBL_score = findViewById(R.id.score_LBL_score);
        score_LBL_time = findViewById(R.id.score_LBL_time);
    }

    private String setTime(int time, boolean sec) {
        if (time == 0) { //if there are no minutes
            return "";
        } else if (time == 1 && sec) {//if there is one second
            return " "+ time + " second";
        } else if (time == 1) {//if there is one second
            return  " "+ time + " minute";
        }else if (time>1 && sec){
            return  " "+ time + " seconds";
        }
        return  " "+ time + " minutes";
    }

    private String printTime(String minutes,String seconds){
        if (minutes.isEmpty()){
            return seconds + " later...";
        }
        if (seconds.isEmpty()){
            return minutes + " later...";
        }
        else{
            return minutes + " and " +seconds+ " later...";
        }
    }



    private void showUserLocation(String user) {
        double lat = 30.99;
        double lon = 32.67;
//        fragment_map.zoom(lat, lon);
    }


}