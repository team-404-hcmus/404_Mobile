package com.example.testproj;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class customAdapter extends BaseAdapter {
    private Context context; // main activity’s context
    private LayoutInflater layoutInflater;
    public int selectedPos=-1;
    FragmentBlue.infor[] listData; // thumbnail data set
    public customAdapter(Context mainActivityContext,  FragmentBlue.infor[] listData)
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
        TextView MSSV;
    }
    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "drawable", pkgName);
        Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }
    public void setSelectedPos(int selecPos)
    {
        this.selectedPos=selecPos;
    }

    public int getSelectedPos() {
        return selectedPos;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.custom_list, null);
            holder = new ViewHolder();
            holder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            holder.MSSV = (TextView) convertView.findViewById(R.id.MSSV_View);
            convertView.setTag(holder);
            holder.MSSV.setText(listData[position].MSSV);
            int imageId = this.getMipmapResIdByName(listData[position].avatar);
            holder.avatar.setImageResource(imageId);
            if(position==this.selectedPos)
            {
                convertView.setBackgroundColor(Color.argb(125,75,236,90));
            }
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //MainActivity.People list = this.listData[position];
        return convertView;
    }//getView
}
