package com.example.escapingthenet.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.escapingthenet.Model.Player;
import com.example.escapingthenet.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class MapFragment extends Fragment implements OnMapReadyCallback {
    private View view;
    private SupportMapFragment supportMapFragment;
    private GoogleMap map;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_map, container, false);

        supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.googleMaps);

        supportMapFragment.getMapAsync(this);

        return view;
    }

    public void zoom(double latitude, double longitude) {
        LatLng place = new LatLng(latitude, longitude);
        map.addMarker(new MarkerOptions().position(place));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(place).zoom(16).build();
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.map = googleMap;

    }

    public void addAllMarkers(ArrayList<Player> players) {
        for (Player player:players) {
            LatLng place = new LatLng(player.getLatitude(), player.getLongitude());
            map.addMarker(new MarkerOptions().position(place));
        }
    }
}