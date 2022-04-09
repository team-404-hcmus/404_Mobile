package com.example.testproj;

import android.database.sqlite.SQLiteDatabase;

public interface FragmentCallbacks {
    public void onMsgFromMainToFragment(FragmentBlue.infor value);
    public void onMsgFromMainToFragment(String value);
    public void sendDbObject(SQLiteDatabase db);
}
