package com.cling.cling.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
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
import android.widget.Toast;

import com.cling.cling.Adapters.GridAdapter;
import com.cling.cling.ClingApp;
import com.cling.cling.Models.Product;
import com.cling.cling.R;
import com.cling.cling.Rest.AppResultReceiver;
import com.cling.cling.Rest.RestConsts;
import com.cling.cling.Rest.ServiceHelper;
import com.cling.cling.UniversalFragmentActivity;
import com.cling.cling.Utilities.PreferencesHelper;
import com.cling.cling.db.DbHelper;
import com.cling.cling.db.GoodsQueries;

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



        gridView = (GridView) rootView.findViewById(R.id.homeGridView);

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

                Toast toast = Toast.makeText(getActivity(), "Please, check your network connection", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case RestConsts.STATUS_OK :

                ArrayList<Integer> products_ids = data.getIntegerArrayList("products_ids");

                ArrayList<Product> products = new ArrayList<Product>();

                GoodsQueries goodsQueries = new GoodsQueries(getActivity().getApplicationContext());
                goodsQueries.open();

                for (int i=0; i < products_ids.size(); i++) {
                    Cursor cursor = goodsQueries.getGoodById(products_ids.get(i));
                    if (cursor != null) {
                        cursor.moveToFirst();
                        do {
                            int id = cursor.getInt(cursor.getColumnIndex(DbHelper.TABLE_COLUMN_ID));
                            String title = cursor.getString(cursor.getColumnIndex(DbHelper.TABLE_COLUMN_TITLE));
                            String description = cursor.getString(cursor.getColumnIndex(DbHelper.TABLE_COLUMN_DESCRIPTION));
                            String price = cursor.getString(cursor.getColumnIndex(DbHelper.TABLE_COLUMN_PRICE));

                            Product pr = new Product(id, title, description, price);
                            products.add(pr);

                        } while (cursor.moveToNext());
                    }
                }





                final ArrayList<Product> const_products = products;

                GridAdapter adapter = new GridAdapter(getActivity(), products);
                gridView.setAdapter(adapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent = new Intent(getActivity(), UniversalFragmentActivity.class);
                        intent.putExtra(UniversalFragmentActivity.ARG_FRAGMENT_ID, UniversalFragmentActivity.AppropriateFragments.PRODUCT.getId());
                        intent.putExtra(UniversalFragmentActivity.PRODUCT_ID, const_products.get(position).id);
                        ClingApp.startActivityWithAnimation(getActivity(), intent);
                    }
                });
                break;
        }
    }
}
