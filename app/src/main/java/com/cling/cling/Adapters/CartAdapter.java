package com.cling.cling.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cling.cling.R;

import java.util.ArrayList;

/**
 * Created by Tier on 12.09.14.
 */

public class CartAdapter extends BaseExpandableListAdapter {

    private Activity activity;
    private ArrayList<ArrayList<String>> myGroups;

    public CartAdapter(Activity activity, ArrayList<ArrayList<String>> myGroups) {
        this.activity = activity;
        this.myGroups = myGroups;
    }

    @Override
    public int getGroupCount() {
        return myGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return myGroups.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return myGroups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return myGroups.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        GroupViewHolder holder;
        String sellerName = "Seller" + String.valueOf(groupPosition);

        if (convertView == null) {

            convertView = LayoutInflater.from(activity).inflate(R.layout.listview_item_cart_shop, null, false);
            holder = new GroupViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.cartItemShopImage);
            holder.nameTextView = (TextView) convertView.findViewById(R.id.cartItemShopNameTextView);
            holder.buyButton = (Button) convertView.findViewById(R.id.cartItemShopBuyButton);
            convertView.setTag(holder);

        } else {

            holder = (GroupViewHolder) convertView.getTag();
        }

        holder.nameTextView.setText(sellerName);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        ChildViewHolder holder;
        String productName = "Product" + String.valueOf(childPosition);

        if (convertView == null) {

            convertView = LayoutInflater.from(activity).inflate(R.layout.listview_item_cart_product, null, false);
            holder = new ChildViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.cartItemProductImage);
            holder.nameTextView = (TextView) convertView.findViewById(R.id.cartItemProductNameTextView);
            holder.priceTextView = (TextView) convertView.findViewById(R.id.cartItemProductPriceTextView);
            convertView.setTag(holder);

        } else {

            holder = (ChildViewHolder) convertView.getTag();
        }

        holder.nameTextView.setText(productName);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    private static class GroupViewHolder {

        public ImageView imageView;
        public TextView nameTextView;
        public Button buyButton;
    }

    private static class ChildViewHolder {

        public ImageView imageView;
        public TextView nameTextView;
        public TextView priceTextView;
    }
}
