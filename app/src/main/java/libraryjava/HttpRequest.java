package libraryjava;

import android.os.StrictMode;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Professor on 17.07.2015.
 */
public class HttpRequest {

    private HttpClient httpClient;
    private String respons;
    private HttpEntity makeResEntityPost = null;
    private HttpEntity makeResEntityGet = null;
    private JSONObject getJson = null;

    public HttpRequest(){

        httpClient = new DefaultHttpClient();
    }

    /**
     * GET
     * @param url
     * @return HttpEntity
     */
    private HttpEntity makeGetRequest(String url) {
        strictMode();

        try {

            HttpGet get = new HttpGet(url);
            HttpResponse responseGet = httpClient.execute(get);

            /**
             * response POST
             */
            makeResEntityGet = responseGet.getEntity();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return makeResEntityGet;
    }

    /**
     *
     * @param url
     * @return JSONArray
     */
    public JSONObject makeGetRequestGetJsonArray(String url){

        makeResEntityGet = makeGetRequest(url);
        if (makeResEntityGet != null) {
            try {
                    //Log.i("GET RESPONSE", EntityUtils.toString(makeGetRequest(url)));
                    respons = EntityUtils.toString(makeGetRequest(url));
                    getJson = new JSONObject(respons);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
            }
        }
        return getJson;
    }

    /**
     *
     * @param url
     * @return String
     */
    public String makeGetRequestGetObject(String url){

        makeResEntityGet = makeGetRequest(url);
        if (makeResEntityGet != null) {
            try {
                //Log.i("GET RESPONSE", EntityUtils.toString(makeGetRequest(url)));
                respons = EntityUtils.toString(makeGetRequest(url));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return respons;
    }

    /**
     * POST
     * @param url
     * @param array
     * @return HttpEntity
     */
    private HttpEntity makePostRequest(String url, Map<String, String> array){
        strictMode();

        try {

            List<NameValuePair> params = new ArrayList<NameValuePair>();

            Set<Map.Entry<String, String>> set = array.entrySet();
            for (Map.Entry<String, String> map : set) {
                params.add(new BasicNameValuePair(map.getKey(), map.getValue()));
            }

            HttpPost post = new HttpPost(url);

            post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

            HttpResponse responsePOST = httpClient.execute(post);

            /**
             * response POST
             */
            makeResEntityPost = responsePOST.getEntity();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return makeResEntityPost;
    }

    /**
     *
     * @param url
     * @param array
     * @return JSONArray
     */
    public JSONObject makePostRequestGetJsonArray(String url, Map<String, String> array){

        makeResEntityPost = makePostRequest(url, array);
        if (makeResEntityPost != null) {
            try {
                //Log.i("POST RESPONSE", EntityUtils.toString(makeresEntityGet));
                respons = EntityUtils.toString(makeResEntityPost);
                Log.d("222", respons);
                if(respons != null) {
                   getJson = new JSONObject(respons);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return getJson;
    }

    /**
     *
     * @param url
     * @param array
     * @return String
     */
    public String makePostRequestGetObject(String url, Map<String, String> array){

        makeResEntityPost = makePostRequest(url, array);
        if (makeResEntityPost != null) {
            try {
                //Log.i("GET RESPONSE", EntityUtils.toString(makePostRequest(url, array)));
                respons = EntityUtils.toString(makeResEntityPost);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return respons;
    }

    /**
     * POST pull file
     */
    public void makePostwithFile(){

        /*File file = new File("path/to/your/file.txt");

        try {
            HttpClient client = new DefaultHttpClient();
            String postURL = "http://someposturl.com";
            HttpPost post = new HttpPost(postURL);
            FileBody bin = new FileBody(file);
            MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
            reqEntity.addPart("myFile", bin);
            post.setEntity(reqEntity);
            HttpResponse response = client.execute(post);
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                Log.i("RESPONSE",EntityUtils.toString(resEntity));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    /**
     * control version
     */
    private void strictMode(){

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }
}
