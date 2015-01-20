package com.cling.cling.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cling.cling.ClingApp;
import com.cling.cling.MainActivity;
import com.cling.cling.R;
import com.cling.cling.UniversalFragmentActivity;

public class AddProductParametersFragment extends Fragment {

    private Button addButton;

    public static AddProductParametersFragment newInstance() {

        return new AddProductParametersFragment();
    }

    public AddProductParametersFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_add_product_parameters, container, false);

        addButton = (Button) rootView.findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UniversalFragmentActivity.class);
                intent.putExtra(UniversalFragmentActivity.ARG_FRAGMENT_ID, UniversalFragmentActivity.AppropriateFragments.PRODUCT.getId());
                ClingApp.startActivityWithAnimation(getActivity(), intent);
            }
        });

        return rootView;
    }
}
