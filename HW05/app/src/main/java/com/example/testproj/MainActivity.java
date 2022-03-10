package com.example.testproj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements MainCallbacks{
    FragmentTransaction ft; FragmentRed redFragment; FragmentBlue blueFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setContentView(R.layout.activity_main);
// create a new BLUE fragment - show it
        ft = getSupportFragmentManager().beginTransaction();
        blueFragment = FragmentBlue.newInstance("first-blue");
        ft.replace(R.id.main_holder_blue, blueFragment); ft.commit();
// create a new RED fragment - show it
        ft = getSupportFragmentManager().beginTransaction();
        redFragment = FragmentRed.newInstance("first-red");
        ft.replace(R.id.main_holder_red, redFragment); ft.commit();
    }
    // MainCallback implementation (receiving messages coming from Fragments)
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