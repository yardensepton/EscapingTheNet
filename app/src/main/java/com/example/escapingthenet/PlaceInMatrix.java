package com.example.escapingthenet;

public class PlaceInMatrix {

    private int row;
    private int col;


    public PlaceInMatrix() {
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

