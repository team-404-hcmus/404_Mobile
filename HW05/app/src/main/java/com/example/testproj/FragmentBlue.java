package com.example.testproj;

import android.content.Context;
import android.os.Bundle;
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

        public infor(String MSSV, String hoTen, String lop, double DTB) {
            this.MSSV = MSSV;
            HoTen = hoTen;
            Lop = lop;
            this.DTB = DTB;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    };
    MainActivity main; Context context = null; String message = "";
    TextView MSSV;
    ListView ListMSSV;
    // data to fill-up the ListView
    public ArrayList<infor> items = new ArrayList<>();


// convenient constructor(accept arguments, copy them to a bundle, binds bundle to fragment)
public static FragmentBlue newInstance(String strArg) {
            FragmentBlue fragment = new FragmentBlue();
            Bundle args = new Bundle();
            args.putString("strArg1", strArg);
            fragment.setArguments(args);
            return fragment;
        }
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
//            items.add(new infor("19127614","Nguyen Anh Tuan","A1",5.0));
//            items.add(new infor("19127613","Phan Dinh Tuan","A2",9.0));
//            items.add(new infor("19127615","Vo Gia Huy","A3",10.0));
//            items.add(new infor("19127632","Nguyen Hoang Vu","A4",10.0));
            infor [] items={
                    new infor("19127614","Nguyen Anh Tuan","A1",5.0),
                    new infor("19127613","Phan Dinh Tuan","A2",9.0),
                    new infor("19127615","Vo Gia Huy","A3",10.0),
                    new infor("19127632","Nguyen Hoang Vu","A4",10.0)
            };
            items[0].setAvatar("dude");
            items[1].setAvatar("dude");
            items[2].setAvatar("dude");
            items[3].setAvatar("dude");
            customAdapter adapter=new customAdapter((MainActivity) getActivity(),items);
            ListMSSV.setAdapter(adapter);
            ListMSSV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                    MSSV.setText("Mã số:" +items[pos].MSSV);
                    main.onMsgFromFragToMain("RED-FRAG",items[pos]);
                }
            });
            return layout_blue;
        }

        public void onMsgFromMainToFragment(infor strValue) {

            // receiving a message from MainActivity (it may happen at any point in time)
        }
}// class