package com.example.escapingthenet;

import java.util.ArrayList;

public class DataManager {

    public static ArrayList<Jellyfish> getJellyfishArrayList() {
        ArrayList<Jellyfish> jellyfishArrayList = new ArrayList<>();
        jellyfishArrayList.add(new Jellyfish());
        jellyfishArrayList.add(new Jellyfish());
        jellyfishArrayList.add(new Jellyfish());
        jellyfishArrayList.add(new Jellyfish());
        jellyfishArrayList.add(new Jellyfish());
        return jellyfishArrayList;
    }

}
