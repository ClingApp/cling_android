package com.cling.cling.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cling.cling.R;

public class RegistrationFragment extends Fragment {

    public static RegistrationFragment newInstance() {

        return new RegistrationFragment();
    }

    public RegistrationFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_registration, container, false);

        return rootView;
    }


}
