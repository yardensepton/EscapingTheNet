package com.example.escapingthenet;

import com.example.escapingthenet.Model.Jellyfish;

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

//    public static RecordList getRecordList() {
//        RecordList recordList = new RecordList();
//        Record record1 = new Record().setUserName("yarden").setLat(39.2).setLon(41.2).setScore(10);
//        Record record2 = new Record().setUserName("lola").setLat(33.2).setLon(76.2).setScore(5);
//        recordList.addToTopTen(record1);
//        recordList.addToTopTen(record2);
//        return recordList;
//    }

}
