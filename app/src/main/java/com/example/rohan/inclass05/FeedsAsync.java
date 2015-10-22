//Assignment In Class 7
//Group8C_InClass07
//Deepak Rohan Sekar
//Justin Campbell
//Erik Crosby
package com.example.rohan.inclass05;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by rohan on 10/5/15.
 */
public class FeedsAsync extends AsyncTask<String, Void, ArrayList<Product>> {

    IGetFeeds currentActivity;
    ProgressDialog JSONParser;


    public FeedsAsync(IGetFeeds activity) {
        this.currentActivity = activity;

    }

    @Override
    protected ArrayList<Product> doInBackground(String... params) {
        BufferedReader reader;
        try {
            URL url = new URL(params[0]);
            Log.d("Demo", params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                Log.d("demo", "test");
                return JSONParsing.JSONFeedParser.AppleFeeds(sb.toString());
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Product> products) {
        super.onPostExecute(products);
        JSONParser.dismiss();
        if(products.size() != 0) {
            for (Product p : products) {
                Log.d("Demo", p.toString());
            }
            currentActivity.checkPreferences(products);
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        JSONParser = new ProgressDialog((Context) currentActivity);
        JSONParser.setMessage("Loading");
        JSONParser.show();
        JSONParser.setTitle("Feeds from Apple");
        JSONParser.setCancelable(false);
    }
    public static interface IGetFeeds{
        public void checkPreferences(ArrayList<Product> products);
    }
}