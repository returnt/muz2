package com.example.android.astam.box_music2;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.net.URI;
import java.util.ArrayList;

import libraryjava.parseJSON;

public class Playlist extends ActionBarActivity {

    parseJSON jasonArray;
    String list1;
    String list2;
    String imgUrl;
    ArrayList musicNames;
    String pictureName;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        jasonArray = new parseJSON("http://muz.returnt.ru/main/getmusiccategory/1", "music");
        //ListView listView = (ListView) findViewById(R.id.lview);
        final ArrayList<String> musicNames = new ArrayList<String>();
        final ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, musicNames);
        //listView.setAdapter(adapter);

        /*ImageView imageView = (ImageView) findViewById(R.id.imageView);
        final ArrayList<ImageView> pictures = new ArrayList<ImageView>();
        final ArrayAdapter<ImageView> adapter1;
        adapter1 = new ArrayAdapter<ImageView>(this, R.layout.list_item, pictures);*/

        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                Toast.makeText(getApplicationContext(), ((TextView) itemClicked).getText(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), Player.class));
            }
        });*/

        //ImageView imageView=(ImageView) findViewById(R.id.iView);

        //ImageManager IM = new ImageManager();
        //ImageView imageView=(ImageView) findViewById(R.id.iView);


        for (int i = 0; i < jasonArray.getJsonArray().length(); i++) {
            try {
                list2 = jasonArray.getJsonArray().getJSONObject(i).getString("muz_music_author");
                list1 = jasonArray.getJsonArray().getJSONObject(i).getString("muz_music_name_def");
                pictureName = jasonArray.getJsonArray().getJSONObject(i).getString("muz_ico_name");
                imgUrl = "http://muz.returnt.ru/img/" + pictureName;

                Log.d("555666", jasonArray.doInBackground(imgUrl)+"");
                LinearLayout TR = (LinearLayout) findViewById(R.id.liner_layout_spisok);
                ImageView imageView = new ImageView(this);
                imageView.setImageBitmap(jasonArray.doInBackground(imgUrl));
                TR.addView(imageView);

                //ImageView imageView=(ImageView) findViewById(R.id.iView);

                //IM.fetchImage(imgUrl, imageView);

                Log.d("fjgjhgjhggh", list1);
                Log.d("fjgjhgjhggh", list2);
                Log.d("fjgjhgjhggh", imgUrl);
                musicNames.add(list1 + " - " + list2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_playlist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}