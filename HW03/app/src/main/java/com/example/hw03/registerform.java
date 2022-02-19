package com.example.hw03;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.Calendar;

public class registerform extends AppCompatActivity {

    TextView username,password,retype,dob;
    Button reset,datePicker;
    RadioGroup gender;
    CheckBox tennis,futbal,others;
    String dob_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerform);
        username= findViewById(R.id.usernameInput);
        password= findViewById(R.id.passwordInput);
        retype= findViewById(R.id.retypeInput);
        reset=findViewById(R.id.resetBtn);
        gender=findViewById(R.id.genderCheckers);
        tennis=findViewById(R.id.checkbox_Tennis);
        futbal=findViewById(R.id.checkbox_Futbal);
        others=findViewById(R.id.checkbox_Others);
        dob=findViewById(R.id.dobInput);
        datePicker=findViewById(R.id.dobBtn);
        //Click Reset Button
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username.setText("");
                password.setText("");
                retype.setText("");
                dob.setText("");
                gender.clearCheck();
                tennis.setChecked(false);
                futbal.setChecked(false);
                others.setChecked(false);
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