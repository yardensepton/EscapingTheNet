package com.example.escapingthenet;


import java.util.ArrayList;
import java.util.Random;

public class GameManager {

    public Finals finals = new Finals();
    private int deaths = 0;
    private GameObjectClass[][] objectMatrix;
    private int lives;
    private Butterfly visibleButterfly;
    private ArrayList<Butterfly> butterflies;
    //    private ArrayList<Net> nets;
    private ArrayList<Net> visibleNets;
    private ArrayList<Flower> visibleFlowers;
    private int score = 0;

    public GameManager() {
    }

    public GameManager(int lives) {
        objectMatrix = new GameObjectClass[finals.ROWS][finals.COLS];
        setLives(lives);
        butterflies = DataManager.getButterflies();
//        nets = DataManager.getNets();
        visibleNets = new ArrayList<>();
        visibleFlowers = new ArrayList<>();
        visibleButterfly = new Butterfly();
        setPlacesButterfly();

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    //
    public ArrayList<Butterfly> getButterflies() {
        return butterflies;
    }

    public void setPlacesButterfly() {
        for (int i = 0; i < butterflies.size(); i++) {
            butterflies.get(i).setCol(i);
        }
    }

    public void initVisibleButterfly() {
        visibleButterfly = butterflies.get(finals.FIRST_INDEX);
        visibleButterfly.setVisibleStatus(Finals.visibleStatus.VISIBLE);

    }

//    public void updateVisibleButterfly() {
//        for (int i = 0; i < butterflies.size(); i++) {
//            if (i != visibleButterfly.getCol()) {
//                visibleButterfly.setVisibleStatus(Finals.visibleStatus.INVISIBLE);
//            }
//        }
//    }


    public GameObjectClass[][] getObjectMatrix() {
        return objectMatrix;
    }

    public int randObstacle() {
        Random random = new Random();
        return random.nextInt(Finals.gameObject.values().length - 1);
    }

    public PlaceInMatrix randPlace() {
        Random random = new Random();
        int col = random.nextInt(finals.COLS);
        return new PlaceInMatrix().setPlace(finals.FIRST_INDEX, col);
    }

//    public void randomObject() {
//        PlaceInMatrix place = randPlace();
//        fillColWithObjects(place, obstacle);
//    }


//    public void whichObjectWasRandomized(PlaceInMatrix randPlace, int randObstacle) {
//        if (randObstacle == Finals.gameObject.NET.ordinal()) {
//            randomNet(randPlace);
//        } else {
//            randomFlower(randPlace);
//
//        }
//    }

    public void randomNet() {
        PlaceInMatrix place = randPlace();
        Net net = new Net();
        net.setPlace(place);
        net.setImageRes(finals.NET_PIC);
        updateObjectMatrix(net,place);
        net.setVisibleStatus(Finals.visibleStatus.VISIBLE);
        visibleNets.add(net);
        fillColWithObjects(place, Finals.gameObject.NET.ordinal());
    }

    public void randomFlower() {
        PlaceInMatrix place = randPlace();
        Flower flower = new Flower();
        updateObjectMatrix(flower, place);
        flower.setPlace(place);
        flower.setVisibleStatus(Finals.visibleStatus.VISIBLE);
        visibleFlowers.add(flower);
        fillColWithObjects(place, Finals.gameObject.NET.ordinal());

    }

    public void fillColWithObjects(PlaceInMatrix randPlace, int randObstacle) {
        for (int i = 1; i < finals.ROWS; i++) {
            if (randObstacle == Finals.gameObject.NET.ordinal()) {
                Net net = new Net();
                net.setImageRes(finals.NET_PIC);
                objectMatrix[i][randPlace.getCol()] = net;
            } else {
                Flower flower = new Flower();
                objectMatrix[i][randPlace.getCol()] = flower;
            }

        }
    }


    public void clearObstacles() {
        for (int i = 0; i < finals.COLS; i++) {
            if (objectMatrix[finals.LAST_ROW_INDEX][i] != null && objectMatrix[finals.LAST_ROW_INDEX][i].getVisibleStatus() == Finals.visibleStatus.VISIBLE) {
                objectMatrix[finals.LAST_ROW_INDEX][i] = null;
            }
        }
    }


    public void moveObjectLeft() {
        visibleButterfly.setVisibleStatus(Finals.visibleStatus.INVISIBLE);
        int currentCol = visibleButterfly.getCol();
        if (visibleButterfly != butterflies.get(finals.FIRST_INDEX)) {
            visibleButterfly = butterflies.get(currentCol - 1);
        }
        visibleButterfly.setVisibleStatus(Finals.visibleStatus.VISIBLE);

    }

    public void moveObjectRight() {
        visibleButterfly.setVisibleStatus(Finals.visibleStatus.INVISIBLE);
        int currentCol = visibleButterfly.getCol();
        if (visibleButterfly != butterflies.get(finals.LAST_COL_INDEX)) {
            visibleButterfly = butterflies.get(currentCol + 1);
        }
        visibleButterfly.setVisibleStatus(Finals.visibleStatus.VISIBLE);
    }


//    public void moveDown() {
//        ArrayList<Net> fallenNets = new ArrayList<>();
//        for (Net net : visibleNets) {
//            //for every seen net - check if it reached the butterfly row - meaning her job is over so remove it.
//            boolean shouldDeleteNet = moveObjectDownTillEnd(net);
//            if (shouldDeleteNet) {
//                fallenNets.add(net);
//            }
//        }
//
//        visibleNets.removeAll(fallenNets);
//    }

//    public void moveDown() {
//        for (int i = 0; i < finals.ROWS; i++) {
//            for (int j = 0; j < finals.COLS; j++) {
//                objectMatrix[i+1][j] = objectMatrix[i][j];
//            }
//        }
//
//    }

//    public void moveDown() {
//        for (int i = finals.LAST_ROW_INDEX; i > 0; i--) {
//            for (int j = 0; j < finals.COLS; j++) {
//                if (objectMatrix[i][j] != null && objectMatrix[i - 1][j] != null) {
//                    objectMatrix[i][j].setVisibleStatus(objectMatrix[i - 1][j].getVisibleStatus());
//                    objectMatrix[i - 1][j].setVisibleStatus(Finals.visibleStatus.INVISIBLE);
//                }
//            }
//        }
//    }


    public void moveDown() {
        for (int i = finals.LAST_ROW_INDEX; i > 0; i--) {
            for (int j = 0; j < finals.COLS; j++) {
                if (objectMatrix[i][j] != null && objectMatrix[i - 1][j] != null) {
                    objectMatrix[i][j].setVisibleStatus(objectMatrix[i - 1][j].getVisibleStatus());
                    objectMatrix[i - 1][j].setVisibleStatus(Finals.visibleStatus.INVISIBLE);

                }
            }
        }
    }



    public PlaceInMatrix collision() {
        for (Net net : visibleNets) {
            if (visibleButterfly.isCaught(net)) {
                deaths++;
                return net.getPlace();
            }
        }
        return null;
    }


//    public void setObjectMatrix() {
//        setButterflies();
//        setNets();
//    }

    public int getLives() {
        return lives;
    }

//    public void setButterflies() {
//        //set the butterflies in their places
//        // in the matrix- only last row
//        for (int i = 0; i < getButterflies().size(); i++) {
//            Butterfly butterfly = butterflies.get(i);
//            updateObjectMatrix(butterfly, finals.LAST_ROW_INDEX, i);
//            butterfly.resetCaught();
//
//        }
//    }

//    public void setNets() {
//        //set the nets in their places - all but last row.
//        for (int i = 0; i < finals.LAST_ROW_INDEX; i++) {//4
//            for (int j = 0; j < finals.COLS; j++) {
//                Net net = nets.get(j);
//                updateObjectMatrix(net, i, j);
//            }
//        }
//    }

    public int getDeaths() {
        return deaths;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

//    public PlaceInMatrix checkIfButterflyIsCaught(PlaceInMatrix placeOfButterfly, ArrayList<PlaceInMatrix> placesOfNets) {
//       Butterfly butterfly = (Butterfly) objectMatrix[placeOfButterfly.getRow()][placeOfButterfly.getCol()];
//       //for each seen net - check if it caught the butterfly, if so - return that place.
//        for (PlaceInMatrix netPlace : placesOfNets) {
//            if (butterfly.isCaught(netPlace) ) {
//                deaths++;
//                return placeOfButterfly;
//            }
//        }
//        return null;
//    }

//    private void updateObjectMatrix(GameObjectClass gameObject, int row, int col) {
//        gameObject.setPlace(row, col);
//        objectMatrix[row][col] = gameObject;
//        int num = Finals.gameObject.values().length;
//    }

    private void updateObjectMatrix(GameObjectClass gameObject, PlaceInMatrix place) {
        objectMatrix[place.getRow()][place.getCol()] = gameObject;
    }
}