package com.example.escapingthenet;


import java.util.ArrayList;

public class GameManager {

    public Finals finals = new Finals();
    private int deaths = 0;
    private GameObjectClass[][] objectMatrix;
    private int lives;
    private Butterfly visibleButterfly;
    private ArrayList<Butterfly> butterflies;
    private int score = 0;

    public GameManager() {
    }

    public GameManager(int lives) {
        objectMatrix = new GameObjectClass[finals.ROWS][finals.COLS];
        setLives(lives);
        butterflies = DataManager.getButterflies();
        visibleButterfly = new Butterfly();
        setPlacesButterfly();

    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

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

    public GameObjectClass[][] getObjectMatrix() {
        return objectMatrix;
    }

    public int randObstacle() {
        return finals.random.nextInt(Finals.gameObject.values().length - 1);
    }

    public PlaceInMatrix randPlace() {
        int col = finals.random.nextInt(finals.COLS);
        return new PlaceInMatrix().setPlace(finals.FIRST_INDEX, col);
    }

    public PlaceInMatrix getEmptyCol() {
        int col = 0;
        for (int i = 0; i < finals.COLS; i++) {
            if (objectMatrix[finals.FIRST_INDEX][i] == null) {
                col = i;
            }

        }
        return new PlaceInMatrix().setPlace(finals.FIRST_INDEX, col);
    }


    public void randomObject() {
        int randObstacle = randObstacle();
        if (randObstacle == Finals.gameObject.NET.ordinal()) {
            randomNet();
        } else {
            randomFlower();

        }
    }

    /**
     * if the col has already visible objects that fall down - the col can't be randomized
     */
    public boolean checkIfCanBeRandomized(PlaceInMatrix place) {
        for (int i = 0; i < finals.LAST_ROW_INDEX; i++) {
            if (objectMatrix[i][place.getCol()] != null) {
                if (objectMatrix[i][place.getCol()].getVisibleStatus() != Finals.visibleStatus.INVISIBLE) {
                    return true;
                }
            }
        }
        return false;
    }

    public void randomNet() {
        PlaceInMatrix place = randPlace();
        while (checkIfCanBeRandomized(place)) {
            place = randPlace();
        }
        Net net = new Net();
        updateMatrixAndObjectPlace(net, place);
        net.setVisibleStatus(Finals.visibleStatus.VISIBLE);
        fillColWithObjects(place, Finals.gameObject.NET.ordinal());
    }

    public void randomFlower() {
        PlaceInMatrix place = randPlace();
        while (checkIfCanBeRandomized(place)) {
            place = randPlace();
        }
        Flower flower = new Flower();
        updateMatrixAndObjectPlace(flower, place);
        flower.setVisibleStatus(Finals.visibleStatus.VISIBLE);
        fillColWithObjects(place, Finals.gameObject.FLOWER.ordinal());

    }

    public void fillColWithObjects(PlaceInMatrix randPlace, int randObstacle) {
        for (int i = 1; i < finals.ROWS; i++) {
            if (randObstacle == Finals.gameObject.NET.ordinal()) {
                Net net = new Net();
                updateMatrixAndObjectPlace(net, new PlaceInMatrix().setPlace(i, randPlace.getCol()));
            } else {
                Flower flower = new Flower();
                updateMatrixAndObjectPlace(flower, new PlaceInMatrix().setPlace(i, randPlace.getCol()));
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


    public void moveDown() {
        for (int i = finals.LAST_ROW_INDEX; i > 0; i--) {
            for (int j = 0; j < finals.COLS; j++) {
                if (objectMatrix[i][j] != null && objectMatrix[i - 1][j] != null) {
                    objectMatrix[i][j].setVisibleStatus(objectMatrix[i - 1][j].getVisibleStatus());
                    if (objectMatrix[i][j].getVisibleStatus() == Finals.visibleStatus.VISIBLE) {
                        objectMatrix[i - 1][j].setVisibleStatus(Finals.visibleStatus.INVISIBLE);
                    }

                }
            }
        }
    }


    public PlaceInMatrix addScoreOrCollision(boolean right, boolean left) {

        for (int i = 0; i < finals.COLS; i++) {
            if (objectMatrix[finals.LAST_ROW_INDEX][i] != null &&
                    objectMatrix[finals.LAST_ROW_INDEX][i].getVisibleStatus() == Finals.visibleStatus.VISIBLE) {

                if (objectMatrix[finals.LAST_ROW_INDEX][i] instanceof Net) {
                    return checkCollisionAndRemoveLife(i, right, left);
                }
                if (objectMatrix[finals.LAST_ROW_INDEX][i] instanceof Flower) {
                    return checkAddScore(i, right, left);
                }
            }
        }
        return null;
    }


    public PlaceInMatrix checkCollisionAndRemoveLife(int i, boolean right, boolean left) {
        Net net = (Net) objectMatrix[finals.LAST_ROW_INDEX][i];
        if (visibleButterfly.isCaughtRight(net, right) || visibleButterfly.isCaught(net) || visibleButterfly.isCaughtLeft(net, left)) {
            deaths++;
        }
        return net.getPlace();
    }

    public PlaceInMatrix checkAddScore(int i, boolean right, boolean left) {
        Flower flower = (Flower) objectMatrix[finals.LAST_ROW_INDEX][i];
        if (visibleButterfly.isCaughtRight(flower, right) || visibleButterfly.isCaught(flower) || visibleButterfly.isCaughtLeft(flower, left)) {
            score += flower.getSCORE();
        }
        return finals.placeIfScoreIsUP;
    }


    public int getLives() {
        return lives;
    }


    public int getDeaths() {
        return deaths;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }


    private void updateMatrixAndObjectPlace(Obstacle obstacle, PlaceInMatrix place) {
        objectMatrix[place.getRow()][place.getCol()] = obstacle;
        obstacle.setPlace(place);

    }
}