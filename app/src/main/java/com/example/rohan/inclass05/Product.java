//Assignment In Class 7
//Group8C_InClass07
//Deepak Rohan Sekar
//Justin Campbell
//Erik Crosby
package com.example.rohan.inclass05;

import java.io.Serializable;

/**
 * Created by rohan on 10/5/15.
 */
public class Product implements Serializable{

    private long _id;



    private String appName;
    private String devName;
    private String category;
    private String currency;
    private String imageLarge;
    private String imageSmall;
    private String date;
    private String appID;
    private double price;
    private int favorite;

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }
    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getImageLarge() {
        return imageLarge;
    }

    public void setImageLarge(String imageLarge) {
        this.imageLarge = imageLarge;
    }

    public String getImageSmall() {
        return imageSmall;
    }

    public void setImageSmall(String imageSmall) {
        this.imageSmall = imageSmall;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "_id=" + _id +
                ", appName='" + appName + '\'' +
                ", devName='" + devName + '\'' +
                ", category='" + category + '\'' +
                ", currency='" + currency + '\'' +
                ", imageLarge='" + imageLarge + '\'' +
                ", imageSmall='" + imageSmall + '\'' +
                ", date='" + date + '\'' +
                ", appID='" + appID + '\'' +
                ", price=" + price +
                ", favorite=" + favorite +
                '}';
    }

}
