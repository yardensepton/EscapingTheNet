package com.example.escapingthenet;

public class Net extends GameObjectClass {
    private PlaceInMatrix place;

    public Net() {
        super(Finals.gameObject.NET);
        setImageRes(finals.NET_PIC);

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
