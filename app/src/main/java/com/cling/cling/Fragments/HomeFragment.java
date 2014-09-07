package com.cling.cling.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.cling.cling.Adapters.GridAdapter;
import com.cling.cling.R;

public class HomeFragment extends Fragment {

    private GridView gridView;

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

        String[] data = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"};

        GridAdapter adapter = new GridAdapter(getActivity(), data);
        gridView = (GridView) rootView.findViewById(R.id.homeGridView);
        gridView.setAdapter(adapter);
        //adapter.notifyDataSetChanged();

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

}
