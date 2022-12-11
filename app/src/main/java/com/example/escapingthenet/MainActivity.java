package com.example.escapingthenet;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private ExtendedFloatingActionButton main_BTN_right;
    private ExtendedFloatingActionButton main_BTN_left;
    private ShapeableImageView[] game_IMG_hearts;
    private ShapeableImageView[][] obstaclesPicturesMatrix;
    private ShapeableImageView[] butterflyPictures;
    private GameManager gameManager;
    private Finals finals;
    private Timer timer;
    private long startTime = 0;
    private PlaceInMatrix seenButterfly;
    private ArrayList<PlaceInMatrix> seenNets;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);
        findViews();
        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startTimer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopTimer();
    }

    private void gameLoop() {
        long millis = System.currentTimeMillis() - startTime;
        int seconds = (int) (millis / finals.DELAY);
        seconds = seconds % finals.SIXTY;

        loadAllImages();

        if (seconds % 2 == 0) {
            gameManager.randomNet();
            loadAllImages();
        }


        caughtHandler();
        gameManager.moveDown();
        refreshUI();
//        if (seconds%4==0){
//            gameManager.randomFlower();
//        }
    }

    private void stopTimer() {
        timer.cancel();
    }

    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(() -> gameLoop());
            }
        }, finals.DELAY, finals.DELAY);
        startTime = System.currentTimeMillis();
    }

    @SuppressLint("CheckResult")
    private void findViews() {
        game_IMG_hearts = new ShapeableImageView[]{findViewById(R.id.game_IMG_heart3), findViewById(R.id.game_IMG_heart2), findViewById(R.id.game_IMG_heart1)};
        gameManager = new GameManager(game_IMG_hearts.length);
        finals = new Finals();
        main_BTN_right = findViewById(R.id.main_BTN_right);
        main_BTN_left = findViewById(R.id.main_BTN_left);

        initObstacleMatrix();
        initButterflyArray();
        gameManager.initVisibleButterfly();
        loadAllButterfliesImages();

        glides();


    }

    private void glides() {
        loadAllImages();
        AppCompatImageView main_IMG_background = findViewById(R.id.main_IMG_background);
        Glide.with(MainActivity.this).load(R.drawable.img_background).into(main_IMG_background);

    }


    private void loadAllImages() {
        for (int i = 0; i < finals.ROWS; i++) {
            for (int j = 0; j < finals.COLS; j++) {
                if (gameManager.getObjectMatrix()[i][j] != null) {
                    Glide.with(MainActivity.this).load(gameManager.getObjectMatrix()[i][j].getImageRes()).into(obstaclesPicturesMatrix[i][j]);
                    if (gameManager.getObjectMatrix()[i][j].getVisibleStatus()== Finals.visibleStatus.VISIBLE){
                        setVisiblePlaceInMatrix(true, new PlaceInMatrix().setPlace(i, j));
                    }
                    else{
                        setVisiblePlaceInMatrix(false, new PlaceInMatrix().setPlace(i, j));

                    }
                }
            }
        }
    }

    private void loadAllButterfliesImages() {
        for (int i = 0; i < finals.COLS; i++) {
            Glide.with(MainActivity.this).load(gameManager.getButterflies().get(i).getImageRes()).into(butterflyPictures[i]);
            if (gameManager.getButterflies().get(i).getVisibleStatus()== Finals.visibleStatus.VISIBLE){
                butterflyPictures[i].setVisibility(View.VISIBLE);
            }else {
                butterflyPictures[i].setVisibility(View.INVISIBLE);
            }
        }
    }




    private void initObstacleMatrix() {
        obstaclesPicturesMatrix = new ShapeableImageView[][]{
                {findViewById(R.id.main_IMG_pic1), findViewById(R.id.main_IMG_pic2), findViewById(R.id.main_IMG_pic3), findViewById(R.id.main_IMG_pic4), findViewById(R.id.main_IMG_pic5)}
                , {findViewById(R.id.main_IMG_pic6), findViewById(R.id.main_IMG_pic7), findViewById(R.id.main_IMG_pic8), findViewById(R.id.main_IMG_pic9), findViewById(R.id.main_IMG_pic10)}
                , {findViewById(R.id.main_IMG_pic11), findViewById(R.id.main_IMG_pic12), findViewById(R.id.main_IMG_pic13), findViewById(R.id.main_IMG_pic14), findViewById(R.id.main_IMG_pic15)},
                {findViewById(R.id.main_IMG_pic16), findViewById(R.id.main_IMG_pic17), findViewById(R.id.main_IMG_pic18), findViewById(R.id.main_IMG_pic19), findViewById(R.id.main_IMG_pic20)},
                {findViewById(R.id.main_IMG_pic21), findViewById(R.id.main_IMG_pic22), findViewById(R.id.main_IMG_pic23), findViewById(R.id.main_IMG_pic24), findViewById(R.id.main_IMG_pic25)},
                {findViewById(R.id.main_IMG_pic26), findViewById(R.id.main_IMG_pic27), findViewById(R.id.main_IMG_pic28), findViewById(R.id.main_IMG_pic29), findViewById(R.id.main_IMG_pic30)}
        };
    }

    private void initButterflyArray() {
        butterflyPictures = new ShapeableImageView[]{
                findViewById(R.id.main_IMG_picB), findViewById(R.id.main_IMG_picB2), findViewById(R.id.main_IMG_picB3), findViewById(R.id.main_IMG_picB4), findViewById(R.id.main_IMG_picB5)
        };

    }

    private void initViews() {
        main_BTN_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameManager.moveObjectRight();
                loadAllButterfliesImages();
            }
        });
        main_BTN_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameManager.moveObjectLeft();
                loadAllButterfliesImages();
            }
        });
    }




    private void setVisiblePlaceInMatrix(boolean visible, PlaceInMatrix newPlace) {
        if (visible)
            obstaclesPicturesMatrix[newPlace.getRow()][newPlace.getCol()].setVisibility(View.VISIBLE);
        if (!visible)
            obstaclesPicturesMatrix[newPlace.getRow()][newPlace.getCol()].setVisibility(View.INVISIBLE);
    }


    private void caughtHandler() {
        PlaceInMatrix placeWhereButterflyCaught = gameManager.collision();
        if (placeWhereButterflyCaught != null) {//if the butterfly is caught
            MySignal.getInstance().toast(finals.CAUGHT_MESSAGE);
            MySignal.getInstance().vibrate();
        }
    }



    private void refreshUI() {
        if (gameManager.getDeaths() <= gameManager.getLives()) {
            for (int i = 0; i < gameManager.getDeaths(); i++) {
                game_IMG_hearts[i].setImageResource(R.drawable.ic_brokenheart);

            }
        }
    }
}
