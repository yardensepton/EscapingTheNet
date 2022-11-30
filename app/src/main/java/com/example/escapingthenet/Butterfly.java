package com.example.escapingthenet;

public class Butterfly extends GameObjectClass{

    private boolean caught;

    public Butterfly() {
        this.caught = false;
    }


    public boolean isCaught() {
        return caught;
    }

    public void setCaught(boolean caught) {
        this.caught = caught;
    }



}
