package com.cling.cling.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.cling.cling.Adapters.CartAdapter;
import com.cling.cling.R;

import java.util.ArrayList;

public class CartFragment extends Fragment {

    private ExpandableListView listView;
    private CartAdapter adapter;

    public static CartFragment newInstance() {

        return new CartFragment();
    }

    public CartFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_cart, container, false);

        ArrayList<ArrayList<String>> groups = new ArrayList<ArrayList<String>>();
        ArrayList<String> children1 = new ArrayList<String>();
        ArrayList<String> children2 = new ArrayList<String>();
        children1.add("Child_1");
        children1.add("Child_2");
        groups.add(children1);
        children2.add("Child_1");
        children2.add("Child_2");
        children2.add("Child_3");
        groups.add(children2);

        adapter = new CartAdapter(getActivity(), groups);
        listView = (ExpandableListView) rootView.findViewById(R.id.cartExpandableListView);
        listView.setAdapter(adapter);
        listView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                if (listView.isGroupExpanded(groupPosition)) {
                    listView.collapseGroup(groupPosition);
                } else {
                    listView.expandGroup(groupPosition);
                }

                return true;
            }
        });

        return rootView;
    }
}
