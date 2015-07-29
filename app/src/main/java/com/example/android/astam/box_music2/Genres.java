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
import android.widget.GridView;
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
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageTextAdapter(this));
    }
}



