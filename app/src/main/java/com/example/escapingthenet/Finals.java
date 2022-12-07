package com.example.escapingthenet;

public class Finals {
    public enum visibleStatus{
        VISIBLE,INVISIBLE
    }
    public enum gameObject {
        NET,FLOWER,BUTTERFLY
    }
    public final int ROWS=6;//size of the rows
    public final int COLS=5;//size of the columns
    public final int FIRST_INDEX=0;//first index of matrix
    public final int LAST_ROW_INDEX=ROWS-1;//last possible row index
    public final int LAST_COL_INDEX=COLS-1;//last possible col index
    public final int BUTTERFLY_PIC = R.drawable.img_moving_butterfly;
    public final int NET_PIC = R.drawable.img_butterflynet;
    public final int FLOWER_PIC = R.drawable.gif_flower;
    public final int DELAY = 1000;
    public final int VIBRATE_MILLISECONDS = 500;
    public final int SIXTY = 60;
    public final String CAUGHT_MESSAGE = "Caught";
}
