//Assignment In Class 7
//Group8C_InClass07
//Deepak Rohan Sekar
//Justin Campbell
//Erik Crosby
package com.example.rohan.inclass05;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by JustinCamp on 10/18/2015.
 */
public class ProductTable {

    static final String TABLENAME = "products";
    static final String COLUMN_ID = "_id";
    static final String COLUMN_APPID = "appId";
    static final String COLUMN_APPNAME = "appname";
    static final String COLUMN_RELEASEDATE = "release_date";
    static final String COLUMN_PRICE = "price";
    static final String COLUMN_CATEGORY = "category";
    static final String COLUMN_IMGURL = "img_url";
    static final String COLUMN_IMGURLLARGE = "img_url_large";
    static final String COLUMN_FAVORITE = "favorite";

    static public void onCreate(SQLiteDatabase db){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TABLENAME + " (");
        sb.append(COLUMN_ID + " integer primary key autoincrement, ");
        sb.append(COLUMN_APPID + " text not null, ");
        sb.append(COLUMN_APPNAME + " text not null, ");
        sb.append(COLUMN_RELEASEDATE + " text not null, ");
        sb.append(COLUMN_PRICE + " text not null, ");
        sb.append(COLUMN_CATEGORY + " text not null, ");
        sb.append(COLUMN_IMGURL + " text not null, ");
        sb.append(COLUMN_IMGURLLARGE + " text not null, ");
        sb.append(COLUMN_FAVORITE + " text not null);");

        try {
            db.execSQL(sb.toString());
        } catch(SQLException ex) {
            ex.printStackTrace();
        }

    }

    static public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
        ProductTable.onCreate(db);
    }


}
