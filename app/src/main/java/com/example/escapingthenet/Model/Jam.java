package com.example.escapingthenet.Model;

import com.example.escapingthenet.Finals;

public class Jam extends Obstacle {


    private final int SCORE = 1;


    public Jam() {
        super(Finals.gameObject.JAM);
        setImageRes(finals.JAM_PIC);
    }

    public int getSCORE() {
        return SCORE;
    }

}
