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

//        gameManager.clearObstacles();
//        loadAllButterfliesImages();

        loadAllImages();

        if (seconds % 2 == 0) {
            gameManager.randomNet();
            loadAllImages();
        }

        caughtHandler();
        gameManager.moveDown();

//        if (seconds%6==0){
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



//    public void initVisibleButterfly() {
//        seenButterfly = new PlaceInMatrix();
//        seenNets = new ArrayList<>();
//        seenButterfly.setPlace(finals.LAST_ROW_INDEX, finals.FIRST_INDEX);
//        setVisiblePlaceInMatrix(true, seenButterfly);
//    }


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
//        main_BTN_right.setOnClickListener(v -> gameManager.moveObjectRight());
//        main_BTN_left.setOnClickListener(v -> gameManager.moveObjectLeft());
        main_BTN_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameManager.moveObjectLeft();
                loadAllButterfliesImages();
            }
        });
    }


    public void changeVisibleButterfly(){
        for (int i = 0; i < finals.COLS; i++) {
            if ( gameManager.getButterflies().get(i).getVisibleStatus()== Finals.visibleStatus.VISIBLE){
                butterflyPictures[i].setVisibility(View.VISIBLE);
            }
            else{
                butterflyPictures[i].setVisibility(View.INVISIBLE);
            }
        }
    }

//    public void moveButterfly(PlaceInMatrix newPlace) {
//        if (seenButterfly != newPlace) {
//            setVisiblePlaceInMatrix(false, seenButterfly);
//        }
//        setVisiblePlaceInMatrix(true, newPlace);
//        seenButterfly.setPlace(newPlace.getRow(), newPlace.getCol());
//        updateVisibleButterfly();
//        refreshUI();
//    }
//
//    public void moveObjectLeft() {
//        PlaceInMatrix newPlace;
//        if (seenButterfly.getCol() == finals.FIRST_INDEX) {
//            newPlace = seenButterfly;
//        } else {
//            newPlace = new PlaceInMatrix().setPlace(seenButterfly.getRow(), seenButterfly.getCol() - 1);
//        }
//
//        moveButterfly(newPlace);
//    }
//
//    public void moveObjectRight() {
//        PlaceInMatrix newPlace;
//        if (seenButterfly.getCol() == finals.LAST_COL_INDEX) {
//            newPlace = seenButterfly;
//        } else {
//            newPlace = new PlaceInMatrix().setPlace(seenButterfly.getRow(), seenButterfly.getCol() + 1);
//        }
//
//        moveButterfly(newPlace);
//    }


//    public void moveNet() {
//        ArrayList<PlaceInMatrix> fallenNets = new ArrayList<>();
//        for (PlaceInMatrix net : seenNets) {
//            //for every seen net - check if it reached the butterfly row - meaning her job is over so remove it.
//            boolean shouldDeleteNet = moveObjectDownTillEnd(net);
//            if (shouldDeleteNet) {
//                fallenNets.add(net);
//            }
//        }
//
//        seenNets.removeAll(fallenNets);
//        refreshUI();
//    }


    private void setVisiblePlaceInMatrix(boolean visible, PlaceInMatrix newPlace) {
        if (visible)
            obstaclesPicturesMatrix[newPlace.getRow()][newPlace.getCol()].setVisibility(View.VISIBLE);
        if (!visible)
            obstaclesPicturesMatrix[newPlace.getRow()][newPlace.getCol()].setVisibility(View.INVISIBLE);
    }

    //Returns true if the net has reached the row of the butterflies.
//    private boolean moveObjectDownTillEnd(PlaceInMatrix net) {
//        setVisiblePlaceInMatrix(false, net.setPlace(net.getRow(), net.getCol()));
//        if (finals.LAST_ROW_INDEX != net.getRow() + 1) {
//            setVisiblePlaceInMatrix(true, net.setPlace(net.getRow() + 1, net.getCol()));
//        } else {
//            finalNetPlaceHandler(net);
//            return true;
//        }
//
//        return false;
//    }


//    private void finalNetPlaceHandler(PlaceInMatrix net) {
//        //if the net reaches the last row -
//        // the butterfly picture changes to net for a second
//        if (net.getCol() != seenButterfly.getCol()) {
//            obstaclesPicturesMatrix[net.getRow() + 1][net.getCol()].setImageResource(finals.NET_PIC);
//            setVisiblePlaceInMatrix(true, new PlaceInMatrix().setPlace(net.getRow() + 1, net.getCol()));
//        }
//    }

//    private void updateVisibleButterfly() {//every second the visible butterfly is updated
//        for (int i = 0; i < finals.COLS; i++) {
//            if (i != seenButterfly.getCol()) {
//                setVisiblePlaceInMatrix(false, new PlaceInMatrix().setPlace(finals.LAST_ROW_INDEX, i));
//                picturesMatrix[i][finals.LAST_ROW_INDEX] = null;
//            }
//        }
//    }

    private void caughtHandler() {//when the butterfly is caught its picture changes +vibrate and toast
        PlaceInMatrix placeWhereButterflyCaught = gameManager.collision();
        if (placeWhereButterflyCaught != null) {//if the butterfly is caught
            obstaclesPicturesMatrix[placeWhereButterflyCaught.getRow()][placeWhereButterflyCaught.getCol()].setImageResource(R.drawable.img_butterfly_catched);
            MySignal.getInstance().toast(finals.CAUGHT_MESSAGE);
            MySignal.getInstance().vibrate();
        }
    }


//    private void randomizeVisible() { //random a net and add to the visible nets array
//        Random rand = new Random();
//        int randCols = rand.nextInt(finals.COLS);
//        PlaceInMatrix place = new PlaceInMatrix();
//        place.setPlace(finals.FIRST_INDEX, randCols);
//        setVisiblePlaceInMatrix(true, place);
//        seenNets.add(place);
//    }


    private void refreshUI() {
        if (gameManager.getDeaths() <= gameManager.getLives()) {
            for (int i = 0; i < gameManager.getDeaths(); i++) {
                game_IMG_hearts[i].setImageResource(R.drawable.ic_brokenheart);

            }
        }
    }
}
