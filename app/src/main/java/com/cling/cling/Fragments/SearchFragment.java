package com.cling.cling.Fragments;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cling.cling.R;

public class SearchFragment extends Fragment {

    public static SearchFragment newInstance() {

        return new SearchFragment();
    }

    public SearchFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (getActivity() != null && getActivity().getActionBar() != null) {

            ActionBar actionBar = getActivity().getActionBar();
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setCustomView(R.layout.view_search);
        }

        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        return rootView;
    }
}
