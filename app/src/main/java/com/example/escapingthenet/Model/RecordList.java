package com.example.escapingthenet.Model;

import java.util.ArrayList;
import java.util.Collections;

public class RecordList {

    private ArrayList<Player> topTenRecords = new ArrayList<>();
    private final int SIZE = 10;


    public RecordList() {

    }

    public ArrayList<Player> getTopTenRecords() {
        return topTenRecords;
    }

    public RecordList setTopTenRecords(ArrayList<Player> topTenRecords) {
        this.topTenRecords = topTenRecords;
        return this;
    }

    public void addToTopTen(Player record) {
        topTenRecords.add(record);
        if (!topTenRecords.isEmpty() || !(topTenRecords.size() < SIZE)) {
            Collections.sort(topTenRecords);
            ArrayList<Player> smallest = new ArrayList<>();
            if (topTenRecords.size() > SIZE) {
                for (int i = SIZE; i < topTenRecords.size(); i++) {
                    smallest.add(topTenRecords.get(i));
                }
                topTenRecords.removeAll(smallest);

            }

        }
    }

    @Override
    public String toString() {
        return "RecordList{" +
                "topTenRecords=" + topTenRecords +
                ", SIZE=" + SIZE +
                '}';
    }
}
