package com.cling.cling.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cling.cling.R;

public class AddProductPhotoFragment extends Fragment {

    public static AddProductPhotoFragment newInstance() {

        return new AddProductPhotoFragment();
    }

    public AddProductPhotoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_add_product_photo, container, false);

        return rootView;
    }
}
