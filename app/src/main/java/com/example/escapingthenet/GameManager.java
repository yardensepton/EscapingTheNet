package com.example.escapingthenet;
// TODO: Do more logs!! :)))

import android.util.Log;

import java.util.ArrayList;

public class GameManager {

    final Finals finals = new Finals();
    private int deaths = 0;
    private GameObjectClass[][] objectMatrix;
    private int lives;
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
            Log.i("butterfly place", "[" + finals.LAST_ROW_INDEX + "," +i + "]");

        }
    }

    public void setNets() {
        //set the nets in their places - all but last row.
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
       //for each seen net - check if it caught the butterfly, if so - return that place.
        for (PlaceInMatrix netPlace : placesOfNets) {
            if (netHit(netPlace, placeOfButterfly) ) {
                deaths++;
                return placeOfButterfly;
            }
        }
        return null;
    }

    public boolean netHit(PlaceInMatrix netPlace, PlaceInMatrix butterflyPlace) {
        return netPlace.getRow() + 1 == butterflyPlace.getRow() && netPlace.getCol() == butterflyPlace.getCol();
    }


    private void updateObjectMatrix(GameObjectClass gameObject, int row, int col) {
        gameObject.setPlace(row,col);
        objectMatrix[row][col] = gameObject;
    }
}