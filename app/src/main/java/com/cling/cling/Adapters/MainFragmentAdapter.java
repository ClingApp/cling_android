package com.cling.cling.Adapters;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.cling.cling.ClingApp;
import com.cling.cling.Fragments.AddProductFragment;
import com.cling.cling.Fragments.HomeFragment;
import com.cling.cling.Fragments.ProfileFragment;
import com.cling.cling.R;
import com.cling.cling.UniversalFragmentActivity;

/**
 * Created by Tier on 22.09.14.
 */

public class MainFragmentAdapter extends FragmentStatePagerAdapter {

    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if (position == ClingApp.MenuItems.HOME.getPosition()) {

            return HomeFragment.newInstance();

        } else if (position == ClingApp.MenuItems.CAMERA.getPosition()) {

            return AddProductFragment.newInstance();

        } else if (position == ClingApp.MenuItems.PROFILE.getPosition()) {

            return ProfileFragment.newInstance();

        } else {

            return new Fragment();
        }
    }

    @Override
    public int getCount() {
        return ClingApp.MenuItems.values().length;
    }
}
