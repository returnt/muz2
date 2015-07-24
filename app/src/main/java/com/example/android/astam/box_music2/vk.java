package com.example.android.astam.box_music2;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.StringTokenizer;

import static android.support.v4.app.ActivityCompat.startActivity;


public class vk extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vk);

        WebView wv = (WebView) this.findViewById(R.id.webView1);
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wv.setHorizontalScrollBarEnabled(false);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wv.setWebViewClient(new VkWebViewClient());
        wv.loadUrl("https://oauth.vk.com/authorize?client_id=5002326&scope=offline,audio&redirect_uri=https://oauth.vk.com/blank.html&display=mobile&v=5.5&response_type=token&revoke=1");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_vk, menu);
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

 class VkWebViewClient extends WebViewClient {
    public VkWebViewClient() {
    }

     @Override
    public void onPageFinished(WebView view, String url) {
        Log.i("VkWebViewClient onPageFinished", url);

                //тут получаю токен
        String token = url.substring(url.indexOf('=') + 1, url.indexOf('&'));
        Log.d("url", token);
                //заканчивается получение токена

        if (url.contains("oauth.vk.com/blank.html#")) {
            if (url.contains("error")) {
                // Error
            } else {
                String ahrore = url.substring(url.indexOf("#") + 1);
            }
        }

     }
 }


