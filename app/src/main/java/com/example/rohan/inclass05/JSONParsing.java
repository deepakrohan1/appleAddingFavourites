//Assignment In Class 7
//Group8C_InClass07
//Deepak Rohan Sekar
//Justin Campbell
//Erik Crosby
package com.example.rohan.inclass05;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by rohan on 10/5/15.
 */
public class JSONParsing {
    static  ArrayList<Product> productsList;
    static Product product;
    public static class JSONFeedParser{
        static ArrayList<Product> AppleFeeds(String in) throws JSONException {
            productsList = new ArrayList<>();
            JSONObject root = new JSONObject(in).getJSONObject("feed");
            JSONArray entryArray = root.getJSONArray("entry");

            for (int i = 0; i < entryArray.length(); i++) {
                Product product = new Product();
//                Log.d("Demo", "Inside Entry Array");
                //Title of App
                String label = entryArray.getJSONObject(i).getJSONObject("im:name").getString("label");
                product.setAppName(label);

                String id = entryArray.getJSONObject(i).getJSONObject("id").getJSONObject("attributes").getString("im:id");
                product.setAppID(id);

                //Price of the App
                Double imPrice = entryArray.getJSONObject(i).getJSONObject("im:price").getJSONObject("attributes").getDouble("amount");
                product.setPrice(imPrice);

                //Currency
                String currency = entryArray.getJSONObject(i).getJSONObject("im:price").getJSONObject("attributes").getString("currency");
                product.setCurrency(currency);

                //Artist
                String artist = entryArray.getJSONObject(i).getJSONObject("im:artist").getString("label");
                product.setDevName(artist);

                //Category
                String category = entryArray.getJSONObject(i).getJSONObject("category").getJSONObject("attributes").getString("label");
                product.setCategory(category);

                //Release Date
                String releaseDate = entryArray.getJSONObject(i).getJSONObject("im:releaseDate").getJSONObject("attributes").getString("label");
                product.setDate(releaseDate);


                JSONObject link = entryArray.getJSONObject(i);
                //Parsing the Preview Links


                //Image Links
                String imageSmall = "";
                String imageLarge = "";
                JSONArray images = link.getJSONArray("im:image");
                for (int k = 0; k < 1; k++) {
                    imageSmall = images.getJSONObject(0).getString("label");
                    imageLarge = images.getJSONObject(2).getString("label");
                    product.setImageSmall(imageSmall);
                    product.setImageLarge(imageLarge);
                }
                productsList.add(product);

            }
            return productsList;
        }
    }




}
