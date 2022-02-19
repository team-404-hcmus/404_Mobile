package com.example.hw03;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class registerform extends AppCompatActivity {

    TextView username,password,retype,dob;
    Button reset,datePicker;
    String dob_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerform);
        username= findViewById(R.id.usernameInput);
        password= findViewById(R.id.passwordInput);
        retype= findViewById(R.id.retypeInput);
        reset=findViewById(R.id.resetBtn);
        dob=findViewById(R.id.dobInput);
        datePicker=findViewById(R.id.dobBtn);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username.setText("");
                password.setText("");
                retype.setText("");
            }
        });
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year_present = calendar.get(Calendar.YEAR);
                int month_present = calendar.get(Calendar.MONTH);
                int day_present = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog= new DatePickerDialog(registerform.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day)
                            {
                                dob_data= day + "/" + month + "/" + year;
                                dob.setText(dob_data);
                            }
                        }, year_present, month_present, day_present);
                datePickerDialog.show();
            }

        });
    }
}