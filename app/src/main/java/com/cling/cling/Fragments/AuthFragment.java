package com.cling.cling.Fragments;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cling.cling.MainActivity;
import com.cling.cling.R;
import com.cling.cling.Utilities.PreferencesHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthFragment extends Fragment {

    final String regex = "\\(\\d{3}\\)\\d{3}\\-\\d{2}\\-\\d{2}";

    String a;
    int keyDel;

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

        final EditText phoneAuthEditText = (EditText) rootView.findViewById(R.id.phoneAuthEditText);
        final EditText confirmCodeEditText = (EditText) rootView.findViewById(R.id.confirmCodeEditText);

        final TextView errorCodeTextView = (TextView) rootView.findViewById(R.id.errorCodeTextView);

        confirmPhoneButton.setVisibility(View.GONE);
        confirmCodeEditText.setVisibility(View.GONE);

        phoneAuthEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (phoneAuthEditText.getText().length() == 1) {
                    phoneAuthEditText.setText(phoneAuthEditText.getText().toString() + "-(");
                    phoneAuthEditText.setSelection(phoneAuthEditText.getText().length());
                }
                else if (phoneAuthEditText.getText().length() == 6) {
                    phoneAuthEditText.setText(phoneAuthEditText.getText().toString() + ")");
                    phoneAuthEditText.setSelection(phoneAuthEditText.getText().length());
                }
                else if (phoneAuthEditText.getText().length() == 10) {
                    phoneAuthEditText.setText(phoneAuthEditText.getText().toString() + "-");
                    phoneAuthEditText.setSelection(phoneAuthEditText.getText().length());
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        phoneAuthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (phoneAuthEditText.getText().toString().length() == 14) {
                    confirmPhoneButton.setVisibility(View.VISIBLE);
                    confirmCodeEditText.setVisibility(View.VISIBLE);
                } else {
                    Toast toast = Toast.makeText(getActivity(), "Error phone", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();                }
            }
        });

        confirmPhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("auth", confirmCodeEditText.getText().toString());
                if ("0000".equals(confirmCodeEditText.getText().toString())) {
                    errorCodeTextView.setVisibility(View.GONE);
                    confirmPhoneButton.setVisibility(View.GONE);
                    int userId = 1;
                    PreferencesHelper.INSTANCE.setUserId(userId);
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast toast = Toast.makeText(getActivity(), "Error code", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });

        return rootView;
    }
}
