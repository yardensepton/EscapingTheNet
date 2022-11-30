package com.example.escapingthenet;

public abstract class GameObjectClass {
    private int locationRow;
    private int locationCol;
    private int imageRes;

    public GameObjectClass() {
    }

    public int getLocationRow() {
        return locationRow;
    }

    public void setLocationRow(int locationRow) {
        this.locationRow = locationRow;
    }

    public int getLocationCol() {
        return locationCol;
    }

    public void setLocationCol(int locationCol) {
        this.locationCol = locationCol;
    }

    public int getImageRes() {
        return imageRes;
    }

    public GameObjectClass setImageRes(int imageRes) {
        this.imageRes = imageRes;
        return this;
    }


}
