package com.cling.cling.Utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.cling.cling.ClingApp;

/**
 * Created by Tier on 02.11.14.
 */

public enum PreferencesHelper {

    INSTANCE(ClingApp.getAppContext());

    private Context context;
    private SharedPreferences preferences;

    PreferencesHelper(Context context) {
        this.context = context;
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    //Save your shit here.
}