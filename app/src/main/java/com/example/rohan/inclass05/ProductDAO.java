//Assignment In Class 7
//Group8C_InClass07
//Deepak Rohan Sekar
//Justin Campbell
//Erik Crosby
package com.example.rohan.inclass05;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JustinCamp on 10/18/2015.
 */
public class ProductDAO {
    private SQLiteDatabase db;

    public ProductDAO(SQLiteDatabase db){
        this.db = db;
    }

    public long save(Product product){
        ContentValues values = new ContentValues();
        values.put(ProductTable.COLUMN_APPID, product.getAppID());
        values.put(ProductTable.COLUMN_APPNAME, product.getAppName());
        values.put(ProductTable.COLUMN_RELEASEDATE, product.getDate());
        values.put(ProductTable.COLUMN_PRICE, product.getPrice());
        values.put(ProductTable.COLUMN_CATEGORY, product.getCategory());
        values.put(ProductTable.COLUMN_IMGURL, product.getImageSmall());
        values.put(ProductTable.COLUMN_IMGURLLARGE, product.getImageLarge());
        values.put(ProductTable.COLUMN_FAVORITE, product.getFavorite());

        return db.insert(ProductTable.TABLENAME, null, values);
    }

    public boolean delete(Product product){
        Log.d("demo","Deleting... and id is" + product.get_id());
        return db.delete(ProductTable.TABLENAME, ProductTable.COLUMN_APPID + "=" + product.getAppID(),null) > 0;
    }

    public Product get(String  id){
        Product product = null;

        Cursor c = db.query(true, ProductTable.TABLENAME, new String[] {
                ProductTable.COLUMN_ID,ProductTable.COLUMN_APPID, ProductTable.COLUMN_APPNAME, ProductTable.COLUMN_RELEASEDATE, ProductTable.COLUMN_PRICE, ProductTable.COLUMN_CATEGORY, ProductTable.COLUMN_IMGURL,
                ProductTable.COLUMN_IMGURLLARGE, ProductTable.COLUMN_FAVORITE}
                , ProductTable.COLUMN_APPNAME + "=?", new String[]{id + ""}, null, null, null, null, null);

        if (c != null && c.moveToFirst()){
            product = buildProductFromCursor(c);
            if (!c.isClosed()){
                c.close();
            }
        }

        return product;
    }

    public List<Product> getAll(){
        List<Product> notes = new ArrayList<Product>();

        Cursor c = db.query(ProductTable.TABLENAME, new String[] {
                ProductTable.COLUMN_ID, ProductTable.COLUMN_APPID, ProductTable.COLUMN_APPNAME, ProductTable.COLUMN_RELEASEDATE, ProductTable.COLUMN_PRICE, ProductTable.COLUMN_CATEGORY, ProductTable.COLUMN_IMGURL,
                ProductTable.COLUMN_IMGURLLARGE, ProductTable.COLUMN_FAVORITE}
                , null, null, null, null, null);

        if (c != null && c.moveToFirst()){
            do {
                Product note = buildProductFromCursor(c);
                if (note != null){
                    notes.add(note);
                }
            } while (c.moveToNext());

            if (!c.isClosed()){
                c.close();
            }
        }
        return notes;
    }

    private Product buildProductFromCursor(Cursor c){
        Product product = null;
        if (c != null){
            product = new Product();
            product.set_id(c.getLong(0));
            product.setAppID(c.getString(1));
            product.setAppName(c.getString(2));
            product.setDate(c.getString(3));
            product.setPrice(c.getDouble(4));
            product.setCategory(c.getString(5));
            product.setImageSmall(c.getString(6));
            product.setImageLarge(c.getString(7));
            product.setFavorite(c.getInt(8));
        }
        return product;
    }
}
