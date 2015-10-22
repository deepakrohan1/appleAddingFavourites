//Assignment In Class 7
//Group8C_InClass07
//Deepak Rohan Sekar
//Justin Campbell
//Erik Crosby
package com.example.rohan.inclass05;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PreviewActivity extends AppCompatActivity {
TextView textViewApp;
    ImageView imageViewImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        ActionBar lActionBar = getSupportActionBar();
        lActionBar.setDisplayShowHomeEnabled(true);
        lActionBar.setIcon(R.drawable.appstore);
        lActionBar.setTitle(" Preview Activity");

        textViewApp = (TextView)findViewById(R.id.textViewApp);
        imageViewImage = (ImageView)findViewById(R.id.imageViewImage);
        if(getIntent().getExtras() != null){
            Product p = (Product) getIntent().getExtras().getSerializable(MainActivity.PRODUCT);
            textViewApp.setText(p.getAppName());
            Picasso.with(this).load(p.getImageLarge()).into(imageViewImage);

        }
    }


}
