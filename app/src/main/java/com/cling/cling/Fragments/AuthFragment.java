package com.cling.cling.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cling.cling.R;

public class AuthFragment extends Fragment {

    public static AuthFragment newInstance() {

        return new AuthFragment();
    }

    public AuthFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_auth, container, false);

        Button face = (Button) rootView.findViewById(R.id.facebookButton);
        Button vk = (Button) rootView.findViewById(R.id.vkontakteButton);
        Button twitter = (Button) rootView.findViewById(R.id.twitterButton);

        Button registrationButton = (Button) rootView.findViewById(R.id.signUpButton);
        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Intent intent = new Intent(getActivity(), UniversalFragmentActivity.class);
                intent.putExtra(UniversalFragmentActivity.ARG_FRAGMENT_ID, UniversalFragmentActivity.AppropriateFragments.REGISTRATION.getId());
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_enter, R.anim.slide_leave);*/

                RegistrationDialogFragment.newInstance().show(getFragmentManager(), "registration");
            }
        });

        Button loginButton = (Button) rootView.findViewById(R.id.signInButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginDialogFragment.newInstance().show(getFragmentManager(), "login");
            }
        });

        return rootView;
    }
}
