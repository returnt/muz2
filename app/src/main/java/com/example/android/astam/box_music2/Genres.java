package com.example.android.astam.box_music2;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        GridLayout linearLayout = (GridLayout) findViewById(R.id.linearLayout);
         rr = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        img = new LinearLayout.LayoutParams(100, 100, LinearLayout.HORIZONTAL);

        text = new LinearLayout.LayoutParams(100, 100, LinearLayout.HORIZONTAL);
        imgParser = new parseJSON("http://muz.returnt.ru/main/getmusiccategory/1", "music");
        jasonParser = new parseJSON("http://muz.returnt.ru/main/getcategories", "category");
        for (int i = 0; i < jasonParser.getJsonArray().length(); i++) {
            try {
                pictureName = imgParser.getJsonArray().getJSONObject(i).getString("muz_ico_name");
                imgUrl = "http://muz.returnt.ru/img/" + pictureName;
                name_genres = jasonParser.getJsonArray().getJSONObject(i).getString("muz_category_name");
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
            linearLayout.addView(img_tex, text);
            linearLayout.addView(img_btn, img);
        }

    }
}
