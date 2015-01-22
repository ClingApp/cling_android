package com.cling.cling.Models;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Tier on 22.09.14.
 */

public class Product {

    public String _title;
    public String _description;
    public String _price;
    public Integer _seller_id;
    public Bitmap _image;
    public Integer _id;

    public Product( String title, String description, String price, Integer seller_id, Integer id) {
        _title = title;
        _description = description;
        _price = price;
//        _image = image;
        _seller_id = seller_id;
        _id = id;
    }
}
