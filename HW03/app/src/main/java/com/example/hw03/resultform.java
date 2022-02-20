package com.example.hw03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class resultform extends AppCompatActivity {
    String username,password,dob,gender,hobbies;
    TextView username_view,pass_view,dob_view,gender_view,hobbies_view;
    Button exitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultform);
        // get data from registerform activity
        Intent getData = getIntent();
        Bundle DataBundle = getData.getExtras();
        username = DataBundle.getString("username");
        password = DataBundle.getString("password");
        dob = DataBundle.getString("dob");
        gender = DataBundle.getString("gender");

        username_view=findViewById(R.id.result_Username);
        username_view.setText(username);
        pass_view=findViewById(R.id.result_Password);
        pass_view.setText(password);
        dob_view=findViewById(R.id.result_DoB);
        dob_view.setText(dob);
        gender_view=findViewById(R.id.result_Gender);
        gender_view.setText(gender);

        exitBtn=findViewById(R.id.exit_Button);
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);

            }
        });
    }
}