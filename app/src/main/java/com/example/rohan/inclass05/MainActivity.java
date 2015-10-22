//Assignment In Class 7
//Group8C_InClass07
//Deepak Rohan Sekar
//Justin Campbell
//Erik Crosby
package com.example.rohan.inclass05;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FeedsAsync.IGetFeeds {

//    ImageView imageViewPreview;
//    TextView textViewAppName;
//    TextView textViewDevName;
//    TextView textViewReleaseDate;
//    TextView textViewPrice;
//    TextView textViewCategory;
    public static String PRODUCT = "product";
    ListView listView;
    DatabaseDataManager dm;
    String type,inScreen = "all";
    List<Product> saved;
    List<Product> allProducts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dm = new DatabaseDataManager(this);

        type = "L";
        ActionBar lActionBar = getSupportActionBar();
        lActionBar.setDisplayShowHomeEnabled(true);
        lActionBar.setIcon(R.drawable.appstore);
        lActionBar.setTitle(" Apps Activity");

        listView = (ListView) findViewById(R.id.listView);
        new FeedsAsync(this).execute("http://itunes.apple.com/us/rss/topgrossingapplications/limit=25/json");
//        checkPreferences();




        saved = dm.getAll();
//        Log.d("ap", "The products are : " + saved.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.allApplications:
                inScreen = "all";
                switchViews(allProducts);
                Log.d("demo", "search clicked");

                return true;
            case R.id.favouriteApplications:
                inScreen = "fav";
                switchViews(saved);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void checkPreferences(final ArrayList<Product> products) {
        ProductAdapter adapter = new ProductAdapter(this, R.layout.product_view, products,saved);
        Log.d("ap","inside interface");
        allProducts = (List<Product>) products.clone();
        listView.setAdapter(adapter);
//        populateData(products);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product product = null;
                if (inScreen == "all"){
                product = allProducts.get(position);
                }else if(inScreen == "fav"){
                    product=saved.get(position);
                }

                Intent i = new Intent(MainActivity.this, PreviewActivity.class);
                i.putExtra(PRODUCT, product);
                startActivity(i);

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View v,
                                           int index, long arg3) {
                Product p =null;
                Product product = (Product)allProducts.get(index);
                //final Product secondP = dm.getProduct(index);
                 p = dm.getProduct(product.getAppName());
//                Log.d("ap",p.toString());
                if (p==null) {
                    product.setFavorite(1);
                    dm.saveProduct(product);
                    Toast.makeText(MainActivity.this, product.getAppName().toString() + " has been added to favorites", Toast.LENGTH_SHORT).show();

                    ImageView star = (ImageView) v.findViewById(R.id.starImage);
                    star.setImageDrawable(getResources().getDrawable(R.drawable.star_filled));
                } else if(p!=null){
                    product.setFavorite(0);
                    dm.delete(product);
                    Toast.makeText(MainActivity.this, product.getAppName().toString() + " has been deleted from favorites", Toast.LENGTH_SHORT).show();
                    Log.d("demo", "Product id in long click is " + product.get_id());

                    ImageView star = (ImageView) v.findViewById(R.id.starImage);
                    star.setImageDrawable(getResources().getDrawable(R.drawable.star_dark));
                }
                Log.d("demo", "The index is " + index);

                saved = dm.getAll();


                return true;
            }
        });
    }



    public void switchViews(List<Product> products){
        ProductAdapter adapter = new ProductAdapter(this, R.layout.product_view, products,saved);
        listView.setAdapter(adapter);
    }



}