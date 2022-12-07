package com.example.escapingthenet;

public class PlaceInMatrix {

    private int row;
    private int col;
    private Finals.visibleStatus visibleStatus;


    public PlaceInMatrix() {
        setVisibleStatus(Finals.visibleStatus.INVISIBLE);
    }

    public Finals.visibleStatus getVisibleStatus() {
        return visibleStatus;
    }

    public void setVisibleStatus(Finals.visibleStatus visibleStatus) {
        this.visibleStatus = visibleStatus;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public PlaceInMatrix setPlace(int row, int col) {
        this.setRow(row);
        this.setCol(col);
        return this;
    }


}

