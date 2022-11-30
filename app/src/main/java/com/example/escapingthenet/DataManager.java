package com.example.escapingthenet;

import java.util.ArrayList;

public class DataManager {

    public static ArrayList<Butterfly> getButterflies() {
        ArrayList<Butterfly> butterflies = new ArrayList<>();
        butterflies.add((Butterfly) new Butterfly().setImageRes(R.drawable.ic_butterfly));
        butterflies.add((Butterfly) new Butterfly().setImageRes(R.drawable.ic_butterfly));
        butterflies.add((Butterfly) new Butterfly().setImageRes(R.drawable.ic_butterfly));
        butterflies.add((Butterfly) new Butterfly().setImageRes(R.drawable.ic_butterfly));
        return butterflies;
    }



    public static ArrayList<Net> getNets() {
        ArrayList<Net> nets = new ArrayList<>();
        nets.add((Net) new Net().setImageRes(R.drawable.img_butterflynet));
        nets.add((Net) new Net().setImageRes(R.drawable.img_butterflynet));
        nets.add((Net) new Net().setImageRes(R.drawable.img_butterflynet));
        nets.add((Net)new Net().setImageRes(R.drawable.img_butterflynet));
        nets.add((Net)new Net().setImageRes(R.drawable.img_butterflynet));
        nets.add((Net)new Net().setImageRes(R.drawable.img_butterflynet));
        nets.add((Net)new Net().setImageRes(R.drawable.img_butterflynet));
        nets.add((Net)new Net().setImageRes(R.drawable.img_butterflynet));
        nets.add((Net)new Net().setImageRes(R.drawable.img_butterflynet));
        nets.add((Net)new Net().setImageRes(R.drawable.img_butterflynet));
        nets.add((Net)new Net().setImageRes(R.drawable.img_butterflynet));
        nets.add((Net)new Net().setImageRes(R.drawable.img_butterflynet));
        nets.add((Net)new Net().setImageRes(R.drawable.img_butterflynet));
        nets.add((Net)new Net().setImageRes(R.drawable.img_butterflynet));
        nets.add((Net)new Net().setImageRes(R.drawable.img_butterflynet));
        nets.add((Net)new Net().setImageRes(R.drawable.img_butterflynet));
        return nets;
    }


}
