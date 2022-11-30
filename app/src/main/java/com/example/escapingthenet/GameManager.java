package com.example.escapingthenet;
// TODO: Do more logs!! :)))

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

public class GameManager {

    final Finals finals = new Finals();
    private int deaths = 0;
    private GameObjectClass[][] objectMatrix;
    private int lives;
    //    TODO: replace the butterfly with GameObject, maybe change the naming - instead of butterflies to escapers
    private ArrayList<Butterfly> butterflies;
    private ArrayList<Net> nets;

    public GameManager() {
    }

    public GameManager(int lives) {
        objectMatrix = new GameObjectClass[finals.ROWS][finals.COLS];
        setLives(lives);
        butterflies = DataManager.getButterflies();
        nets = DataManager.getNets();

    }


//    public void initVisibleButterfly() {
//        activeButterfly = new PlaceInMatrix();
//        activeButterfly.setPlace(finals.LAST_ROW_INDEX, finals.FIRST_INDEX);
//        setActiveButterflyObject(activeButterfly);
//    }

    public ArrayList<Butterfly> getButterflies() {
        return butterflies;
    }

    public GameObjectClass[][] getObjectMatrix() {
        return objectMatrix;
    }

    public void setObjectMatrix() {
        setButterflies();
        setNets();
    }

    public int getLives() {
        return lives;
    }

    public void setButterflies() {
        //set the butterflies in their places
        // in the matrix- only last row
        for (int i = 0; i < getButterflies().size(); i++) {
            Butterfly butterfly = butterflies.get(i);
            updateObjectMatrix(butterfly, finals.LAST_ROW_INDEX, i);
        }
    }

    public void setNets() {
        //set the nets in their places
        for (int i = 0; i < finals.LAST_ROW_INDEX; i++) {//4
            for (int j = 0; j < finals.COLS; j++) {
                Net net = nets.get(j);
                updateObjectMatrix(net, i, j);
                Log.i("net place", "[" + i + "," + j + "]");
            }
        }
    }

    public int getDeaths() {
        return deaths;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public PlaceInMatrix checkIfButterflyIsCaught(PlaceInMatrix placeOfButterfly, ArrayList<PlaceInMatrix> placesOfNets) {
        for (PlaceInMatrix netPlace : placesOfNets) {
            if (didNetHit(netPlace, placeOfButterfly)) {
                deaths++;
                Log.i("deaths", "" + deaths);
                Log.i("net who caught", "" + netPlace.getRow() + "," + netPlace.getCol());
                return placeOfButterfly;
            }

        }
        return null;
    }


//    public void checkIfButterflyIsCaught(ArrayList<PlaceInMatrix> placesOfButterfly, ArrayList<PlaceInMatrix> placesOfNets) {
//        Log.i("size of visible places - butterfly", "" + placesOfButterfly.size());
//        Log.i("size of visible places - net", "" + placesOfNets.size());
//        for (PlaceInMatrix butterflyPlace : placesOfButterfly) {
//            for (PlaceInMatrix netPlace : placesOfNets) {
//                setCaught(netPlace, butterflyPlace);
//                if (lastMovedButterfly.isCaught()) {
//                    deaths++;
//                    Log.i("deaths", "" + deaths);
//
//                }
//            }
//        }
//    }

//    public void setCaught(PlaceInMatrix netPlace, PlaceInMatrix butterflyPlace) {
//        if (netFalls(netPlace, butterflyPlace)) {
//            activeButterflyObject.setCaught(true);
//        } else {
//            activeButterflyObject.setCaught(false);
//
//        }
//    }


    public boolean didNetHit(PlaceInMatrix netPlace, PlaceInMatrix butterflyPlace) {
        boolean bool = netPlace.getRow() + 1 == butterflyPlace.getRow() && netPlace.getCol() == butterflyPlace.getCol();
        Log.i("did net hit?", "" + bool);

        return bool;
    }

    public boolean netFromLeft(PlaceInMatrix netPlace, PlaceInMatrix butterflyPlace) {
        boolean bool = netPlace.getRow() == butterflyPlace.getRow() && netPlace.getCol() == butterflyPlace.getCol();

        Log.i("net left", "" + bool);
        return netPlace.getRow() == butterflyPlace.getRow() && netPlace.getCol() - 1 == butterflyPlace.getCol();


    }

    public boolean netFromRight(PlaceInMatrix netPlace, PlaceInMatrix butterflyPlace) {
        boolean bool = netPlace.getRow() == butterflyPlace.getRow() && netPlace.getCol() + 1 == butterflyPlace.getCol();
        Log.i("net left", "" + bool);
        return netPlace.getRow() == butterflyPlace.getRow() && netPlace.getCol() + 1 == butterflyPlace.getCol();


    }
//    public void netOnButterfly(PlaceInMatrix netPlace, PlaceInMatrix butterflyPlace) {
//        if (netPlace.getRow() + 1 == butterflyPlace.getRow() && netPlace.getCol() != butterflyPlace.getCol()) {
//            objectMatrix[butterflyPlace.getRow()][butterflyPlace.getCol()].setImageRes(finals.NET_PIC);
//        }
//    }

    public void setDeaths(HashMap<PlaceInMatrix, Boolean> nextTo) {
        for (PlaceInMatrix place : nextTo.keySet()) {
            if (Boolean.TRUE.equals(nextTo.get(place))) {
                deaths++;
                Log.i("deaths", "" + deaths);
            }

        }
    }




    public boolean isLose() {
        return deaths == lives;
    }


    private void updateObjectMatrix(GameObjectClass gameObjectClass, int row, int col) {
        gameObjectClass.setLocationRow(row);
        gameObjectClass.setLocationCol(col);
        objectMatrix[row][col] = gameObjectClass;
    }
}