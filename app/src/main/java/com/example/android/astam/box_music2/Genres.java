package com.example.android.astam.box_music2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;

import libraryjava.parseJSON;


public class Genres extends ActionBarActivity {
    private parseJSON imgParser;
    private parseJSON jasonParser;
    private LinearLayout.LayoutParams img;
    private LinearLayout.LayoutParams text;
    private String pictureName;
    private String imgUrl;
    private String name_genres;
    private LinearLayout.LayoutParams rr;
    private String customHtml = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        WebView linearLayout = (WebView) findViewById(R.id.webView1);
         rr = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        img = new LinearLayout.LayoutParams(100, 100, LinearLayout.HORIZONTAL);

        text = new LinearLayout.LayoutParams(100, 100, LinearLayout.HORIZONTAL);
        imgParser = new parseJSON("http://muz.returnt.ru/main/getmusiccategory/1", "music");
        jasonParser = new parseJSON("http://muz.returnt.ru/main/getcategories", "category");
        for (int i = 0; i < jasonParser.getJsonArray().length(); i++) {
            try {
                pictureName = jasonParser.getJsonArray().getJSONObject(i).getString("muz_category_img");
                imgUrl = "http://muz.returnt.ru/img/" + pictureName;
                name_genres = jasonParser.getJsonArray().getJSONObject(i).getString("muz_category_desc");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            ImageView img_btn = new ImageView(this);
            img_btn.setImageBitmap(jasonParser.doInBackground(imgUrl));
            img_btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), Playlist.class));
                }
            });
            TextView img_tex = new TextView(this);
            img_tex.setText(name_genres);
            img_tex.setRotationY(-20);
            img_tex.setRotationX(30);
            img_tex.setTextColor(Color.BLACK);
            img_tex.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(), Playlist.class));
                }
            });

            //Log.d("5555",imgUrl );
            customHtml += "<div style='float: left; position: relative'><div style='text-align:center'><img onclick=\"javascript:alert(test.getGreeting());\" src='"+imgUrl+"' width='100'></div><div style='text-align:center'>"+name_genres+"</div></div>";
            //customHtml += "<div><img src='"+imgUrl+"' width='100' style='float: left;'></div><div style='float: left;'>"+name_genres+"</div>";
            linearLayout.getSettings().setJavaScriptEnabled(true);
            linearLayout.addJavascriptInterface(new MyJavaInterface(), "test");



        }
        linearLayout.loadData(customHtml, "text/html", "UTF-8");

    }

}
class MyJavaInterface {
    @android.webkit.JavascriptInterface
    public String getGreeting() {
        return "Hello JavaScript!";
    }
}

