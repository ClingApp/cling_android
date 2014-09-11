package com.cling.cling.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cling.cling.R;

/**
 * Created by Tier on 09.09.14.
 */

public class ActionsAdapter extends BaseAdapter {

    private Activity activity;
    private String[] actions;

    public ActionsAdapter(Activity activity, String[] actions) {
        this.activity = activity;
        this.actions = actions;
    }

    @Override
    public int getCount() {
        return actions.length;
    }

    @Override
    public Object getItem(int position) {
        return actions[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ActionViewHolder holder;

        if (view == null) {

            view = LayoutInflater.from(activity).inflate(R.layout.listview_item_user_action, null, false);
            holder = new ActionViewHolder();
            holder.titleTextView = (TextView) view.findViewById(R.id.actionItemTitleTextView);
            view.setTag(holder);

        } else {

            holder = (ActionViewHolder) view.getTag();
        }

        holder.titleTextView.setText(actions[position]);

        return view;
    }

    private static class ActionViewHolder {
        public TextView titleTextView;
    }
}
