package com.example.escapingthenet.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.escapingthenet.CallBack_UserProtocol;
import com.example.escapingthenet.Model.ListAdapter;
import com.example.escapingthenet.Model.MySPv3;
import com.example.escapingthenet.Model.Player;
import com.example.escapingthenet.Model.RecordList;
import com.example.escapingthenet.MySignal;
import com.example.escapingthenet.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class Fragment_List extends Fragment {

    private MaterialTextView list_LBL_title;
    private MaterialButton list_BTN_user1;
    private ListView score_LV_records;
    private ListAdapter listAdapter;

    private CallBack_UserProtocol callBack_userProtocol;

    public void setCallBack_userProtocol(CallBack_UserProtocol callBack_userProtocol) {
        this.callBack_userProtocol = callBack_userProtocol;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        findViews(view);
        initViews();

        RecordList recordList = MySPv3.getInstance().loadFromSP();
        listAdapter = new ListAdapter(getContext(),R.layout.list_item,recordList.getTopTenRecords());
        score_LV_records.setAdapter(listAdapter);

        score_LV_records.setOnItemClickListener((parent,view1,position,id)->{
            Player player = (Player) parent.getItemAtPosition(position);
            MySignal.getInstance().toast(player.toString());
        });

        return view;
    }

    private void user1Clicked() {
        if (callBack_userProtocol != null) {
            callBack_userProtocol.user("Guy");
        }
    }

    public void changeTitle(String title) {
        list_LBL_title.setText(title);
    }

    private void initViews() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user1Clicked();
            }
        };

        list_BTN_user1.setOnClickListener(onClickListener);
    }

    private void findViews(View view) {
        list_BTN_user1 = view.findViewById(R.id.list_BTN_user1);
        score_LV_records = view.findViewById(R.id.score_LV_records);

    }
}