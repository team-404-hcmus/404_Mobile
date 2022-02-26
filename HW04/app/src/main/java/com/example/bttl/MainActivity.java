package com.example.bttl;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
    ListView list;
    TextView choice;

    public class People
    {
        public String name;
        public String phonenumber;
        public String avatar;
        public People(String name, String phonenumber, String avatar) {
            this.avatar= avatar;
            this.name = name;
            this.phonenumber = phonenumber;
        }

    }
    People [] data={
            new People("Tuan","0975682017","dude"),
            new People("Vu","0465465465","glasswoman"),
            new People("Huy","046545641","man"),
            new People("Tuan","0456456466","woman")
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        choice = findViewById(R.id.choice);
        list = (ListView) findViewById(R.id.phone_list);
        customAdapter adapter=new customAdapter(this,data);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                choice.setText("You choose: "+ data[position].name);
            }
        }
        );
    }

}