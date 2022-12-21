package com.example.escapingthenet;

import android.app.Application;

import com.example.escapingthenet.Model.MySPv3;

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MySPv3.init(this);
        MySignal.init(this);

    }
}