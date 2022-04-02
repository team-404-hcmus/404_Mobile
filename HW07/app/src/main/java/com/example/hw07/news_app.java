package com.example.hw07;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class news_app extends Activity {
    TextView ThanhNien,VnExpress,TuoiTre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_app);
        Intent callMainActivity = new Intent(news_app.this, MainActivity.class);
        VnExpress=findViewById(R.id.VnExpressBtn);
        VnExpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callMainActivity.putExtra("News","VnExpress");
                startActivity(callMainActivity);
            }
        });
        ThanhNien=findViewById(R.id.ThanhNienBtn);
        ThanhNien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callMainActivity.putExtra("News","ThanhNien");
                startActivity(callMainActivity);
            }
        });
        TuoiTre=findViewById(R.id.TuoiTreBtn);
        TuoiTre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callMainActivity.putExtra("News","TuoiTre");
                startActivity(callMainActivity);
            }
        });
    }
}