package com.example.escapingthenet.Model;

public class Player implements Comparable<Player> {
    private String name;
    private double latitude;
    private double longitude;
    private int score=0;

    public Player() {

    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(Player otherRecord) {//minus means the otherScore is smaller
        int otherScore = ((Player)otherRecord).getScore();
        return otherScore-this.getScore();
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", score=" + score +
                '}';
    }
}
