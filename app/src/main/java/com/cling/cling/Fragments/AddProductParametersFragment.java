package com.cling.cling.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cling.cling.R;

public class AddProductParametersFragment extends Fragment {

    public AddProductFragment parentFragment;

    public static AddProductParametersFragment newInstance() {

        return new AddProductParametersFragment();
    }

    public AddProductParametersFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_add_product_parameters, container, false);

        Button nextButton = (Button) rootView.findViewById(R.id.addProductFirstNextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parentFragment.selectPage(2);
            }
        });

        return rootView;
    }
}
