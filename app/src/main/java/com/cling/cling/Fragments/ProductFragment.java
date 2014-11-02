package com.cling.cling.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cling.cling.Adapters.GalleryAdapter;
import com.cling.cling.R;

public class ProductFragment extends Fragment {

    private ViewPager galleryPagerView;

    public static ProductFragment newInstance() {

        return new ProductFragment();
    }

    public ProductFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_product, container, false);

        String[] data = {"http://cling.com/image.png","http://cling.com/image.png","http://cling.com/image.png"};

        GalleryAdapter galleryAdapter = new GalleryAdapter(getActivity(), data);
        galleryPagerView = (ViewPager) rootView.findViewById(R.id.productViewPager);
        galleryPagerView.setAdapter(galleryAdapter);
        galleryAdapter.notifyDataSetChanged();

        return rootView;
    }
}
