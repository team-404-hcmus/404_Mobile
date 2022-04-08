package com.example.testproj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends FragmentActivity implements MainCallbacks{
    FragmentTransaction ft; FragmentRed redFragment; FragmentBlue blueFragment;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setContentView(R.layout.activity_main);
        // create a new BLUE fragment - show it
        ft = getSupportFragmentManager().beginTransaction();
        blueFragment = FragmentBlue.newInstance("first-blue");
        ;
        ft.replace(R.id.main_holder_blue, blueFragment); ft.commit();
        // create a new RED fragment - show it
        ft = getSupportFragmentManager().beginTransaction();
        redFragment = FragmentRed.newInstance("first-red");
        ft.replace(R.id.main_holder_red, redFragment); ft.commit();
        deleteDatabase("HW09.db");
        createDatabase();
        createTable();
        blueFragment.sendDbObject(db);

    }
    // MainCallback implementation (receiving messages coming from Fragments)
    //Create Database
    public void createDatabase()
    {
        File storagePath = getApplication().getFilesDir();
        String myDbPath = storagePath + "/" + "HW09.db";
        try {
            db = SQLiteDatabase.openDatabase(myDbPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            if(!db.isOpen())
            {
                finish();
            }
            // here you do something with your database ...
            //db.close();
        }
        catch (SQLException e)
        {
            Log.e("Create DB","\nERROR " + e.getMessage());
        }
    }
    //Create Table
    public void createTable()
    {
        //Create 2 table LOP and HOCSINH
        db.beginTransaction();
        try {
            //drop table

            // create tablet
            db.execSQL("drop table if exists LOP");
            db.execSQL("drop table if exists HOCSINH");
            db.execSQL("create table LOP ( MaLop text PRIMARY KEY, TenLop text);");
            db.execSQL("create table HOCSINH ( MaHS text,TenHS text,MaLop text,FOREIGN KEY (MaLop) REFERENCES LOP(MaLop));");
            // commit your changes
            // commit your changes
            db.setTransactionSuccessful();
        }
        catch (SQLException e1) {
            Log.e("Add table","\nERROR " + e1.getMessage());
            finish();
        }
        finally { db.endTransaction(); }
        //insert data
        db.beginTransaction();
        try {
            // insert LOP
            db.execSQL("insert into LOP(MaLop,TenLop) " + " values ('M1','Mobile1');");
            db.execSQL("insert into LOP(MaLop,TenLop) " + " values ('M2','Mobile2');");
            db.execSQL("insert into LOP(MaLop,TenLop) " + " values ('M3','Mobile3');");
            // insert HOCSINH
            db.execSQL("insert into HOCSINH(MaHS,TenHS,MaLop) " + " values ('19127614','Nguyen Anh Tuan','M1');");
            db.execSQL("insert into HOCSINH(MaHS,TenHS,MaLop) " + " values ('19127632','Nguyen Hoang Vu','M2');");
            db.execSQL("insert into HOCSINH(MaHS,TenHS,MaLop) " + " values ('19127165','Vo Gia Huy','M1');");
            db.execSQL("insert into HOCSINH(MaHS,TenHS,MaLop) " + " values ('19127613','Phan Dinh Tuan','M3');");
            // commit your changes
            db.setTransactionSuccessful();

        }
        catch (SQLException e2)
        {
            Log.e("Add data","\nERROR " + e2.getMessage());
        }
        finally { db.endTransaction(); }

    }
    @Override
    public void onMsgFromFragToMain(String sender, FragmentBlue.infor strValue) {
    // show message arriving to MainActivity
        //red click btn
        if (sender.equals("RedClick_First"))
        {
            blueFragment.onMsgFromMainToFragment("First");
        }
        if (sender.equals("RedClick_Last"))
        {
            blueFragment.onMsgFromMainToFragment("Last");
        }
        if (sender.equals("RedClick_Previous"))
        {
            blueFragment.onMsgFromMainToFragment("Previous");
        }
        if (sender.equals("RedClick_Next"))
        {
            blueFragment.onMsgFromMainToFragment("Next");
        }
        //blue send infor
        if (sender.equals("RED-FRAG")) {
            try { // forward blue-data to redFragment using its callback method
                redFragment.onMsgFromMainToFragment(strValue);
            }
            catch (Exception e) { Log.e("ERROR", "onStrFromFragToMain" + e.getMessage()); }
        }
    }
}