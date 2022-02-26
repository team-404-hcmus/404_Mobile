package com.example.bttl;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class customAdapter extends BaseAdapter {
    private Context context; // main activity’s context
    private LayoutInflater layoutInflater;
    MainActivity.People[] listData; // thumbnail data set
    public customAdapter(Context mainActivityContext,  MainActivity.People[] listData)
    {
        this.context = mainActivityContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
    }
    // how many entries are there in the data set?
    public int getCount()
    {
        return listData.length;
    }

    public Object getItem(int position)
    {
        return listData[position];
    }

    public long getItemId(int position)
    {
        return position;
    }
    static class ViewHolder {
        ImageView avatar;
        TextView name;
        TextView phonenumber;
    }
    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "drawable", pkgName);
        Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.custom_list, null);
            holder = new ViewHolder();
            holder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            holder.name = (TextView) convertView.findViewById(R.id.textname);
            holder.phonenumber = (TextView) convertView.findViewById(R.id.textphone);
            convertView.setTag(holder);
            holder.name.setText(listData[position].name);
            holder.phonenumber.setText(listData[position].phonenumber);
            int imageId = this.getMipmapResIdByName(listData[position].avatar);
            holder.avatar.setImageResource(imageId);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //MainActivity.People list = this.listData[position];
        return convertView;
    }//getView

}
