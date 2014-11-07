package com.cling.cling.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.cling.cling.R;

public class RegistrationDialogFragment extends DialogFragment {

    private EditText email, password, repeatPassword;
    private View noticeView;

    public static RegistrationDialogFragment newInstance() {

        return new RegistrationDialogFragment();
    }

    public RegistrationDialogFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(true);
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Translucent_NoTitleBar);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        View rootView = inflater.inflate(R.layout.dialog_registration, container, false);
        noticeView = rootView.findViewById(R.id.includeNoticeView);

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissNotice();
            }
        });

        return rootView;
    }

    private void showNotice(boolean negative, String message) {

        if (negative) {
            noticeView.setBackgroundColor(getResources().getColor(R.color.negativeRed));
        } else {
            noticeView.setBackgroundColor(getResources().getColor(R.color.positiveGreen));
        }

        TextView messageTextView = (TextView) noticeView.findViewById(R.id.noticeViewMessageTextView);
        messageTextView.setText(message);
        noticeView.setVisibility(View.VISIBLE);
    }

    private void dismissNotice() {
        noticeView.setVisibility(View.GONE);
    }
}
