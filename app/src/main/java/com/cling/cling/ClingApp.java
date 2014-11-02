package com.cling.cling;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Tier on 02.09.14.
 */

public class ClingApp extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getAppContext() {
        return context;
    }

    public enum MenuItems {

        HOME(0, R.string.menu_item_home, R.drawable.selector_button_home),
        CART(1, R.string.menu_item_cart, R.drawable.selector_button_cart),
        //CAMERA(2, R.string.menu_item_camera, R.drawable.selector_button_camera),
        PROFILE(2, R.string.menu_item_profile, R.drawable.selector_button_profile);

        private int position;
        private int title;
        private int drawableId;

        MenuItems(int position, int title, int drawableId) {
            this.position = position;
            this.title = title;
            this.drawableId = drawableId;
        }

        public int getPosition() {
            return position;
        }

        public String getTitle() {
            return context.getString(title);
        }

        public int getDrawableId() {
            return drawableId;
        }
    }

    public static void startActivityWithAnimation(Activity activity, Intent intent) {

        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.scale_open, R.anim.scale_close);
    }
}
