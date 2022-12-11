package com.example.escapingthenet;

public class Butterfly extends GameObjectClass {
    private boolean caught;
    private boolean coin;
    private int col;

    public Butterfly() {
        super(Finals.gameObject.BUTTERFLY);
        setImageRes(finals.BUTTERFLY_PIC);
        resetCaught();
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean isCaught(Net netPlace) {
        this.caught = netPlace.getPlace().getRow() == finals.LAST_ROW_INDEX &&
                netPlace.getPlace().getCol() == this.getCol();
        return caught;
    }

    public void resetCaught() {
        this.caught = false;
    }


}
