package com.example.escapingthenet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

import java.util.Objects;

public class StartPageActivity extends AppCompatActivity {

    private AppCompatCheckedTextView start_TXT_hello;
    private MaterialButton start_BTN_play;
    private AppCompatImageView start_IMG_grass;
    private AppCompatImageView start_IMG_icon;
    private AppCompatImageView start_IMG_icon2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_start_page);

        findViews();
        openGame();
        start_IMG_grass = findViewById(R.id.start_IMG_grass);
        Glide.with(StartPageActivity.this).load(R.drawable.pineapple).into(start_IMG_grass);
        start_IMG_icon  = findViewById(R.id.start_IMG_icon);
        Glide.with(StartPageActivity.this).load(R.drawable.flying_jellyfish).into(start_IMG_icon);
        start_IMG_icon2  = findViewById(R.id.start_IMG_icon2);
        Glide.with(StartPageActivity.this).load(R.drawable.spongebob_running).into(start_IMG_icon2);
    }

    private void openGame() {
        start_BTN_play.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();

        });


    }


    @SuppressLint("SetTextI18n")
    private void findViews() {
        start_TXT_hello = findViewById(R.id.start_TXT_hello);
        start_TXT_hello.setText("Escaping the net");
        start_BTN_play = findViewById(R.id.start_BTN_play);
    }
}