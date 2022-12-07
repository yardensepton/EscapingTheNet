package com.example.escapingthenet;

public class Flower extends GameObjectClass {

    private PlaceInMatrix place;
    private final int SCORE = 10;


    public Flower() {
        super(Finals.gameObject.FLOWER);
        setImageRes(finals.FLOWER_PIC);
    }

    public int getSCORE() {
        return SCORE;
    }

    public void setPlace(PlaceInMatrix placeInMatrix) {
        place = new PlaceInMatrix();
        place.setRow(placeInMatrix.getRow());
        place.setCol(placeInMatrix.getCol());
    }

    public PlaceInMatrix getPlace() {
        return place;
    }
}
