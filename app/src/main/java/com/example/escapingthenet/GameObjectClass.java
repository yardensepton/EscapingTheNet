package com.example.escapingthenet;

public abstract class GameObjectClass {
    private PlaceInMatrix place;
    private int imageRes;

    public GameObjectClass() {
    }


    public PlaceInMatrix getPlace() {
        return place;
    }

    public void setPlace(int row, int col) {
        place = new PlaceInMatrix();
        place.setRow(row);
        place.setCol(col);
    }


    public int getImageRes() {
        return imageRes;
    }

    public GameObjectClass setImageRes(int imageRes) {
        this.imageRes = imageRes;
        return this;
    }


}
