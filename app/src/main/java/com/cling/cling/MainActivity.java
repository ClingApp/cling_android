package com.cling.cling;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TabHost;

import com.cling.cling.Fragments.CartFragment;
import com.cling.cling.Fragments.HomeFragment;
import com.cling.cling.Fragments.ProfileFragment;

public class MainActivity extends FragmentActivity {

    private TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {

                if (getActionBar() != null) {

                    if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                        getActionBar().setDisplayHomeAsUpEnabled(true);
                    } else {
                        getActionBar().setDisplayHomeAsUpEnabled(false);
                    }
                }
            }
        });

        tabHost = (TabHost) findViewById(R.id.mainTabHost);
        initTabHost();
        showHome();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home) {

            if (getSupportFragmentManager().getBackStackEntryCount() != 0) {

                onBackPressed();
                return true;

            } else {

                return super.onOptionsItemSelected(item);
            }

        } else if (id == R.id.action_search) {

            Intent intent = new Intent(MainActivity.this, UniversalFragmentActivity.class);
            intent.putExtra(UniversalFragmentActivity.ARG_FRAGMENT_ID, UniversalFragmentActivity.AppropriateFragments.SEARCH.getId());
            startActivity(intent);
            overridePendingTransition(R.anim.slide_enter, R.anim.slide_leave);

        } else if (id == R.id.action_settings) {

            Intent intent = new Intent(MainActivity.this, InfoActivity.class);
            startActivity(intent);

        } else if (id == R.id.action_add) {

            Intent intent = new Intent(MainActivity.this, UniversalFragmentActivity.class);
            intent.putExtra(UniversalFragmentActivity.ARG_FRAGMENT_ID, UniversalFragmentActivity.AppropriateFragments.ADD_PRODUCT.getId());
            startActivity(intent);
            overridePendingTransition(R.anim.slide_enter, R.anim.slide_leave);

        }

        return super.onOptionsItemSelected(item);
    }


    /* Manage tabs */

    private void initTabHost() {

        tabHost.setup();
        TabHost.TabSpec spec;
        View tabView;
        ImageButton tabImageButton;

        for (ClingApp.MenuItems item : ClingApp.MenuItems.values()) {

            tabView = LayoutInflater.from(this).inflate(R.layout.view_tab, null, false);
            tabImageButton = (ImageButton) tabView.findViewById(R.id.tabImageButton);
            tabImageButton.setBackgroundResource(item.getDrawableId());

            spec = tabHost.newTabSpec(item.getTitle());
            spec.setContent(new EmptyTabFactory());
            spec.setIndicator(tabView);
            tabHost.addTab(spec);
        }

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            @Override
            public void onTabChanged(String tabId) {

                if (tabId.equals(ClingApp.MenuItems.HOME.getTitle())) {

                    showHome();

                } else if (tabId.equals(ClingApp.MenuItems.CART.getTitle())) {

                    popToRootFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainFragmentContainer,
                            CartFragment.newInstance(), ClingApp.MenuItems.CART.getTitle()).commit();

                } else if (tabId.equals(ClingApp.MenuItems.PROFILE.getTitle())) {

                    popToRootFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.mainFragmentContainer,
                            ProfileFragment.newInstance(), ClingApp.MenuItems.PROFILE.getTitle()).commit();
                }
                /*else if (tabId.equals(ClingApp.MenuItems.CAMERA.getTitle())) {

                    getSupportFragmentManager().beginTransaction().replace(R.id.mainFragmentContainer,
                            AddProductFragment.newInstance(), ClingApp.MenuItems.CAMERA.getTitle()).commit();

                }*/
            }
        });
    }

    private class EmptyTabFactory implements TabHost.TabContentFactory {

        @Override
        public View createTabContent(String tag) {
            return new View(MainActivity.this);
        }
    }


    /* Manage fragments */

    @Override
    public void onBackPressed() {

        if (getFragmentManager().getBackStackEntryCount() == 0) {

            super.onBackPressed();

        } else {

            getSupportFragmentManager().popBackStack();
        }
    }

    private void popToRootFragment() {

        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    private void showHome() {

        popToRootFragment();

        if (tabHost.getCurrentTab() == ClingApp.MenuItems.HOME.getPosition()) {

            getSupportFragmentManager().beginTransaction().replace(R.id.mainFragmentContainer,
                    HomeFragment.newInstance(), ClingApp.MenuItems.HOME.getTitle()).commit();
        } else {

            tabHost.setCurrentTab(ClingApp.MenuItems.HOME.getPosition());
        }
    }

    public static void presentFragment(FragmentActivity activity, Fragment fragment) {

        if (activity != null && activity instanceof MainActivity) {

            activity.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.slide_enter,
                    R.anim.slide_leave,
                    R.anim.slide_in_left,
                    R.anim.slide_leave_rignt).replace(R.id.mainFragmentContainer, fragment).addToBackStack(null).commit();
        }
    }
}
