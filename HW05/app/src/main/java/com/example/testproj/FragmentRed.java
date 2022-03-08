package com.example.testproj;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
public class FragmentRed  extends Fragment implements FragmentCallbacks {
    MainActivity main;
    TextView MaSo,HoTen,Lop,DTB;
    Button First,Previous,Next,Last;
    public static FragmentRed newInstance(String strArg1) {
        FragmentRed fragment = new FragmentRed();
        Bundle bundle = new Bundle(); bundle.putString("arg1", strArg1);
        fragment.setArguments(bundle);
        return fragment;
    }// newInstance
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
// Activities containing this fragment must implement interface: MainCallbacks
        if (!(getActivity() instanceof MainCallbacks)) {
            throw new IllegalStateException( "Activity must implement MainCallbacks");
        }
        main = (MainActivity) getActivity(); // use this reference to invoke main callbacks
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // inflate res/layout_red.xml which includes a textview and a button
        RelativeLayout view_layout_red = (RelativeLayout) inflater.inflate(R.layout.fragment2, null);
        MaSo = (TextView) view_layout_red.findViewById(R.id.txtMaso);
        HoTen = (TextView) view_layout_red.findViewById(R.id.txtHoten);
        Lop = (TextView) view_layout_red.findViewById(R.id.txtLop);
        DTB = (TextView) view_layout_red.findViewById(R.id.txtDTB);

        First = view_layout_red.findViewById(R.id.btnFirst);
        Previous = view_layout_red.findViewById(R.id.btnPrevious);
        Next = view_layout_red.findViewById(R.id.btnNext);
        Last = view_layout_red.findViewById(R.id.btnLast);
    // show string argument supplied by constructor (if any!)
    // clicking the button changes the time displayed and sends a copy to MainActivity

        return view_layout_red;
    }
    @Override
    public void onMsgFromMainToFragment(FragmentBlue.infor strValue) {
    // receiving a message from MainActivity (it may happen at any point in time)
        MaSo.setText(strValue.MSSV);
        HoTen.setText(strValue.HoTen);
        Lop.setText(strValue.Lop);
        DTB.setText(String.valueOf(strValue.DTB));

    }
}
