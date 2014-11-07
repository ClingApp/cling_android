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

    private PreferencesHelper(Context context) {
        this.context = context;
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    //Save your shit here.

    private static final String KEY_MANUAL_LEARNED = "manual_learned";

    public void setUserManualLearned(boolean learned) {

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(KEY_MANUAL_LEARNED, learned);
        editor.apply();
    }

    public boolean isUserManualLearned() {

       return preferences.getBoolean(KEY_MANUAL_LEARNED, false);
    }
}