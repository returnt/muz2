package com.example.android.astam.box_music2;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

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
        gridview.setOnItemClickListener(gridviewOnItemClickListener);
    }

    private GridView.OnItemClickListener gridviewOnItemClickListener = new GridView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position,long id) {
            // TODO Auto-generated method stub

            // Sending image id to FullScreenActivity
            Intent i = new Intent(getApplicationContext(), Playlist.class);
            // passing array index
            switch (id+""){
                case "0": i.putExtra("category", "61951476");
                    break;
                case "1": i.putExtra("category", "61951463");
                    break;
                case "2": i.putExtra("category", "61951456");
                    break;
                case "3": i.putExtra("category", "61951452");
                    break;
                case "4": i.putExtra("category", "61951445");
                    break;
                case "5": i.putExtra("category", "61951426");
                    break;
                case "6": i.putExtra("category", "61951412");
                    break;
                case "7": i.putExtra("category", "61951404");
                    break;
                case "8": i.putExtra("category", "61951400");
                    break;
                case "9": i.putExtra("category", "61951392");
                    break;
                case "10": i.putExtra("category", "61951386");
                    break;
                case "11": i.putExtra("category", "61951383");
                    break;
                case "12": i.putExtra("category", "61951377");
                    break;
                case "13": i.putExtra("category", "61951346");
                    break;
                case "14": i.putExtra("category", "61878724");
                    break;
            }

            startActivity(i);
            Log.d("wrwr", id + "");
        }
    };
}
