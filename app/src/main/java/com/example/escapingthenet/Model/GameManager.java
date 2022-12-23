package com.example.escapingthenet.Model;


import com.example.escapingthenet.DataManager;
import com.example.escapingthenet.Finals;

import java.util.ArrayList;

public class GameManager {

    public Finals finals = new Finals();
    private int deaths = 0;
    private GameObjectClass[][] objectMatrix;
    private int lives;
    private Jellyfish visibleJellyfish;
    private ArrayList<Jellyfish> jellyfishArrayList;
    private Player player;

    public GameManager() {
    }

    public GameManager(int lives) {
        objectMatrix = new GameObjectClass[finals.ROWS][finals.COLS];
        setLives(lives);
        jellyfishArrayList = DataManager.getJellyfishArrayList();
        visibleJellyfish = new Jellyfish();
        setPlacesJellyfish();
        this.player = new Player();

    }


    public void died() {
        if (deaths < finals.LIVES) {
            deaths++;
        }
    }


    public ArrayList<Jellyfish> getJellyfishArrayList() {
        return jellyfishArrayList;
    }

    public void setPlacesJellyfish() {
        for (int i = 0; i < jellyfishArrayList.size(); i++) {
            jellyfishArrayList.get(i).setCol(i);
        }
    }

    public void initVisibleJellyfish() {
        visibleJellyfish = jellyfishArrayList.get(finals.FIRST_INDEX);
        visibleJellyfish.setVisibleStatus(Finals.visibleStatus.VISIBLE);

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

    public void randomObject() {
        int randObstacle = randObstacle();
        if (randObstacle == Finals.gameObject.NET.ordinal()) {
            randomNet();
        } else {
            randomJam();

        }
    }

    /**
     * if the col has already visible objects that fall down - the col can't be randomized
     */
    public boolean checkIfCanBeRandomized(PlaceInMatrix place) {
        for (int i = 0; i < finals.LAST_ROW_INDEX; i++) {
            if (objectMatrix[i][place.getCol()] != null) {
                if (objectMatrix[i][place.getCol()].getVisibleStatus() == Finals.visibleStatus.VISIBLE) {
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

    public void randomJam() {
        PlaceInMatrix place = randPlace();
        while (checkIfCanBeRandomized(place)) {
            place = randPlace();
        }
        Jam jam = new Jam();
        updateMatrixAndObjectPlace(jam, place);
        jam.setVisibleStatus(Finals.visibleStatus.VISIBLE);
        fillColWithObjects(place, Finals.gameObject.JAM.ordinal());

    }

    public void fillColWithObjects(PlaceInMatrix randPlace, int randObstacle) {
        for (int i = 1; i < finals.ROWS; i++) {
            if (randObstacle == Finals.gameObject.NET.ordinal()) {
                Net net = new Net();
                updateMatrixAndObjectPlace(net, new PlaceInMatrix().setPlace(i, randPlace.getCol()));
            } else {
                Jam jam = new Jam();
                updateMatrixAndObjectPlace(jam, new PlaceInMatrix().setPlace(i, randPlace.getCol()));
            }

        }
    }

    public boolean lost() {
        return deaths == finals.LIVES;
    }

    public void moveObjectLeft() {
        visibleJellyfish.setVisibleStatus(Finals.visibleStatus.INVISIBLE);
        int currentCol = visibleJellyfish.getCol();
        if (visibleJellyfish != jellyfishArrayList.get(finals.FIRST_INDEX)) {
            visibleJellyfish = jellyfishArrayList.get(currentCol - 1);
        }
        visibleJellyfish.setVisibleStatus(Finals.visibleStatus.VISIBLE);
    }

    public void moveObjectRight() {
        visibleJellyfish.setVisibleStatus(Finals.visibleStatus.INVISIBLE);
        int currentCol = visibleJellyfish.getCol();
        if (visibleJellyfish != jellyfishArrayList.get(finals.LAST_COL_INDEX)) {
            visibleJellyfish = jellyfishArrayList.get(currentCol + 1);
        }
        visibleJellyfish.setVisibleStatus(Finals.visibleStatus.VISIBLE);

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
                } else if (objectMatrix[finals.LAST_ROW_INDEX][i] instanceof Jam) {
                    return checkAddScore(i, right, left);
                }
            }
        }
        return null;
    }


    public PlaceInMatrix checkCollisionAndRemoveLife(int i, boolean right, boolean left) {
        Net net = (Net) objectMatrix[finals.LAST_ROW_INDEX][i];
        if (visibleJellyfish.isCaughtRight(net, right) || visibleJellyfish.isCaught(net) || visibleJellyfish.isCaughtLeft(net, left)) {
            died();
            return net.getPlace();
        }
        return null;
    }

    public PlaceInMatrix checkAddScore(int i, boolean right, boolean left) {
        Jam jam = (Jam) objectMatrix[finals.LAST_ROW_INDEX][i];
        if (visibleJellyfish.isCaughtRight(jam, right) || visibleJellyfish.isCaught(jam) || visibleJellyfish.isCaughtLeft(jam, left)) {
            player.setScore(player.getScore() + jam.getSCORE());
            removeDeath();
            return finals.placeIfScoreIsUP;
        }
        return null;
    }

    public void removeDeath() {
        if (deaths <= finals.LIVES && deaths > 0 && player.getScore() % finals.SCORE_TO_ADD_LIFE == 0) {
            deaths--;
        }

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

    public Player getPlayer() {
        return player;
    }

    public void addUser(Player player) {
        RecordList recordList = MySPv3.getInstance().loadFromSP();
        recordList.addToTopTen(player);
        MySPv3.getInstance().saveToSP(recordList);
    }
}