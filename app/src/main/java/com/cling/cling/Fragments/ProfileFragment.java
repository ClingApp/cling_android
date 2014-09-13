package com.cling.cling.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cling.cling.R;

public class ProfileFragment extends Fragment {

    public static ProfileFragment newInstance() {

        return new ProfileFragment();
    }

    public ProfileFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        return rootView;
    }
}
