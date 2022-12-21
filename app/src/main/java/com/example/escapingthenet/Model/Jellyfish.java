package com.example.escapingthenet.Model;

import com.example.escapingthenet.Finals;

public class Jellyfish extends Obstacle {
    private boolean caught;
    private int col;


    public Jellyfish() {
        super(Finals.gameObject.JELLYFISH);
        setImageRes(finals.JELLYFISH_PIC);
        resetCaught();
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean isCaught(Obstacle obstacle) {
        this.caught = obstacle.getPlace().getCol() == this.getCol();
        return caught;
    }

    public boolean isCaughtRight(Obstacle obstacle,boolean right) {
        this.caught = right && obstacle.getPlace().getCol() == this.getCol()+1;
        return caught;
    }

    public boolean isCaughtLeft(Obstacle obstacle,boolean left) {
        this.caught = left && obstacle.getPlace().getCol() == this.getCol()-1;
        return caught;
    }

    public void resetCaught() {
        this.caught = false;
    }


}
