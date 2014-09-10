package com.cling.cling.Adapters;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cling.cling.R;

/**
 * Created by Tier on 09.09.14.
 */

public class GalleryAdapter extends PagerAdapter {

    private Activity activity;
    private String[] data;

    public GalleryAdapter(Activity activity, String[] data) {
        this.activity = activity;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object object) {
        collection.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup collection, final int position) {

        View view = LayoutInflater.from(activity).inflate(R.layout.view_gallery_item, null, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.galleryItemImageView);
        imageView.setImageResource(R.drawable.ic_logo);
        collection.addView(view, 0);

        return view;
    }
}
