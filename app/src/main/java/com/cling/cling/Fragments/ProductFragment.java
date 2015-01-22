package com.cling.cling.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.ResultReceiver;
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
import android.widget.TextView;
import android.content.BroadcastReceiver;

import com.cling.cling.ClingApp;
import com.cling.cling.R;
import com.cling.cling.Rest.AppResultReceiver;
import com.cling.cling.Rest.RestConsts;
import com.cling.cling.Rest.ServiceHelper;
import com.cling.cling.UniversalFragmentActivity;

import android.os.Handler;

public class ProductFragment extends Fragment implements AppResultReceiver.Receiver {

    private ImageView productImageView;
    private Button buyButton;
    private Button deleteButton;
    private TextView productNameTextView;
    private Integer productId;

    private AppResultReceiver mReceiver;

    private static final String PRODUCT_ID = "product_id";

    public static ProductFragment newInstance(Integer id) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putInt(PRODUCT_ID, id);
        fragment.setArguments(args);

        return fragment;
    }

    public ProductFragment() {

    }

    private void getFragmetArguments() {

        if (!getArguments().isEmpty()) {
            productId = getArguments().getInt(PRODUCT_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_product, container, false);
        getFragmetArguments();
        productImageView = (ImageView) rootView.findViewById(R.id.productImageView);
        buyButton = (Button) rootView.findViewById(R.id.productBuyButton);
        deleteButton = (Button) rootView.findViewById(R.id.productDeleteButton);
        productNameTextView = (TextView) rootView.findViewById(R.id.productNameTextView);

        mReceiver = new AppResultReceiver(new Handler());
        mReceiver.setReceiver(this);

        ServiceHelper serviceHelper = new ServiceHelper(this.getActivity());
        Log.v("prId", String.valueOf(productId));
        serviceHelper.getGood(productId, mReceiver);

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

    @Override
    public void onResume() {
        super.onResume();
        mReceiver = new AppResultReceiver(new Handler());
        mReceiver.setReceiver(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mReceiver.setReceiver(null);
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle data) {
        switch (resultCode) {
            case RestConsts.STATUS_ERROR :
                //TODO: inform user about not avalible network
                break;
            case RestConsts.STATUS_OK :
                String title = data.getString("title");
                productNameTextView.setText(title);
                break;
        }
    }

}
