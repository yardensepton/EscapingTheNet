package com.example.escapingthenet;

public class Flower extends Obstacle {


    private final int SCORE = 10;


    public Flower() {
        super(Finals.gameObject.FLOWER);
        setImageRes(finals.FLOWER_PIC);
    }

    public int getSCORE() {
        return SCORE;
    }

}
