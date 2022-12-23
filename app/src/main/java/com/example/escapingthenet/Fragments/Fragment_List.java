package com.example.escapingthenet.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.escapingthenet.Interfaces.CallBack_MapZoom;
import com.example.escapingthenet.Model.ListAdapter;
import com.example.escapingthenet.Model.MySPv3;
import com.example.escapingthenet.Model.Player;
import com.example.escapingthenet.Model.RecordList;
import com.example.escapingthenet.R;

public class Fragment_List extends Fragment {

    private ListView score_LV_records;
    private ListAdapter listAdapter;

    private CallBack_MapZoom callBack_mapZoom;

    public void setCallBack_map(CallBack_MapZoom callBack_mapZoom) {
        this.callBack_mapZoom = callBack_mapZoom;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        findViews(view);
        initViews();

        RecordList recordList = MySPv3.getInstance().loadFromSP();
        listAdapter = new ListAdapter(getContext(), R.layout.list_item, recordList.getTopTenRecords());
        score_LV_records.setAdapter(listAdapter);

        score_LV_records.setOnItemClickListener((parent, view1, position, id) -> {
            Player player = (Player) parent.getItemAtPosition(position);
            callBack_mapZoom.zoomOnMap(player.getLatitude(), player.getLongitude());
            callBack_mapZoom.markAllPlaces(recordList.getTopTenRecords());
        });

        return view;
    }
    private void initViews() {
    }

    private void findViews(View view) {
        score_LV_records = view.findViewById(R.id.score_LV_records);

    }
}