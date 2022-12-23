package com.example.escapingthenet.Model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class MySPv3 {
    private static final String DB_FILE = "DB_FILE";
    private static final String SP_KEY_RECORDS = " SP_KEY_RECORDS";



    private static MySPv3 instance = null;
    private SharedPreferences preferences;

    private MySPv3(Context context) {
        preferences = context.getSharedPreferences(DB_FILE, Context.MODE_PRIVATE);
    }

    public static void init(Context context) {
        if (instance == null)
            instance = new MySPv3(context);
    }

    public static MySPv3 getInstance() {
        return instance;
    }

    public void putInt(String key, int value) {

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public int getInt(String key, int defValue) {
        return preferences.getInt(key, defValue);
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key, String defValue) {
        return preferences.getString(key, defValue);
    }


    public void saveToSP(RecordList recordList){
        String recordsString = new Gson().toJson(recordList);
        putString(SP_KEY_RECORDS,recordsString);

    }

    public void deleteSP(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

    public RecordList loadFromSP(){
        String importGson = getString(SP_KEY_RECORDS,"");

        RecordList recordList = new Gson().fromJson(importGson,RecordList.class);

        if (recordList == null) {

            recordList = new RecordList();
        }

        return recordList;
    }
}