package com.example.escapingthenet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

import java.util.Objects;

public class StartPageActivity extends AppCompatActivity {

    private AppCompatCheckedTextView start_TXT_hello;
    private MaterialButton start_BTN_play;
    private AppCompatImageView start_IMG_house;
    private AppCompatImageView start_IMG_icon;
    private AppCompatImageView start_IMG_icon2;
    private ToggleButton start_TGL_difficulty;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_start_page);

        findViews();
        openGame();

    }

    private void openGame() {
        start_BTN_play.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(MainActivity.DIFFICULTY, start_TGL_difficulty.getText());
            startActivity(intent);
            finish();

        });


    }
    @SuppressLint("SetTextI18n")
    private void findViews() {
        start_TXT_hello = findViewById(R.id.start_TXT_hello);
        start_TXT_hello.setText("Escaping the net");
        start_BTN_play = findViewById(R.id.start_BTN_play);
        start_IMG_house = findViewById(R.id.start_IMG_house);
        Glide.with(StartPageActivity.this).load(R.drawable.pineapple).into(start_IMG_house);
        start_IMG_icon  = findViewById(R.id.start_IMG_jellyfish);
        Glide.with(StartPageActivity.this).load(R.drawable.flying_jellyfish).into(start_IMG_icon);
        start_IMG_icon2  = findViewById(R.id.start_IMG_spongebob);
        Glide.with(StartPageActivity.this).load(R.drawable.spongebob_running).into(start_IMG_icon2);
        start_TGL_difficulty = findViewById(R.id.start_TGL_difficulty);
    }




}