package com.example.android.astam.box_music2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import static android.support.v4.app.ActivityCompat.startActivity;
import static android.support.v4.content.ContextCompat.getDrawable;

public class Facebook extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);

        WebView webView = (WebView) this.findViewById(R.id.webViewFacebook);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSaveFormData(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.setWebViewClient(new FWebViewClient());
        webView.loadUrl("https://www.facebook.com/dialog/oauth?client_id=673226222812378&redirect_uri=https://www.facebook.com/profile.php?id=100009683417595/fblogin&response_type=token");

    }

    /*class FWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_facebook, menu);
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

class FWebViewClient extends WebViewClient {
    public FWebViewClient() {
    }
    @Override
    public void onPageFinished(WebView view, String url) {
        Log.i("FWebViewClient onPageFinished", url);
        String str = url;
        String token = str.substring(str.indexOf('=') + 1, str.indexOf('&'));
        String id = str.substring(str.lastIndexOf("=") + 1);


        Log.i("Toooooooooooooooooooken", token);
        Log.i("IIIIIIIIDDDDDDDDDDDDDDD", id);

        /*if (url.contains("facebook.com/dialog/oauth#")) {
            if (url.contains("error")) {
                // Error
            } else {
                String ahrore = url.substring(url.indexOf("#") + 1);
                Log.i("FWebViewClient onPageFinished", ahrore);
            }
        }*/
    }
}
