//Assignment In Class 7
//Group8C_InClass07
//Deepak Rohan Sekar
//Justin Campbell
//Erik Crosby
package com.example.rohan.inclass05;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by rohan on 10/19/15.
 */
public class DatabaseDataManager {
    private Context mContext;
    private DatabaseOpenHelper dbOpenHelper;
    private SQLiteDatabase db;
    private ProductDAO productsDAO;

    public DatabaseDataManager(Context mContext) {
        this.mContext = mContext;
        dbOpenHelper = new DatabaseOpenHelper(this.mContext);
        db = dbOpenHelper.getWritableDatabase();
        productsDAO = new ProductDAO(db);
    }

    public void closeDB(){
        if(db!=null){
            db.close();
        }
    }

    public ProductDAO getProductDAO() {
        return productsDAO;
    }

    public long saveProduct(Product product){
        return this.productsDAO.save(product);
    }

    public Product getProduct(String id){
        return getProductDAO().get(id);
    }

    public List<Product> getAll(){
        return getProductDAO().getAll();
    }

    public boolean delete(Product product) {
        return this.productsDAO.delete(product);
    }
}