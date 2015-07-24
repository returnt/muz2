/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libraryjava;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Professor
 */
public class parseJSON {

    private String JsonObject;
    private JSONArray JsonArray;

    private String mWeatherIcon;

    public parseJSON() {
    }

    public parseJSON(String URL, String key, String value) {
        try {
            StrictMode();

            JsonObject = getJsonObject(URL).getJSONObject(key).getString(value);
        } catch (JSONException ex) {
            Log.d("parseJSONException", ex.toString());
        }
    }

    public parseJSON(String URL, String array) {
        try {
            StrictMode();

            JsonArray = getJsonObject(URL).getJSONArray(array);
        } catch (JSONException ex) {
            Log.d("parseJSONException", ex.toString());
        }
    }

    public parseJSON(String URL, String array, int i, String value) {
        try {
            StrictMode();

            JsonObject = getJsonObject(URL).getJSONArray(array).getJSONObject(i).getString(value);
        } catch (JSONException ex) {
            Log.d("parseJSONException", ex.toString());
        }
    }

    private JSONObject getJsonObject(String URL){

        try{

            DefaultHttpClient defaultClient = new DefaultHttpClient();

            HttpResponse httpResponse = defaultClient.execute(new HttpGet(URL));

            BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"));

            JSONObject jsonObject = new JSONObject(reader.readLine());

            return jsonObject;

        }catch(IOException ex){
            return null;
        } catch (IllegalStateException ex) {
            return null;
        } catch (JSONException ex) {
            return null;
        }
    }

    public Bitmap doInBackground(String urll) {
        try {
            URL url = new URL(urll);

            HttpGet httpRequest = new HttpGet(url.toURI());
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response = httpclient.execute(httpRequest);

            HttpEntity entity = response.getEntity();
            BufferedHttpEntity bufferedEntity = new BufferedHttpEntity(entity);
            InputStream input = bufferedEntity.getContent();

            return BitmapFactory.decodeStream(input);
        } catch (Exception ex) {
            return null;
        }
    }

    public String getJsonObject() {
        return JsonObject ;
    }

    public JSONArray getJsonArray() {
        return JsonArray ;
    }



    private void StrictMode(){

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

}
