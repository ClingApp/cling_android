package com.cling.cling.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cling.cling.ClingApp;
import com.cling.cling.InfoActivity;
import com.cling.cling.MainActivity;
import com.cling.cling.R;
import com.cling.cling.UniversalFragmentActivity;
import com.cling.cling.Utilities.Helper;

public class SettingFragment extends Fragment {

    private LinearLayout actionsLayout;
//    private ImageView avatarImageView;

    public static SettingFragment newInstance() {

        return new SettingFragment();
    }

    public SettingFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        actionsLayout = (LinearLayout) rootView.findViewById(R.id.userSettingsActionLayout);

        Button changeButton = (Button) rootView.findViewById(R.id.changeButton);
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                ClingApp.startActivityWithAnimation(getActivity(), intent);
            }
        });


        return rootView;
    }

}
