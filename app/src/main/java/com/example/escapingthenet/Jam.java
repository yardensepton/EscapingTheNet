package com.example.escapingthenet;

public class Jam extends Obstacle {


    private final int SCORE = 10;


    public Jam() {
        super(Finals.gameObject.JAM);
        setImageRes(finals.JAM_PIC);
    }

    public int getSCORE() {
        return SCORE;
    }

}
