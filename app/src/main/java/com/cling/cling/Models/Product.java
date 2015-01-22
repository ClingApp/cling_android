package com.cling.cling.Models;

import android.graphics.Bitmap;

import java.util.Date;
import java.util.List;

/**
 * Created by Tier on 22.09.14.
 */

public class Product {

    public int id;
    public String title;
    public String description;
    public String price;
    public User seller_id;
    public Date date;
    public String photo;
    public Bitmap image;
}
