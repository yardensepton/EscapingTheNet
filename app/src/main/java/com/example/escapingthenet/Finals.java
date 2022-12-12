package com.example.escapingthenet;

import java.util.Random;

public class Finals {
    public enum visibleStatus{
        VISIBLE,INVISIBLE
    }
    public enum gameObject {
        NET, JAM, JELLYFISH
    }
    public final int ROWS=6;//size of the rows
    public final int COLS=5;//size of the columns
    public final int FIRST_INDEX=0;//first index of matrix
    public final int LAST_ROW_INDEX=ROWS-1;//last possible row index
    public final int LAST_COL_INDEX=COLS-1;//last possible col index
    public final int SCORE_TO_ADD_LIFE=50;
    public final int JELLYFISH_PIC = R.drawable.jellyfish;
    public final int NET_PIC = R.drawable.img_net;
    public final int JAM_PIC = R.drawable.jam;
    public final int DELAY = 1000;
    public final int VIBRATE_MILLISECONDS = 500;
    public final int LIVES = 3;
    public final int SIXTY = 60;
    public final String CAUGHT_MESSAGE = "Caught";
    public final String SCORE_MESSAGE = "Yay your score is ";
    public Random random = new Random();
    public final PlaceInMatrix placeIfScoreIsUP = new PlaceInMatrix().setPlace(-1,-1);
}
