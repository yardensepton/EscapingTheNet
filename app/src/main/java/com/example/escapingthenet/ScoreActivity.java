package com.example.escapingthenet;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.textview.MaterialTextView;

import java.util.Objects;

public class ScoreActivity extends AppCompatActivity {
    public static final String KEY_SCORE = "KEY_SCORE";
    public static final String KEY_TIME = "KEY_TIME";

    private MaterialTextView score_LBL_score;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Objects.requireNonNull(getSupportActionBar()).hide();

        findViews();

        Intent previousIntent = getIntent();
        int score = previousIntent.getExtras().getInt(KEY_SCORE);
        int seconds = previousIntent.getExtras().getInt(KEY_TIME);
        score_LBL_score.setText("You collected "+score+" points"+
                "You survived for "+seconds+" seconds");
    }

    private void findViews() {
        score_LBL_score = findViewById(R.id.score_LBL_score);
    }
}