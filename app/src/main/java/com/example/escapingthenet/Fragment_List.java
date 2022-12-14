package com.example.escapingthenet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

public class Fragment_List extends Fragment {

    private MaterialTextView list_LBL_title;
    private MaterialButton list_BTN_user1;

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

        changeTitle("List Page");

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
        list_LBL_title = view.findViewById(R.id.list_LBL_title);
        list_BTN_user1 = view.findViewById(R.id.list_BTN_user1);
    }
}