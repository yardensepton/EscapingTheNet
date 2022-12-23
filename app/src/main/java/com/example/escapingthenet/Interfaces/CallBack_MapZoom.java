package com.example.escapingthenet.Interfaces;

import com.example.escapingthenet.Model.Player;

import java.util.ArrayList;

public interface CallBack_MapZoom {
    void zoomOnMap(double lat, double lon);
    void markAllPlaces(ArrayList<Player>players);
}
