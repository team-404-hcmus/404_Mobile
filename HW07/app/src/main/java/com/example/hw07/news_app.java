package com.example.hw07;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class news_app extends Activity {
    Button ThanhNien,VnExpress,DanTri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_app);
        VnExpress=findViewById(R.id.VnExpressBtn);
        VnExpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callMainActivity = new Intent(news_app.this, MainActivity.class);
                //prepare a Bundle and add the input arguments: url & caption
                Bundle myData = new Bundle();
                callMainActivity.putExtras(myData);
                startActivity(callMainActivity);
            }
        });
    }
}