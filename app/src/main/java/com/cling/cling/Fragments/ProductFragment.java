package com.cling.cling.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.cling.cling.ClingApp;
import com.cling.cling.R;
import com.cling.cling.UniversalFragmentActivity;

public class ProductFragment extends Fragment {

    private ImageView productImageView;
    private Button buyButton;
    private Button deleteButton;


    public static ProductFragment newInstance() {

        return new ProductFragment();
    }

    public ProductFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_product, container, false);

        productImageView = (ImageView) rootView.findViewById(R.id.productImageView);
        buyButton = (Button) rootView.findViewById(R.id.productBuyButton);
        deleteButton = (Button) rootView.findViewById(R.id.productDeleteButton);

        productImageView.setImageResource(R.drawable.dump_product_1);


        if (!true) {
            deleteButton.setVisibility(View.GONE);
            buyButton.setVisibility(View.VISIBLE);
        } else {
            buyButton.setVisibility(View.GONE);
            deleteButton.setVisibility(View.VISIBLE);
        }

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UniversalFragmentActivity.class);
                intent.putExtra(UniversalFragmentActivity.ARG_FRAGMENT_ID, UniversalFragmentActivity.AppropriateFragments.USER.getId());
                ClingApp.startActivityWithAnimation(getActivity(), intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UniversalFragmentActivity.class);
                intent.putExtra(UniversalFragmentActivity.ARG_FRAGMENT_ID, UniversalFragmentActivity.AppropriateFragments.USER.getId());
                ClingApp.startActivityWithAnimation(getActivity(), intent);
            }
        });

        return rootView;
    }
}
