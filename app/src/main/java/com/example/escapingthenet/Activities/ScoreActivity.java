package com.example.escapingthenet.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.escapingthenet.Interfaces.CallBack_MapZoom;
import com.example.escapingthenet.Fragments.Fragment_List;
import com.example.escapingthenet.Fragments.MapFragment;
import com.example.escapingthenet.Model.MySPv3;
import com.example.escapingthenet.Model.Player;
import com.example.escapingthenet.MySignal;
import com.example.escapingthenet.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;


import java.util.ArrayList;
import java.util.Objects;

public class ScoreActivity extends AppCompatActivity {
    public static final String KEY_SCORE = "KEY_SCORE";
    public static final String KEY_SECONDS = "KEY_SECONDS";
    public static final String KEY_NAME = "KEY_NAME";
    public static final String KEY_MINUTES = "KEY_MINUTES";


    private MaterialTextView score_LBL_score;
    private MaterialTextView score_LBL_time;
    private Fragment_List fragment_list;
    private MapFragment fragment_map;
    private MaterialButton score_BTN_back;
    private MaterialButton score_BTN_delete;


    CallBack_MapZoom callBack_userProtocol = new CallBack_MapZoom() {
        @Override
        public void zoomOnMap(double lat, double lon) {
            showUserLocation(lat,lon);
        }

        @Override
        public void markAllPlaces(ArrayList<Player>players) {
            markAllUsersOnMap(players);
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

        fragment_list.setCallBack_map(callBack_userProtocol);
        getSupportFragmentManager().beginTransaction().add(R.id.panel_LAY_list, fragment_list).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.panel_LAY_map, fragment_map).commit();
        delete();
        openStartPage();

    }

    @SuppressLint("SetTextI18n")
    private void lastIntent() {
        Intent previousIntent = getIntent();
        if (previousIntent.getExtras() == null) {
            score_LBL_time.setVisibility(View.INVISIBLE);
            score_LBL_score.setVisibility(View.INVISIBLE);
        } else {
            int score = previousIntent.getExtras().getInt(KEY_SCORE);
            int minutes = previousIntent.getExtras().getInt(KEY_MINUTES);
            int seconds = previousIntent.getExtras().getInt(KEY_SECONDS);
            String name = previousIntent.getExtras().getString(KEY_NAME);
            boolean sec = true;
            String newSeconds = setTime(seconds, sec);
            String newMinutes = setTime(minutes, !sec);
            score_LBL_time.setText(printTime(newMinutes, newSeconds));
            score_LBL_score.setText(" " + name + " collected " + jams(score));



        }

    }

    private String jams(int score) {
        if (score < 1) {
            return "no jams";
        }
        if (score == 1) {
            return score + " jam";
        } else {
            return score + " jams";
        }
    }

    private void findViews() {
        score_LBL_score = findViewById(R.id.score_LBL_score);
        score_LBL_time = findViewById(R.id.score_LBL_time);
        score_BTN_back = findViewById(R.id.score_BTN_back);
        score_BTN_delete = findViewById(R.id.score_BTN_delete);
    }

    private String setTime(int time, boolean sec) {
        if (time == 0) { //if there are no minutes
            return "";
        } else if (time == 1 && sec) {//if there is one second
            return " " + time + " second";
        } else if (time == 1) {//if there is one second
            return " " + time + " minute";
        } else if (time > 1 && sec) {
            return " " + time + " seconds";
        }
        return " " + time + " minutes";
    }

    private String printTime(String minutes, String seconds) {
        if (minutes.isEmpty()) {
            return seconds + " later...";
        }
        if (seconds.isEmpty()) {
            return minutes + " later...";
        } else {
            return minutes + " and" + seconds + " later...";
        }
    }


    private void showUserLocation(double lat,double lon) {
        fragment_map.zoom(lat, lon);
    }

    private void markAllUsersOnMap(ArrayList<Player> players) {
        fragment_map.addAllMarkers(players);
    }


    private void openStartPage() {
        score_BTN_back.setOnClickListener(v -> {
            Intent intent = new Intent(this, StartPageActivity.class);
            startActivity(intent);
            finish();
        });

    }



    private void delete() {
        score_BTN_delete.setOnClickListener(v -> {
            MySPv3.getInstance().deleteSP();
            MySignal.getInstance().toast("please load the page again");
        });

    }

}