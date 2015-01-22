package com.cling.cling.Adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cling.cling.Models.Product;
import com.cling.cling.R;

import java.util.ArrayList;

/**
 * Created by Tier on 06.09.14.
 */

public class GridAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<Product>  data;

    public GridAdapter(Activity activity, ArrayList<Product> data) {
        this.activity = activity;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ProductViewHolder holder;

        if (view == null) {

            view = LayoutInflater.from(activity).inflate(R.layout.grid_item_product, parent, false);
            holder = new ProductViewHolder();
            holder.imageView = (ImageView) view.findViewById(R.id.gridItemProductImage);
            holder.nameTextView = (TextView) view.findViewById(R.id.gridItemProductName);
            holder.descTextView = (TextView) view.findViewById(R.id.gridItemProductDescription);
            holder.productPriceTextView = (TextView) view.findViewById(R.id.gridItemProductPriceTextView);
            view.setTag(holder);

        } else {

            holder = (ProductViewHolder) view.getTag();
        }

        holder.nameTextView.setText(data.get(position)._title);
        holder.descTextView.setText(data.get(position)._description);
        holder.productPriceTextView.setText(data.get(position)._price);
//        holder.imageView.setImageBitmap(data.get(position)._image);

        return view;
    }

    private static class ProductViewHolder {

        public ImageView imageView;
        public TextView nameTextView;
        public TextView descTextView;
        public TextView productPriceTextView;
    }
}
