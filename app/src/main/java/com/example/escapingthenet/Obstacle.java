package com.example.escapingthenet;

public class Obstacle extends GameObjectClass{
    private PlaceInMatrix place;

    public Obstacle() {
    }

    public Obstacle(Finals.gameObject gameObjectName) {
        super(gameObjectName);
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
