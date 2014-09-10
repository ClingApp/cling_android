package com.cling.cling.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cling.cling.Adapters.ActionsAdapter;
import com.cling.cling.R;

public class ProfileFragment extends Fragment {

    private ListView actionsListView;
    private ActionsAdapter adapter;

    public static ProfileFragment newInstance() {

        return new ProfileFragment();
    }

    public ProfileFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        String[] actions = {"hueta 1", "hueta 2", "hueta 3"};

        adapter = new ActionsAdapter(getActivity(), actions);
        actionsListView = (ListView) rootView.findViewById(R.id.profileActionsListView);
        actionsListView.setAdapter(adapter);

        return rootView;
    }
}
