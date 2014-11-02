package com.cling.cling.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import com.cling.cling.Adapters.CartAdapter;
import com.cling.cling.ClingApp;
import com.cling.cling.R;
import com.cling.cling.UniversalFragmentActivity;
import com.cling.cling.Utilities.Helper;

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

        //indicator position
        int width = getResources().getDisplayMetrics().widthPixels;
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            listView.setIndicatorBounds(width - Helper.getPixelValue(40), width);
        } else {
            listView.setIndicatorBoundsRelative(width - Helper.getPixelValue(40), width);
        }
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //TODO: put product id as parameter
                Intent intent = new Intent(getActivity(), UniversalFragmentActivity.class);
                intent.putExtra(UniversalFragmentActivity.ARG_FRAGMENT_ID, UniversalFragmentActivity.AppropriateFragments.PRODUCT.getId());
                ClingApp.startActivityWithAnimation(getActivity(), intent);
            }
        });

        return rootView;
    }
}
