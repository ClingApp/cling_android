package com.cling.cling.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cling.cling.MainActivity;
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

        final Button phoneAuthButton = (Button) rootView.findViewById(R.id.phoneAuthButton);
        final Button confirmPhoneButton = (Button) rootView.findViewById(R.id.confirmPhoneButton);

        final EditText confirmCodeEditText = (EditText) rootView.findViewById(R.id.confirmCodeEditText);

        final TextView errorCodeTextView = (TextView) rootView.findViewById(R.id.errorCodeTextView);

        confirmPhoneButton.setVisibility(View.GONE);
        confirmCodeEditText.setVisibility(View.GONE);

        phoneAuthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmPhoneButton.setVisibility(View.VISIBLE);
                confirmCodeEditText.setVisibility(View.VISIBLE);
            }
        });

        confirmPhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("auth", confirmCodeEditText.getText().toString());
                if ("0000".equals(confirmCodeEditText.getText().toString())) {
                    errorCodeTextView.setVisibility(View.GONE);
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                } else {
                    errorCodeTextView.setVisibility(View.VISIBLE);
                }
            }
        });

//        Button registrationButton = (Button) rootView.findViewById(R.id.signUpButton);
//        registrationButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                /*Intent intent = new Intent(getActivity(), UniversalFragmentActivity.class);
//                intent.putExtra(UniversalFragmentActivity.ARG_FRAGMENT_ID, UniversalFragmentActivity.AppropriateFragments.REGISTRATION.getId());
//                startActivity(intent);
//                getActivity().overridePendingTransition(R.anim.slide_enter, R.anim.slide_leave);*/
//
//                RegistrationDialogFragment.newInstance().show(getFragmentManager(), "registration");
//            }
//        });
//
//        Button loginButton = (Button) rootView.findViewById(R.id.signInButton);
//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                LoginDialogFragment.newInstance().show(getFragmentManager(), "login");
//            }
//        });

        return rootView;
    }
}
