//Assignment In Class 7
//Group8C_InClass07
//Deepak Rohan Sekar
//Justin Campbell
//Erik Crosby
package com.example.rohan.inclass05;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rohan on 10/5/15.
 */
public class ProductAdapter extends ArrayAdapter<Product> {
    Context mContext;
    int mResource;
    List<Product> mObjects;
    List<Product> mSaved;
    DatabaseDataManager dbm;


    public ProductAdapter(Context context, int resource, List<Product> objects,List<Product> saved) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
        this.mObjects = objects;
        this.mSaved = saved;
        dbm = new DatabaseDataManager(context);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource, parent, false);
        }
        Product p = mObjects.get(position);

        ImageView imageViewPreview = (ImageView) convertView.findViewById(R.id.imageView);
        Picasso.with(mContext).load(p.getImageSmall()).resize(100,100).into(imageViewPreview);

        TextView textViewAppName = (TextView) convertView.findViewById(R.id.appName);
        textViewAppName.setText(p.getAppName());

        TextView textViewDevName = (TextView) convertView.findViewById(R.id.developerName);
        textViewDevName.setText(p.getDevName());

        TextView textViewReleaseDate = (TextView) convertView.findViewById(R.id.releaseDate);
        textViewReleaseDate.setText(p.getDate());

        TextView textViewPrice = (TextView) convertView.findViewById(R.id.price);
        textViewPrice.setText(p.getPrice()+" "+p.getCurrency());

        TextView textViewCategory = (TextView) convertView.findViewById(R.id.category);
        textViewCategory.setText(p.getCategory());

        ImageView star = (ImageView) convertView.findViewById(R.id.starImage);
        star.setImageDrawable(mContext.getResources().getDrawable(R.drawable.star_dark));
//        for(Product l : mSaved){
//            if(l.getAppID().equals(mObjects.get(position).getAppID())){
//                Log.d("ap","inside if "+l.toString());
//                star.setImageDrawable(mContext.getResources().getDrawable(R.drawable.star_filled));
//            }else{
//                star.setImageDrawable(mContext.getResources().getDrawable(R.drawable.star_dark));
//            }
//        }

        Product r = dbm.getProduct(p.getAppName());
        if(r == null){
            star.setImageDrawable(mContext.getResources().getDrawable(R.drawable.star_dark));
        }else{
            star.setImageDrawable(mContext.getResources().getDrawable(R.drawable.star_filled));
        }




//        if (mObjects.get(position).getFavorite() == 0) {
//            ImageView star = (ImageView) convertView.findViewById(R.id.starImage);
//            star.setImageDrawable(mContext.getResources().getDrawable(R.drawable.star_dark));
//        } else {
//            ImageView star = (ImageView) convertView.findViewById(R.id.starImage);
//            star.setImageDrawable(mContext.getResources().getDrawable(R.drawable.star_filled));
//        }

        return convertView;
    }
}
