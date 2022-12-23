package com.example.escapingthenet.Activities;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.escapingthenet.Finals;
import com.example.escapingthenet.Model.GameManager;
import com.example.escapingthenet.MovesDetector;
import com.example.escapingthenet.MySignal;
import com.example.escapingthenet.Model.PlaceInMatrix;
import com.example.escapingthenet.R;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;


import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import im.delight.android.location.SimpleLocation;

public class MainActivity extends AppCompatActivity{
    public static SimpleLocation location;
    public static final String DIFFICULTY = "DIFFICULTY";
    public static final String USER_NAME = "NAME";
    public static final String BUTTONS = "BUTTONS";
    public static final String SENSORS = "SENSORS";
    private MovesDetector movesDetector;
    private ExtendedFloatingActionButton main_BTN_right;
    private ExtendedFloatingActionButton main_BTN_left;
    private ShapeableImageView[] game_IMG_hearts;
    private ShapeableImageView[][] obstaclesPicturesMatrix;
    private ShapeableImageView[] jellyfishPictures;
    private TextView main_TXT_score;
    private GameManager gameManager;
    private Finals finals;
    private Timer timer;
    private long startTime = 0;
    private int seconds;
    private int minutes;
    int delay;
    private Intent previousIntent;
    private MediaPlayer[] sounds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);
        previousIntent = getIntent();
        findViews();
        initDelay();
        initViews();
        movesDetector = new MovesDetector(this, callBack_moves);
        initButtonsOrSensors();
        gameManager.getPlayer().setName(previousIntent.getExtras().getString(USER_NAME));
        location = new SimpleLocation(this);
        requestLocationPermission(location);
    }


    private MovesDetector.CallBack_moves callBack_moves = new MovesDetector.CallBack_moves() {

        @Override
        public void moveLeft() {
            gameManager.moveObjectLeft();
            loadAllJellyfishImages();
        }

        @Override
        public void moveRight() {
            gameManager.moveObjectRight();
            loadAllJellyfishImages();
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        startTimer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        movesDetector.stop();
        stopTimer();

    }


    private void gameLoop() {
        long millis = System.currentTimeMillis() - startTime;
        seconds = (int) (millis / finals.DELAY_EASY);
        minutes = seconds / 60;
        seconds = seconds % finals.SIXTY;

        loadAllImages();

        if (seconds % 2 == 0) {
            gameManager.randomObject();
            loadAllImages();
        }

        caughtHandler();
        gameManager.moveDown();

        refreshUI();
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
        }, delay, delay);

        startTime = System.currentTimeMillis();
    }

    @SuppressLint({"CheckResult", "SetTextI18n"})
    private void findViews() {
        finals = new Finals();
        game_IMG_hearts = new ShapeableImageView[]{findViewById(R.id.game_IMG_heart1), findViewById(R.id.game_IMG_heart2), findViewById(R.id.game_IMG_heart3)};
        gameManager = new GameManager(finals.LIVES);
        main_BTN_right = findViewById(R.id.main_BTN_right);
        main_BTN_left = findViewById(R.id.main_BTN_left);
        main_TXT_score = findViewById(R.id.main_TXT_score);
        main_TXT_score.setText("" + finals.FIRST_INDEX);
        initObstacleMatrix();
        initJellyfishArray();
        gameManager.initVisibleJellyfish();
        loadAllJellyfishImages();
        glides();
        initSounds();


    }

    private void initDelay() {
        String difficulty = previousIntent.getExtras().getString(DIFFICULTY);
        if (difficulty.equalsIgnoreCase(finals.EASY)) {
            delay = finals.DELAY_EASY;
        } else {
            delay = finals.DELAY_HARD;
        }
    }

    private void initButtonsOrSensors() {
        String sensors = previousIntent.getExtras().getString(SENSORS);
        if (Objects.equals(sensors, "")) {
            main_BTN_right.setVisibility(View.VISIBLE);
            main_BTN_left.setVisibility(View.VISIBLE);
        } else {
            main_BTN_right.setVisibility(View.INVISIBLE);
            main_BTN_left.setVisibility(View.INVISIBLE);
            movesDetector.start();

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

    private void initSounds() {
        sounds = new MediaPlayer[]{
                MediaPlayer.create(this, R.raw.eating_sound),
                MediaPlayer.create(this, R.raw.yay),
                MediaPlayer.create(this, R.raw.spongebob_laugh)

        };
    }

    private void initJellyfishArray() {
        jellyfishPictures = new ShapeableImageView[]{
                findViewById(R.id.main_IMG_picB), findViewById(R.id.main_IMG_picB2), findViewById(R.id.main_IMG_picB3), findViewById(R.id.main_IMG_picB4), findViewById(R.id.main_IMG_picB5)
        };

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
                    setVisiblePlaceInMatrix(gameManager.getObjectMatrix()[i][j].getVisibleStatus() == Finals.visibleStatus.VISIBLE, new PlaceInMatrix().setPlace(i, j));
                }
            }
        }
    }

    private void loadAllJellyfishImages() {
        for (int i = 0; i < finals.COLS; i++) {
            Glide.with(MainActivity.this).load(gameManager.getJellyfishArrayList().get(i).getImageRes()).into(jellyfishPictures[i]);
            if (gameManager.getJellyfishArrayList().get(i).getVisibleStatus() == Finals.visibleStatus.VISIBLE) {
                jellyfishPictures[i].setVisibility(View.VISIBLE);
            } else {
                jellyfishPictures[i].setVisibility(View.INVISIBLE);
            }
        }

    }

    private void initViews() {
        main_BTN_right.setOnClickListener(v -> {
            gameManager.moveObjectRight();
            loadAllJellyfishImages();
        });
        main_BTN_left.setOnClickListener(v -> {
            gameManager.moveObjectLeft();
            loadAllJellyfishImages();
        });
    }

    private void setVisiblePlaceInMatrix(boolean visible, PlaceInMatrix newPlace) {
        if (visible)
            obstaclesPicturesMatrix[newPlace.getRow()][newPlace.getCol()].setVisibility(View.VISIBLE);
        if (!visible)
            obstaclesPicturesMatrix[newPlace.getRow()][newPlace.getCol()].setVisibility(View.INVISIBLE);
    }

    @SuppressLint("SetTextI18n")
    private void caughtHandler() {
        PlaceInMatrix placeWhereJellyfishCaught = gameManager.addScoreOrCollision(main_BTN_right.isPressed(), main_BTN_left.isPressed());
        if (placeWhereJellyfishCaught != null && placeWhereJellyfishCaught.equals(finals.placeIfScoreIsUP)) {
            main_TXT_score.setText("" + gameManager.getPlayer().getScore());
            sounds[0].start();
        } else if (placeWhereJellyfishCaught != null) {
            obstaclesPicturesMatrix[placeWhereJellyfishCaught.getRow()][placeWhereJellyfishCaught.getCol()].setImageResource(finals.CAUGHT_PIC);
            sounds[2].start();
            MySignal.getInstance().toast(finals.CAUGHT_MESSAGE);
            MySignal.getInstance().vibrate();
        }
    }

    private void refreshUI() {
        if (gameManager.lost()) {
            stopTimer();
            openScorePage();
        } else {
            int before = countVisibleLives();
            int current = 0;
            for (int i = 0; i < finals.LIVES; i++) {
                if (current < gameManager.getDeaths()) {
                    game_IMG_hearts[current].setVisibility(View.INVISIBLE);
                } else {
                    game_IMG_hearts[i].setVisibility(View.VISIBLE);
                }
                current++;
            }
            int after = countVisibleLives();
            if (after > before) {
                sounds[1].start();
            }
        }
    }


    private int countVisibleLives() {
        int amount = 0;
        for (ShapeableImageView game_img_heart : game_IMG_hearts) {
            if (game_img_heart.getVisibility() == View.VISIBLE) {
                amount++;
            }
        }
        return amount;
    }

    private void openScorePage() {
        Intent intent = new Intent(this, ScoreActivity.class);
        intent.putExtra(ScoreActivity.KEY_SCORE, gameManager.getPlayer().getScore());
        intent.putExtra(ScoreActivity.KEY_MINUTES, minutes);
        intent.putExtra(ScoreActivity.KEY_SECONDS, seconds);

        intent.putExtra(ScoreActivity.KEY_NAME,gameManager.getPlayer().getName());
        gameManager.addUser(gameManager.getPlayer());
        startActivity(intent);
        finish();
    }



    private void requestLocationPermission(SimpleLocation location) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 101);
        } else {
            saveLocation(location);
        }
    }

    private void saveLocation(SimpleLocation location) {
        location.beginUpdates();
        gameManager.getPlayer().setLatitude(location.getLatitude());
        gameManager.getPlayer().setLongitude(location.getLongitude());
    }

}
