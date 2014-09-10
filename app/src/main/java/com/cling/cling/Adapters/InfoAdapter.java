package com.cling.cling.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.cling.cling.Fragments.InfoFragment;
import com.cling.cling.Fragments.LoginFragment;

/**
 * Created by Tier on 08.09.14.
 */

public class InfoAdapter extends FragmentStatePagerAdapter {

    private String[] data;

    public InfoAdapter(FragmentManager fm, String[] data) {
        super(fm);
        this.data = data;
    }

    @Override
    public Fragment getItem(int position) {

        if (position == data.length) {

            return LoginFragment.newInstance();

        } else {

            return InfoFragment.newInstance(data[position], "No image", position);
        }
    }

    @Override
    public int getCount() {
        return data.length + 1;
    }
}
