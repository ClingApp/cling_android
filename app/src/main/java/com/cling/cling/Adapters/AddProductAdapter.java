package com.cling.cling.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.cling.cling.Fragments.AddProductFragment;
import com.cling.cling.Fragments.AddProductParametersFragment;
import com.cling.cling.Fragments.AddProductPhotoFragment;

/**
 * Created by Tier on 17.09.14.
 */

public class AddProductAdapter extends FragmentStatePagerAdapter {

    private static final int NUMBER_OF_FRAGMENTS = 2;
    private AddProductFragment parentFragment;

    public AddProductAdapter(FragmentManager fm, AddProductFragment parentFragment) {
        super(fm);
        this.parentFragment = parentFragment;
    }

    @Override
    public Fragment getItem(final int position) {

        Fragment fragment = null;

        switch (position) {
            case 0: {

                AddProductParametersFragment parametersFragment = AddProductParametersFragment.newInstance();
                parametersFragment.parentFragment = parentFragment;
                fragment = parametersFragment;
            }
            break;
            case 1: {
                fragment = AddProductPhotoFragment.newInstance();
            }
            break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return NUMBER_OF_FRAGMENTS;
    }
}
