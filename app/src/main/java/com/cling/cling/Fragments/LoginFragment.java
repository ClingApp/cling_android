package com.cling.cling.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cling.cling.R;
import com.cling.cling.UniversalFragmentActivity;

public class LoginFragment extends Fragment {

    public static LoginFragment newInstance() {

        return new LoginFragment();
    }

    public LoginFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        Button registrationButton = (Button) rootView.findViewById(R.id.signUpButton);
        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String className = RegistrationFragment.class.getCanonicalName();
                Log.i("CLASS NAME", className);
                Intent intent = new Intent(getActivity(), UniversalFragmentActivity.class);
                intent.putExtra(UniversalFragmentActivity.ARG_FRAGMENT, className);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

        return rootView;
    }
}
