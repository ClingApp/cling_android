package com.cling.cling.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.cling.cling.Adapters.GridAdapter;
import com.cling.cling.ClingApp;
import com.cling.cling.Models.Product;
import com.cling.cling.R;
import com.cling.cling.Rest.AppResultReceiver;
import com.cling.cling.Rest.RestConsts;
import com.cling.cling.Rest.ServiceHelper;
import com.cling.cling.UniversalFragmentActivity;
import com.cling.cling.Utilities.PreferencesHelper;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class HomeFragment extends Fragment implements AppResultReceiver.Receiver {

    private GridView gridView;

    private AppResultReceiver mReceiver;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        mReceiver = new AppResultReceiver(new Handler());
        mReceiver.setReceiver(this);

        ServiceHelper serviceHelper = new ServiceHelper(this.getActivity());
        serviceHelper.getFeed(mReceiver);

//        String[] data = {"a", "b", "c", "d", "e", "f"};



//        GridAdapter adapter = new GridAdapter(getActivity(), data);
        gridView = (GridView) rootView.findViewById(R.id.homeGridView);
//        gridView.setAdapter(adapter);
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                //TODO: put product id as parameter
//                Intent intent = new Intent(getActivity(), UniversalFragmentActivity.class);
//                intent.putExtra(UniversalFragmentActivity.ARG_FRAGMENT_ID, UniversalFragmentActivity.AppropriateFragments.PRODUCT.getId());
//                ClingApp.startActivityWithAnimation(getActivity(), intent);
//            }
//        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }


    @Override
    public void onReceiveResult(int resultCode, Bundle data) {
        switch (resultCode) {
            case RestConsts.STATUS_ERROR :
                //TODO: inform user about not avalible network
                break;
            case RestConsts.STATUS_OK :

//                ArrayList<Integer> products_ids = data.getIntegerArrayList("products_ids");

                ArrayList<Product> products = new ArrayList<Product>();

                Product pr1 = new Product("title1", "description1", "price1", 1, 1);
                Product pr2 = new Product("title2", "description2", "price2", 1, 2);

                products.add(pr1);
                products.add(pr2);

                //взять из базы по айдишникам продукты

//                products.add(pr);

                final ArrayList<Product> const_products = products;

                GridAdapter adapter = new GridAdapter(getActivity(), products);
                gridView.setAdapter(adapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        //TODO: put product id as parameter
                        Intent intent = new Intent(getActivity(), UniversalFragmentActivity.class);
                        intent.putExtra(UniversalFragmentActivity.ARG_FRAGMENT_ID, UniversalFragmentActivity.AppropriateFragments.PRODUCT.getId());
                        intent.putExtra(UniversalFragmentActivity.PRODUCT_ID, const_products.get(position)._id);
                        ClingApp.startActivityWithAnimation(getActivity(), intent);
                    }
                });
                break;
        }
    }
}
