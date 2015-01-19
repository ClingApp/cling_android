package com.cling.cling.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.cling.cling.Adapters.GalleryAdapter;
import com.cling.cling.R;

public class ProductFragment extends Fragment {

    private ViewPager galleryPagerView;
    private Button buyButton;


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

        buyButton = (Button) rootView.findViewById(R.id.productBuyButton);

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.setCustomAnimations(android.R.animator.fade_in,
                        android.R.animator.fade_out);
                ft.show(ProfileFragment.newInstance());

                ft.commit();
//                ProfileFragment.newInstance();
            }
        });

        return rootView;
    }
}
