package com.cling.cling.Rest;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.util.Log;

//import com.beautyteam.smartkettle.LoginActivity;
//import com.beautyteam.smartkettle.MainActivity;

import com.cling.cling.Fragments.ProductFragment;
import com.cling.cling.MainActivity;
import com.cling.cling.Models.Product;
import com.cling.cling.db.GoodsQueries;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class Processor {
    private String url = "http://188.166.6.241/";
    private JSONObject urlparametres;
    private JSONObject json = null;
    String action = "";
    private String photo;
    private String title;
    private String description;
    private String price;
    private Integer id;
    private Integer seller_id;
    private Context context;

    public Processor(Context _context) {
        context = _context;
    }

    public void request(Intent intent, Network network) throws JSONException, IOException {
        urlparametres = new JSONObject();
        if (intent != null) {
            String method = intent.getExtras().getString(RestConsts.METHOD_NAME.toString());

            GoodsQueries goodsQueries = new GoodsQueries(context);
            goodsQueries.open();

            if (RestConsts.GET.toString().equals(method)) {
                String subject = intent.getStringExtra(RestConsts.SUBJECT.toString());
                String params = intent.getStringExtra(RestConsts.PARAMS.toString());
                String basic_url = url;
                url += "api/";
                url += subject;
                url += "/";
                url += params;
                Log.v("url", url);
                json = new JSONObject(network.urlConnectionGet(url));
                ResultReceiver receiver = intent.getParcelableExtra(ServiceHelper.RECEIVER);
                final Bundle data = new Bundle();
                Log.v("json", json.toString());
                title = json.getJSONObject("good").getString("title");
                description = json.getJSONObject("good").getString("description");
                price = json.getJSONObject("good").getString("price") + " руб.";
                id = json.getJSONObject("good").getInt("id");
                seller_id = json.getJSONObject("good").getInt("seller_id");
                photo = basic_url + json.getJSONObject("good").getString("photo");

                Product product = new Product(id, title, description, price);

                goodsQueries.insertGood(product);

                URL url_photo = null;
                try {
                    url_photo = new URL(photo);
                    Bitmap bmp = BitmapFactory.decodeStream(url_photo.openConnection().getInputStream());
                    data.putParcelable("photo_bm", bmp);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                data.putString("title", title);
                data.putString("photo", photo);
                data.putString("description", description);
                data.putString("price", price);
                data.putInt("id", id);
                data.putInt("seller_id", seller_id);
                receiver.send(RestConsts.STATUS_OK, data);
            } else if (RestConsts.GET_FEED.toString().equals(method)) {
                String basic_url = url;
                url += "api/";
                url += "feed";
                Log.v("url", url);
                json = new JSONObject(network.urlConnectionGet(url));
                ResultReceiver receiver = intent.getParcelableExtra(ServiceHelper.RECEIVER);
                final Bundle data = new Bundle();
                Log.v("json", json.toString());
                JSONObject goods = json.getJSONObject("goods");

                ArrayList<Integer> products_ids = new ArrayList<Integer>();
                products_ids.add(1);


                ArrayList<Product> products = new ArrayList<Product>();
                for (int i = 0; i < goods.length(); i++) {
                    photo = basic_url + goods.getString("photo");
                    URL url_photo = null;
                    try {
                        url_photo = new URL(photo);
                        Bitmap bmp = BitmapFactory.decodeStream(url_photo.openConnection().getInputStream());
                        Log.v("id", goods.getString("title"));

                        Product pr = new Product(goods.getInt("id"), goods.getString("title"), goods.getString("description"),
                                goods.getString("price") + " руб.");

                        products.add(pr);

                        goodsQueries.insertGood(pr);
                        products_ids.add(pr.id);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

//                data.putParcelableArrayList("products", products);
                data.putIntegerArrayList("products_ids", products_ids);
                receiver.send(RestConsts.STATUS_OK, data);
            }




        }
    }

}
