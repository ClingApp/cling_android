package com.cling.cling.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.cling.cling.R;
import com.cling.cling.Utilities.Helper;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;

public class AddProductPhotoFragment extends Fragment {

    private RelativeLayout addButton, deleteButton;
    private ImageButton cameraButton;
    private ImageView photoImageView;
    private Bitmap photo;
    private int position;

    private static final String ARG_POSITION = "fragment_position";

    public static AddProductPhotoFragment newInstance(int position) {

        AddProductPhotoFragment fragment = new AddProductPhotoFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(ARG_POSITION, position);
        fragment.setArguments(arguments);

        return fragment;
    }

    public AddProductPhotoFragment() {

    }

    private void getFragmetArguments() {

        if (!getArguments().isEmpty()) {
            position = getArguments().getInt(ARG_POSITION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_add_product_photo, container, false);
        addButton = (RelativeLayout) rootView.findViewById(R.id.addProductPhotoView);
        cameraButton = (ImageButton) rootView.findViewById(R.id.addProductPhotoCameraButton);
        deleteButton = (RelativeLayout) rootView.findViewById(R.id.addProductPhotoDeleteView);
        photoImageView = (ImageView) rootView.findViewById(R.id.addProductPhotoImageView);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.getImageFromGallery(AddProductPhotoFragment.this);
            }
        });

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.captureImageFromCamera(AddProductPhotoFragment.this);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteButton.setVisibility(View.GONE);
                photoImageView.setVisibility(View.GONE);
                addButton.setVisibility(View.VISIBLE);
            }
        });

        return rootView;
    }

    public Bitmap getImage() {
        return photo;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.i("Pam", String.valueOf(resultCode));

        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == Helper.REQUEST_PICK_IMAGE) {

                photo = decodeUri(data.getData());

            }

            if (requestCode == Helper.REQUEST_IMAGE_CAPTURE) {
                photo = (Bitmap) data.getExtras().get("data"); //TODO: capture and save full size photo!
            }

            photoImageView.setImageBitmap(photo);
            photoImageView.setVisibility(View.VISIBLE);
            addButton.setVisibility(View.GONE);
            deleteButton.setVisibility(View.VISIBLE);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public Bitmap decodeUri(Uri uri) {

        ParcelFileDescriptor parcelFD = null;
        try {
            parcelFD = getActivity().getContentResolver().openFileDescriptor(uri, "r");
            FileDescriptor imageSource = parcelFD.getFileDescriptor();

            // Decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            BitmapFactory.decodeFileDescriptor(imageSource, null, o);

            // the new size we want to scale to
            final int REQUIRED_SIZE = 1024;

            // Find the correct scale value. It should be the power of 2.
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp < REQUIRED_SIZE && height_tmp < REQUIRED_SIZE) {
                    break;
                }
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }

            // decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;

            return BitmapFactory.decodeFileDescriptor(imageSource, null, o2);

        } catch (FileNotFoundException e) {
            // handle errors
        } finally {

            if (parcelFD != null) {
                try {
                    parcelFD.close();
                } catch (IOException e) {
                    // ignored
                }
            }
        }

        return null;
    }
}
