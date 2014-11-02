package com.cling.cling.Utilities;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;

import com.cling.cling.ClingApp;
import com.cling.cling.R;
import com.google.gson.Gson;

/**
 * Created by Tier on 31.10.14.
 */

public class Helper {

    //write all static non-specific methods here

    public static Gson getGson() {
        return new Gson();
    }

    public static int getPixelValue(int dp) {

        final float scale = ClingApp.getAppContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static final int REQUEST_PICK_IMAGE = 1;

    public static void getImageFromGallery(Fragment fragment) {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        fragment.startActivityForResult(Intent.createChooser(intent, fragment.getString(R.string.title_choose_image)), REQUEST_PICK_IMAGE);
    }

    public static final int REQUEST_IMAGE_CAPTURE = 2;

    public static void captureImageFromCamera(Fragment fragment) {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(fragment.getActivity().getPackageManager()) != null) {
            fragment.startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
}
