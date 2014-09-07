package com.cling.cling.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cling.cling.R;

/**
 * Created by Tier on 06.09.14.
 */

public class GridAdapter extends BaseAdapter {

    private Activity activity;
    private String[] data;

    public GridAdapter(Activity activity, String[] data) {
        this.activity = activity;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ProductViewHolder holder;

        if (view == null) {

            view = LayoutInflater.from(activity).inflate(R.layout.grid_item_product, null, false);
            holder = new ProductViewHolder();
            holder.imageView = (ImageView) view.findViewById(R.id.gridItemProductImage);
            holder.nameTextView = (TextView) view.findViewById(R.id.gridItemProductName);
            view.setTag(holder);

        } else {

            holder = (ProductViewHolder) view.getTag();
        }

        holder.nameTextView.setText(data[position]);

        return view;
    }

    private static class ProductViewHolder {

        public ImageView imageView;
        public TextView nameTextView;

    }
}
