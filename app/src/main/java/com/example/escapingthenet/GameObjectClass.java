package com.example.escapingthenet;

public abstract class GameObjectClass {
    public Finals finals = new Finals();
    private int imageRes;
    private Finals.visibleStatus visibleStatus;
    private Finals.gameObject gameObjectName;

    public GameObjectClass() {

    }

    public GameObjectClass(Finals.gameObject gameObjectName) {
        setGameObjectName(gameObjectName);
        setVisibleStatus(Finals.visibleStatus.INVISIBLE);
    }



    public int getImageRes() {
        return imageRes;
    }

    public GameObjectClass setImageRes(int imageRes) {
        this.imageRes = imageRes;
        return this;
    }

    public Finals.visibleStatus getVisibleStatus() {
        return visibleStatus;
    }

    public void setVisibleStatus(Finals.visibleStatus visibleStatus) {
        this.visibleStatus = visibleStatus;
    }

    public Finals.gameObject getGameObjectName(){
        return gameObjectName;
    }


    public void setGameObjectName(Finals.gameObject gameObject){
        this.gameObjectName = gameObject;
    }
}
