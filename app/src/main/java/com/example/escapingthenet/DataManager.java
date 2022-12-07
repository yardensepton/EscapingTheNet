package com.example.escapingthenet;

import java.util.ArrayList;
public class DataManager {

    static Finals finals = new Finals();

    public static ArrayList<Butterfly> getButterflies() {
        ArrayList<Butterfly> butterflies = new ArrayList<>();
        butterflies.add((Butterfly) new Butterfly().setImageRes(finals.BUTTERFLY_PIC));
        butterflies.add((Butterfly) new Butterfly().setImageRes(finals.BUTTERFLY_PIC));
        butterflies.add((Butterfly) new Butterfly().setImageRes(finals.BUTTERFLY_PIC));
        butterflies.add((Butterfly) new Butterfly().setImageRes(finals.BUTTERFLY_PIC));
        butterflies.add((Butterfly) new Butterfly().setImageRes(finals.BUTTERFLY_PIC));
        return butterflies;
    }

    public static ArrayList<Net> getNets() {
        ArrayList<Net> nets = new ArrayList<>();
        nets.add((Net) new Net().setImageRes(finals.NET_PIC));
        nets.add((Net) new Net().setImageRes(finals.NET_PIC));
        nets.add((Net) new Net().setImageRes(finals.NET_PIC));
        nets.add((Net)new Net().setImageRes(finals.NET_PIC));
        nets.add((Net)new Net().setImageRes(finals.NET_PIC));
        nets.add((Net)new Net().setImageRes(finals.NET_PIC));
        nets.add((Net)new Net().setImageRes(finals.NET_PIC));
        nets.add((Net)new Net().setImageRes(finals.NET_PIC));
        nets.add((Net)new Net().setImageRes(finals.NET_PIC));
        nets.add((Net)new Net().setImageRes(finals.NET_PIC));
        nets.add((Net)new Net().setImageRes(finals.NET_PIC));
        nets.add((Net)new Net().setImageRes(finals.NET_PIC));
        nets.add((Net)new Net().setImageRes(finals.NET_PIC));
        nets.add((Net)new Net().setImageRes(finals.NET_PIC));
        nets.add((Net)new Net().setImageRes(finals.NET_PIC));
        nets.add((Net)new Net().setImageRes(finals.NET_PIC));
        nets.add((Net)new Net().setImageRes(finals.NET_PIC));
        nets.add((Net)new Net().setImageRes(finals.NET_PIC));
        nets.add((Net)new Net().setImageRes(finals.NET_PIC));
        nets.add((Net)new Net().setImageRes(finals.NET_PIC));
        nets.add((Net)new Net().setImageRes(finals.NET_PIC));
        nets.add((Net)new Net().setImageRes(finals.NET_PIC));
        nets.add((Net)new Net().setImageRes(finals.NET_PIC));
        nets.add((Net)new Net().setImageRes(finals.NET_PIC));
        nets.add((Net)new Net().setImageRes(finals.NET_PIC));
        return nets;
    }

    public static ArrayList<Net> getFlowers() {
        ArrayList<Net> flowers = new ArrayList<>();
        flowers.add((Net) new Net().setImageRes(finals.FLOWER_PIC));
        flowers.add((Net) new Net().setImageRes(finals.FLOWER_PIC));
        flowers.add((Net) new Net().setImageRes(finals.FLOWER_PIC));
        flowers.add((Net)new Net().setImageRes(finals.FLOWER_PIC));
        flowers.add((Net)new Net().setImageRes(finals.FLOWER_PIC));
        flowers.add((Net)new Net().setImageRes(finals.FLOWER_PIC));
        flowers.add((Net)new Net().setImageRes(finals.FLOWER_PIC));
        flowers.add((Net)new Net().setImageRes(finals.FLOWER_PIC));
        flowers.add((Net)new Net().setImageRes(finals.FLOWER_PIC));
        flowers.add((Net)new Net().setImageRes(finals.FLOWER_PIC));
        flowers.add((Net)new Net().setImageRes(finals.FLOWER_PIC));
        flowers.add((Net)new Net().setImageRes(finals.FLOWER_PIC));
        flowers.add((Net)new Net().setImageRes(finals.FLOWER_PIC));
        flowers.add((Net)new Net().setImageRes(finals.FLOWER_PIC));
        flowers.add((Net)new Net().setImageRes(finals.FLOWER_PIC));
        flowers.add((Net)new Net().setImageRes(finals.FLOWER_PIC));
        flowers.add((Net)new Net().setImageRes(finals.FLOWER_PIC));
        flowers.add((Net)new Net().setImageRes(finals.FLOWER_PIC));
        flowers.add((Net)new Net().setImageRes(finals.FLOWER_PIC));
        flowers.add((Net)new Net().setImageRes(finals.FLOWER_PIC));
        flowers.add((Net)new Net().setImageRes(finals.FLOWER_PIC));
        flowers.add((Net)new Net().setImageRes(finals.FLOWER_PIC));
        flowers.add((Net)new Net().setImageRes(finals.FLOWER_PIC));
        flowers.add((Net)new Net().setImageRes(finals.FLOWER_PIC));
        flowers.add((Net)new Net().setImageRes(finals.FLOWER_PIC));
        return flowers;
    }


}
