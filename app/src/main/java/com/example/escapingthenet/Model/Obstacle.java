package com.example.escapingthenet.Model;

import com.example.escapingthenet.Finals;
import com.example.escapingthenet.Model.GameObjectClass;
import com.example.escapingthenet.Model.PlaceInMatrix;

public class Obstacle extends GameObjectClass {
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
