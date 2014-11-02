package com.cling.cling.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.cling.cling.Fragments.AddProductParametersFragment;

import java.util.List;

/**
 * Created by Tier on 17.09.14.
 */

public class AddProductAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList;

    public AddProductAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;

        for (Fragment fragment : fragmentList) {
            if (fragment.isAdded()) {
                fm.beginTransaction().remove(fragment).commit();
            }
        }
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return AddProductParametersFragment.newInstance();
        } else {
            return fragmentList.get(position - 1);
        }
    }

    @Override
    public int getCount() {
        return fragmentList.size() + 1;
    }

    /*@Override
    public int getItemPosition(Object object){
        return PagerAdapter.POSITION_NONE;
    }*/
}
