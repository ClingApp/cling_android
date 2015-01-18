package com.cling.cling.Fragments;

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
import com.cling.cling.R;
import com.cling.cling.Utilities.Helper;

public class ProfileFragment extends Fragment {

    private LinearLayout actionsLayout;
    private ImageView avatarImageView;

    public static ProfileFragment newInstance() {

        return new ProfileFragment();
    }

    public ProfileFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        avatarImageView = (ImageView) rootView.findViewById(R.id.profileAvatarImageView);
        actionsLayout = (LinearLayout) rootView.findViewById(R.id.userProfileActionLayout);

        avatarImageView.setImageResource(R.drawable.dump_avatar);
        initActionButtons();

        return rootView;
    }

    private void initActionButtons() {

        for (UserAction action : UserAction.values()) {

            Button actionButton = new Button(getActivity());
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            if (action.getId() == UserAction.LOGOUT.getId()) {
                layoutParams.setMargins(Helper.getPixelValue(10), Helper.getPixelValue(20), Helper.getPixelValue(10), Helper.getPixelValue(5));
                actionButton.setTextColor(getResources().getColorStateList(R.color.text_selector_user_action_logout));
                actionButton.setBackgroundResource(R.drawable.selector_user_action_logout);
            } else {
                layoutParams.setMargins(Helper.getPixelValue(10), Helper.getPixelValue(5), Helper.getPixelValue(10), Helper.getPixelValue(5));
                actionButton.setTextColor(getResources().getColorStateList(R.color.text_selector_user_action));
                actionButton.setBackgroundResource(R.drawable.selector_user_action);
            }
            actionButton.setLayoutParams(layoutParams);
            actionButton.setMinWidth(Helper.getPixelValue(300));
            actionButton.setMaxWidth(Helper.getPixelValue(400));
            actionButton.setText(action.getTitle());
            actionButton.setTag(action.getTitle());
            actionButton.setOnClickListener(actionsListener);

            actionsLayout.addView(actionButton);
        }
    }

    private View.OnClickListener actionsListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Log.i("ACTION CLICK", (String) v.getTag());
        }
    };

    private enum UserAction {

        MY_ITEMS(R.string.title_action_my_items),
        LOGOUT(R.string.title_action_logout);

        private int title;

        UserAction(int title) {
            this.title = title;
        }

        public int getId() {
            return title;
        }

        public String getTitle() {
            return ClingApp.getAppContext().getString(title);
        }
    }
}
