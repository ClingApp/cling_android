package com.cling.cling.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cling.cling.R;

/**
 * Created by Tier on 07.11.14.
 */

public class LoginDialogFragment extends DialogFragment {

    private View noticeView;

    public static LoginDialogFragment newInstance() {

        return new LoginDialogFragment();
    }

    public LoginDialogFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(true);
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme_Translucent_NoTitleBar);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        super.onCreateView(inflater, container, savedInstanceState);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        View rootView = inflater.inflate(R.layout.dialog_login, container, false);
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
