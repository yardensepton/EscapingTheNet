package com.example.escapingthenet;

import java.util.ArrayList;
public class DataManager {

    public static ArrayList<Jellyfish> getJellyfishArrayList() {
        ArrayList<Jellyfish> jellyfishArrayList = new ArrayList<>();
        jellyfishArrayList.add((Jellyfish) new Jellyfish());
        jellyfishArrayList.add((Jellyfish) new Jellyfish());
        jellyfishArrayList.add((Jellyfish) new Jellyfish());
        jellyfishArrayList.add((Jellyfish) new Jellyfish());
        jellyfishArrayList.add((Jellyfish) new Jellyfish());
        return jellyfishArrayList;
    }

}
