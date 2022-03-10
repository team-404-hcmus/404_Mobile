package com.example.testproj;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;



public class FragmentBlue extends Fragment implements FragmentCallbacks{
    // this fragment shows a ListView
    public class infor
    {
        public String MSSV,HoTen,Lop,avatar;
        double DTB;

        public infor(String MSSV, String hoTen, String lop, double DTB,String avatar) {
            this.MSSV = MSSV;
            this.HoTen = hoTen;
            this.Lop = lop;
            this.DTB = DTB;
            this.avatar=avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    };
    MainActivity main; Context context = null; String message = "";
    TextView MSSV;
    ListView ListMSSV;
    int curpos=-1;

    // convenient constructor(accept arguments, copy them to a bundle, binds bundle to fragment)
    public static FragmentBlue newInstance(String strArg) {

            FragmentBlue fragment = new FragmentBlue();
            Bundle args = new Bundle();
            args.putString("strArg1", strArg);
            fragment.setArguments(args);
            return fragment;
        }
        infor [] items={
                new infor("19127614","Nguyen Anh Tuan","A1",5.0,"dude"),
                new infor("19127613","Phan Dinh Tuan","A2",9.0,"dude"),
                new infor("19127615","Vo Gia Huy","A3",10.0,"dude"),
                new infor("19127632","Nguyen Hoang Vu","A4",10.0,"dude")
        };

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            try {
                context = getActivity(); // use this reference to invoke main callbacks
                main = (MainActivity) getActivity();
            }
                catch (IllegalStateException e) {
                throw new IllegalStateException("MainActivity must implement callbacks");
            }
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            LinearLayout layout_blue = (LinearLayout) inflater.inflate(R.layout.fragment1, null);
            MSSV =layout_blue.findViewById(R.id.ID_View);
            ListMSSV =layout_blue.findViewById(R.id.ID_ListView);


            customAdapter adapter=new customAdapter((MainActivity) getActivity(),items);
            ListMSSV.setAdapter(adapter);
            ListMSSV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {

                    MSSV.setText("Mã số:" +items[pos].MSSV);
                    main.onMsgFromFragToMain("RED-FRAG",items[pos]);
                    adapter.setSelectedPos(pos);
                    curpos=pos;
                    ListMSSV.setAdapter(adapter);
                }
            });
            return layout_blue;
        }
        public void onMsgFromMainToFragment(String strvalue){
            customAdapter adapter=new customAdapter((MainActivity) getActivity(),items);
            if(strvalue.equals("First"))
            {
                MSSV.setText("Mã số:" +items[0].MSSV);
                main.onMsgFromFragToMain("RED-FRAG",items[0]);
                adapter.setSelectedPos(0);
                curpos=0;
                ListMSSV.setAdapter(adapter);
            }
            else if(strvalue.equals("Last"))
            {
                MSSV.setText("Mã số:" +items[items.length-1].MSSV);
                main.onMsgFromFragToMain("RED-FRAG",items[items.length-1]);
                adapter.setSelectedPos(items.length-1);
                curpos=items.length-1;
                ListMSSV.setAdapter(adapter);
            }
            else if(strvalue.equals("Previous"))
            {
                if(curpos>0)
                {
                    curpos=curpos-1;
                    MSSV.setText("Mã số:" +items[curpos].MSSV);
                    main.onMsgFromFragToMain("RED-FRAG",items[curpos]);
                    adapter.setSelectedPos(curpos);
                    ListMSSV.setAdapter(adapter);
                }

            }
            else if(strvalue.equals("Next"))
            {
                if(curpos<items.length-1)
                {
                    curpos=curpos+1;
                    MSSV.setText("Mã số:" +items[curpos].MSSV);
                    main.onMsgFromFragToMain("RED-FRAG",items[curpos]);
                    adapter.setSelectedPos(curpos);
                    ListMSSV.setAdapter(adapter);
                }

            }
        };
        public void onMsgFromMainToFragment(infor Value) {}
            // receiving a message from MainActivity (it may happen at any point in time)
}// class