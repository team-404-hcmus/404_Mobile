package com.example.hw07;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.nio.channels.Channel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends Activity {
    // Main GUI - A NEWS application based on National Public Radio RSS material
    ArrayAdapter<String> adapterMainSubjects;
    ListView myMainListView;
    Context context;

    SingleItem selectedNewsItem;
    ImageView icon;
    // hard-coding main NEWS categories (TODO: use a resource file)
    String [][] myUrlCaptionMenu;
    /*String [][] myUrlCaptionMenu = {
            {"https://vnexpress.net/rss/the-thao.rss", "Thể Thao"},
            {"https://vnexpress.net/rss/oto-xe-may.rss", "Xe"},
            {"https://vnexpress.net/rss/cuoi.rss", "Cười"},
            {"https://vnexpress.net/rss/giao-duc.rss", "Giáo dục"},
            {"https://vnexpress.net/rss/du-lich.rss", "Du lịch"},
            {"https://vnexpress.net/rss/suc-khoe.rss", "Sức khỏe"},
            {"https://vnexpress.net/rss/kinh-doanh.rss", "Kinh doanh"}
    };*/
    String[] myUrlCaption;
    String[] myUrlAddress;

    public static String niceDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("EE MMM d, yyyy",
                Locale.US);
        return sdf.format(new Date());
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //set Item rss base on newspaper
        Intent news_intent = getIntent();
        String News = news_intent.getStringExtra("News");
        if(News.equals("VnExpress"))
        {
            myUrlCaptionMenu = new String[][]{
                    {"https://vnexpress.net/rss/the-thao.rss", "Thể Thao"},
                    {"https://vnexpress.net/rss/oto-xe-may.rss", "Xe"},
                    {"https://vnexpress.net/rss/cuoi.rss", "Cười"},
                    {"https://vnexpress.net/rss/giao-duc.rss", "Giáo dục"},
                    {"https://vnexpress.net/rss/du-lich.rss", "Du lịch"},
                    {"https://vnexpress.net/rss/suc-khoe.rss", "Sức khỏe"},
                    {"https://vnexpress.net/rss/kinh-doanh.rss", "Kinh doanh"}
            };

        }
        else if(News.equals("ThanhNien"))
        {

            myUrlCaptionMenu = new String[][]{
                    {"https://thanhnien.vn/rss/thoi-su-4.rss", "Thời Sự"},
                    {"https://thanhnien.vn/rss/chao-ngay-moi-2.rss", "Chào Ngày Mới"},
                    {"https://thanhnien.vn/rss/the-gioi-66.rss", "Thế Giới"},
                    {"https://thanhnien.vn/rss/van-hoa-93.rss", "Văn Hóa"},
                    {"https://thanhnien.vn/rss/giai-tri-285.rss", "Giải Trí"},
                    {"https://thanhnien.vn/rss/the-thao-318.rss", "Thể Thao"},
                    {"https://thanhnien.vn/rss/doi-song-17.rss", "Đời Sống"},
                    {"https://thanhnien.vn/rss/tai-chinh-kinh-doanh-49.rss", "Tài Chính - Kinh Doanh"}
            };

        }
        else if(News.equals("TuoiTre"))
        {

            myUrlCaptionMenu = new String[][]{
                    {"https://tuoitre.vn/rss/the-gioi.rss", "Thế Giới"},
                    {"https://tuoitre.vn/rss/kinh-doanh.rss", "Kinh Doanh"},
                    {"https://tuoitre.vn/rss/xe.rss", "Xe"},
                    {"https://tuoitre.vn/rss/van-hoa.rss", "Văn Hóa"},
                    {"https://tuoitre.vn/rss/the-thao.rss", "Thể Thao"},
                    {"https://tuoitre.vn/rss/khoa-hoc.rss", "Khoa Học"},
                    {"https://tuoitre.vn/rss/thoi-su.rss", "Thời Sự"},
                    {"https://tuoitre.vn/rss/thu-gian.rss", "Thư Giãn"}
            };

        }
        myUrlCaption = new String[myUrlCaptionMenu.length];
        myUrlAddress = new String[myUrlCaptionMenu.length];
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i=0; i<myUrlAddress.length; i++) {
            myUrlAddress[i] = myUrlCaptionMenu[i][0]; myUrlCaption[i] = myUrlCaptionMenu[i][1];
        }
        context = getApplicationContext();
        this.setTitle("NPR Headline News\n" + niceDate() );
        // user will tap on a ListView’s row to request category’s headlines
        myMainListView = (ListView)this.findViewById(R.id.myListView);
        myMainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int _index, long _id) {
                String urlAddress = myUrlAddress[_index], urlCaption = myUrlCaption[_index];
                //create an Intent to talk to activity: ShowHeadlines
                Intent callShowHeadlines = new Intent(MainActivity.this, ShowHeadlines.class);
                //prepare a Bundle and add the input arguments: url & caption
                Bundle myData = new Bundle();
                myData.putString("urlAddress", urlAddress); myData.putString("urlCaption", urlCaption);
                callShowHeadlines.putExtras(myData);
                startActivity(callShowHeadlines);

            }
        });
        // fill up the Main-GUI’s ListView with main news categories
        int layoutID = R.layout.my_simple_list_item_1;
        adapterMainSubjects = new ArrayAdapter<String>(this,layoutID, myUrlCaption);
        myMainListView.setAdapter(adapterMainSubjects);
    }
}