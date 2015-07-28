package com.example.android.astam.box_music2;

import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import java.util.ArrayList;
import libraryjava.parseJSON;

public class Playlist extends ActionBarActivity {

    int idSong;
    private parseJSON jasonArray;
    String title, artist, imgUrl;
    //ArrayList musicNames;
    String pictureName;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        jasonArray = new parseJSON("https://api.vk.com/method/audio.get?owner_id=20111260&&access_token=8b9c746a06252d374feb71641aacc858a6d902136783354f65d314a9397784556e27ff182fe4a36e55c95&album_id="+getIntent().getStringExtra("category"), "response");

        LinearLayout.LayoutParams imgLL = new LinearLayout.LayoutParams(85, 85);
        ListView listView = (ListView) findViewById(R.id.lview);
        final ArrayList<String> musicNames = new ArrayList<String>();
        final ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, musicNames);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
                Toast.makeText(getApplicationContext(), ((TextView) itemClicked).getText(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Player.class);
                //long songId = adapter.getItemId(position);
                intent.putExtra("musicID", String.valueOf(position+1));
                //Log.d("999999999999999999", String.valueOf(id));
                Log.d("55555555555555555555", String.valueOf(position));
                startActivity(intent);
            }
        });

        for (int i = 0; i < jasonArray.getJsonArray().length(); i++) {
            try {
                //idSong = jasonArray.getJsonArray().getJSONObject(i).getInt("aid");
                artist = jasonArray.getJsonArray().getJSONObject(i).getString("artist");
                title = jasonArray.getJsonArray().getJSONObject(i).getString("title");
                pictureName = jasonArray.getJsonArray().getJSONObject(i).getString("muz_ico_name");
                imgUrl = "http://muz.returnt.ru/img/" + pictureName;

                TextView textView = (TextView) findViewById(R.id.text);
                Log.d("muz_ico_name--------------------", jasonArray.doInBackground(imgUrl) + "");
                LinearLayout lL = (LinearLayout) findViewById(R.id.imgLinerL);
                ImageView imageView = new ImageView(this);
                imageView.setImageBitmap(jasonArray.doInBackground(imgUrl));
                imageView.setAdjustViewBounds(true);
                //imageView.setBaselineAlignBottom(true);
                imageView.setMinimumHeight(100);
                imageView.setMinimumWidth(100);
                lL.addView(imageView, imgLL);

                //ImageView imageView = (ImageView) findViewById(R.id.img);
                //imageView.setImageBitmap(jasonArray.doInBackground(imgUrl));
                //imageView.add(imageView);

                Log.d("fjgjhgjhggh", title);
                Log.d("fjgjhgjhggh", artist);
                Log.d("fjgjhgjhggh", imgUrl);
                //Log.isLoggable("IIIIIIIIIIIDDDDDDDDDDDDDD", idSong);
                musicNames.add(artist + " - " + title);
                //idSongs.add(idSong);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.d("555", getIntent().getStringExtra("category")+"");
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