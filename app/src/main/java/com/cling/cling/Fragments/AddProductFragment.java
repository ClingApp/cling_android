package com.cling.cling.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cling.cling.Adapters.AddProductAdapter;
import com.cling.cling.R;
import com.cling.cling.SimplePageIndicator;

public class AddProductFragment extends Fragment {

    private ViewPager viewPager;
    private SimplePageIndicator pageIndicator;

    public static AddProductFragment newInstance() {

        return new AddProductFragment();
    }

    public AddProductFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_add_product, container, false);

        AddProductAdapter adapter = new AddProductAdapter(getFragmentManager(), this);

        viewPager = (ViewPager) rootView.findViewById(R.id.addProductViewPager);
        pageIndicator = (SimplePageIndicator) rootView.findViewById(R.id.addProductPageIndicator);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                pageIndicator.notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        pageIndicator.setViewPager(viewPager);
        pageIndicator.notifyDataSetChanged();

        return rootView;
    }

    public void selectPage(int position) {

        if (isAdded()) {
            viewPager.setCurrentItem(position, true);
        }
    }
}
