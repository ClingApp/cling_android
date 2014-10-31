package com.cling.cling.Utilities;

import com.cling.cling.ClingApp;

/**
 * Created by Tier on 31.10.14.
 */

public class Helper {

    //write all static non-specific methods here

    public static int getPixelValue(int dp) {

        final float scale = ClingApp.getAppContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
