package com.example.hw03;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class registerform extends AppCompatActivity {

    EditText username,password,retype,dob;
    Button reset,signup,datePicker;
    RadioGroup gender;
    CheckBox tennis,futbal,others;
    String username_data,password_data,retype_data,dob_data,gender_data;
    ArrayList<String> hobby_data=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerform);
        username= findViewById(R.id.usernameInput);
        password= findViewById(R.id.passwordInput);
        retype= findViewById(R.id.retypeInput);
        reset=findViewById(R.id.resetBtn);
        signup=findViewById(R.id.signupBtn);
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
        //Click Sign Up
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username_data=username.getText().toString();
                password_data=password.getText().toString();
                retype_data=retype.getText().toString();
                int radioButtonId=gender.getCheckedRadioButtonId();
                Toast checkedToast;

                if (radioButtonId == -1 || username_data.isEmpty() || password_data.isEmpty()
                || retype_data.isEmpty()||dob_data==null) {
                    checkedToast=Toast.makeText(registerform.this,
                            "Please fill out all information", Toast.LENGTH_LONG);
                    checkedToast.show();
                }

                else if(!retype_data.equals(password_data))
                {
                    checkedToast=Toast.makeText(registerform.this,
                            "Retype does not match password", Toast.LENGTH_LONG);
                    checkedToast.show();
                }
                else
                {
                    RadioButton selectedRadioButton = (RadioButton) findViewById(radioButtonId);
                    gender_data = selectedRadioButton.getText().toString();
                    checkedToast=Toast.makeText(registerform.this,
                            "Sign Up successfully", Toast.LENGTH_LONG);
                    checkedToast.show();
                    changeActivity();
                }

            }
        });

        //Datepicker setup
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get Today
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
    //Change Activity
    public void changeActivity()
    {
        Intent register_To_result = new Intent (registerform.this, resultform.class);
        Bundle myBundle = new Bundle();
        myBundle.putString ("username", username_data);
        myBundle.putString ("password", password_data);
        myBundle.putString ("dob", dob_data);
        myBundle.putString ("gender", gender_data);
        register_To_result.putExtras(myBundle);
        startActivity(register_To_result);
    };
}