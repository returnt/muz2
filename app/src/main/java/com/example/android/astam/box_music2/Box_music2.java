package com.example.android.astam.box_music2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import libraryjava.HttpRequest;
import libraryjava.MDisplay;
import libraryjava.RSing;
import libraryjava.parseJSON;

public class Box_music2 extends Activity {

    private ImageView btnGreen;
    private TextView titleCat;
    private LinearLayout catIco;
    private LinearLayout catTitle;
    private LinearLayout newsIco;
    private LinearLayout newsTitle;
    private ImageView catAll;
    private LinearLayout.LayoutParams layoutCatIco;
    private LinearLayout.LayoutParams layoutCatIcoAll;
    private LinearLayout.LayoutParams layoutCatTitle;
    private Boolean orientation;
    Player pl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        orientation = new  MDisplay().getScreenOrientation(this);
        if(orientation){
            setContentView(R.layout.activity_box_music2);

            catIco = (LinearLayout) findViewById(R.id.categoryIco);
            catTitle = (LinearLayout) findViewById(R.id.categoryTitle);
            newsIco = (LinearLayout) findViewById(R.id.newsIco);
            newsTitle = (LinearLayout) findViewById(R.id.newsTitle);
            setVerticalCat();
            setVerticalNew();
        }else {
            setContentView(R.layout.activity_box_music2_horizontal);

            catIco = (LinearLayout) findViewById(R.id.categoryIco);
            catTitle = (LinearLayout) findViewById(R.id.categoryTitle);
            newsIco = (LinearLayout) findViewById(R.id.newsIco);
            newsTitle = (LinearLayout) findViewById(R.id.newsTitle);
            setHorizontalCat();
            setHorizontalNew();
        }

        findViewById(R.id.PlayRand).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(getApplicationContext(), Player.class));

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_box_music2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, Box_music2.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setVerticalCat(){

        layoutCatIco = new LinearLayout.LayoutParams(100,  100);
        layoutCatTitle = new LinearLayout.LayoutParams(100,  100);
        layoutCatIco.setMargins(30,10,10,0);
        layoutCatTitle.setMargins(30,-10,10,10);

        for (int i = 0; i < 3; i++) {
            btnGreen = new ImageView(this);
            titleCat = new TextView(this);

            Log.d(""+i,""+Color.BLUE);
            btnGreen.setId(i);
            btnGreen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in = new Intent(getApplicationContext(), Player.class);
                    startActivityForResult(in, 100);
                }
            });

            btnGreen.setImageResource(R.drawable.wewew);
            btnGreen.setMinimumWidth(100);
            btnGreen.setMinimumHeight(100);

            titleCat.setText(i + "wewew");
            titleCat.setRotationY(-20);
            titleCat.setRotationX(30);
            titleCat.setTextColor(Color.WHITE);
            titleCat.setTextSize(16);
            titleCat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });

            catIco.addView(btnGreen, layoutCatIco);
            catTitle.addView(titleCat, layoutCatTitle);
        }
        catAll = new ImageView(this);
        layoutCatIcoAll = new LinearLayout.LayoutParams(40,70);
        layoutCatIcoAll.setMargins(10,30,10,10);
        catAll.setBackgroundResource(R.drawable.alll);
        catAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        catIco.addView(catAll, layoutCatIcoAll);
    }

    private void setVerticalNew(){

        layoutCatIco = new LinearLayout.LayoutParams(100,  100);
        layoutCatTitle = new LinearLayout.LayoutParams(100,  100);
        layoutCatIco.setMargins(30,10,10,10);
        layoutCatTitle.setMargins(30,-8,10,10);

        for (int i = 0; i < 3; i++) {
            btnGreen = new ImageView(this);
            titleCat = new TextView(this);

            Log.d(""+i,""+Color.BLUE);
            btnGreen.setId(i);
            btnGreen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Box_music2.this, Playlist.class));
                }
            });

            btnGreen.setImageResource(R.drawable.uioo);
            btnGreen.setMinimumWidth(100);
            btnGreen.setMinimumHeight(100);

            titleCat.setText(i + "wewewe");
            titleCat.setTextColor(Color.WHITE);
            titleCat.setTextSize(14);
            titleCat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Box_music2.this, Playlist.class));
                }
            });
            newsIco.addView(btnGreen, layoutCatIco);
            newsTitle.addView(titleCat, layoutCatTitle);
        }

        catAll = new ImageView(this);
        layoutCatIcoAll = new LinearLayout.LayoutParams(40,70);
        layoutCatIcoAll.setMargins(10,30,10,10);
        catAll.setBackgroundResource(R.drawable.alll);
        catAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        newsIco.addView(catAll, layoutCatIcoAll);
    }

    private void setHorizontalCat(){

        layoutCatIco = new LinearLayout.LayoutParams(100,  100);
        layoutCatTitle = new LinearLayout.LayoutParams(100,  23);
        layoutCatIco.setMargins(10,10,10,0);
        layoutCatTitle.setMargins(10,-10,10,10);

        for (int i = 0; i < 3; i++) {
            btnGreen = new ImageView(this);
            titleCat = new TextView(this);

            Log.d(""+i,""+Color.BLUE);
            btnGreen.setId(i);
            btnGreen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Box_music2.this, Playlist.class));
                }
            });

            btnGreen.setImageResource(R.drawable.wewew);
            btnGreen.setMinimumWidth(100);
            btnGreen.setMinimumHeight(100);

            titleCat.setText(i + "wewew");
            titleCat.setRotationY(-20);
            titleCat.setRotationX(30);
            titleCat.setTextColor(Color.WHITE);
            titleCat.setTextSize(16);
            titleCat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Box_music2.this, Playlist.class));
                }
            });

            catIco.addView(btnGreen, layoutCatIco);
            catIco.addView(titleCat, layoutCatTitle);
        }
        catAll = new ImageView(this);
        layoutCatIcoAll = new LinearLayout.LayoutParams(100,40);
        layoutCatIcoAll.setMargins(10,0,10,10);
        catAll.setBackgroundResource(R.drawable.alllhor);
        catAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        catIco.addView(catAll, layoutCatIcoAll);
    }

    private void setHorizontalNew(){

        layoutCatIco = new LinearLayout.LayoutParams(100,  100);
        layoutCatTitle = new LinearLayout.LayoutParams(100,  25);
        layoutCatIco.setMargins(10,0,10,0);
        layoutCatTitle.setMargins(10,0,10,10);

        for (int i = 0; i < 3; i++) {
            btnGreen = new ImageView(this);
            titleCat = new TextView(this);

            Log.d(""+i,""+Color.BLUE);
            btnGreen.setId(i);
            btnGreen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Box_music2.this, Playlist.class));
                }
            });

            btnGreen.setImageResource(R.drawable.uioo);
            btnGreen.setMinimumWidth(100);
            btnGreen.setMinimumHeight(100);

            titleCat.setText(i + "wewew");
            titleCat.setTextColor(Color.WHITE);
            titleCat.setTextSize(16);
            titleCat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Box_music2.this, Playlist.class));
                }
            });

            newsIco.addView(btnGreen, layoutCatIco);
            newsIco.addView(titleCat, layoutCatTitle);
        }

        catAll = new ImageView(this);
        layoutCatIcoAll = new LinearLayout.LayoutParams(100,40);
        layoutCatIcoAll.setMargins(10,-3,10,10);
        catAll.setBackgroundResource(R.drawable.alllhor);
        catAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        newsIco.addView(catAll, layoutCatIcoAll);
    }
}
