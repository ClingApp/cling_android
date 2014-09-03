package com.cling.cling;

import android.app.Application;
import android.content.Context;

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

        HOME(0, R.string.menu_item_home, R.drawable.selector_tab_button),
        CART(1, R.string.menu_item_home, R.drawable.selector_tab_button),
        CAMERA(2, R.string.menu_item_home, R.drawable.selector_tab_button),
        PROFILE(3, R.string.menu_item_home, R.drawable.selector_tab_button);

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
}
