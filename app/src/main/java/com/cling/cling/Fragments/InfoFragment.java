package com.cling.cling.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cling.cling.R;

public class InfoFragment extends Fragment {

    private static final String ARG_DESCRIPTION = "info_description";
    private static final String ARG_IMAGE = "info_image";
    private static final String ARG_POSITION = "info_position";

    private String info;
    private String imageUrl;
    private int position;

    public static InfoFragment newInstance(String info, String imageUrl, int position) {

        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DESCRIPTION, info);
        args.putString(ARG_IMAGE, imageUrl);
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);

        return fragment;
    }

    public InfoFragment() {

    }

    private void getArgumentsObjects() {

        Bundle arguments = getArguments();
        if (arguments != null) {
            info = arguments.getString(ARG_DESCRIPTION);
            imageUrl = arguments.getString(ARG_IMAGE);
            position = arguments.getInt(ARG_POSITION);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getArgumentsObjects();

        View rootView = inflater.inflate(R.layout.fragment_info, container, false);

        TextView infoTextView = (TextView) rootView.findViewById(R.id.infoTextView);
        ImageView imageView = (ImageView) rootView.findViewById(R.id.infoImageView);

        infoTextView.setText(info);

        /*if (position != 0) {
            imageView.setVisibility(View.INVISIBLE);
        }*/

        //TODO: load description image (if needed)

        return rootView;
    }
}
