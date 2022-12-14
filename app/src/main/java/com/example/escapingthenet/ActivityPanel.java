package com.example.escapingthenet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

public class ActivityPanel extends AppCompatActivity {

    private Fragment_List fragment_list;
    private Fragment_Map fragment_map;

    CallBack_UserProtocol callBack_userProtocol = new CallBack_UserProtocol() {
        @Override
        public void user(String user) {
            showUserLocation(user);
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel);

        fragment_list = new Fragment_List();
        fragment_map = new Fragment_Map();

        fragment_list.setCallBack_userProtocol(callBack_userProtocol);

        getSupportFragmentManager().beginTransaction().add(R.id.panel_LAY_list, fragment_list).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.panel_LAY_map, fragment_map).commit();
    }


    private void showUserLocation(String user) {
        double lat = 30.99;
        double lon = 32.67;
        fragment_map.zoom(lat, lon);
    }
}
