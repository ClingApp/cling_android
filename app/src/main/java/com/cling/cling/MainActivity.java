package com.cling.cling;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TabHost;

import com.cling.cling.Adapters.MainFragmentAdapter;
import com.cling.cling.Utilities.PreferencesHelper;

public class MainActivity extends FragmentActivity {

    private TabHost tabHost;
    private ViewPager viewPager;

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

        initViewPager();
        initTabHost();

        if (!PreferencesHelper.INSTANCE.isUserManualLearned()) {
            Intent intent = new Intent(MainActivity.this, InfoActivity.class);
            startActivity(intent);
            PreferencesHelper.INSTANCE.setUserManualLearned(true);
        }
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

        } else if (id == R.id.action_settings) {

            Intent intent = new Intent(MainActivity.this, InfoActivity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }


    /* Manage view pager*/

    private void initViewPager() {

        MainFragmentAdapter adapter = new MainFragmentAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.mainViewPager);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                tabHost.setCurrentTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


    /* Manage tabs */

    private void initTabHost() {

        tabHost = (TabHost) findViewById(R.id.mainTabHost);

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

                for (ClingApp.MenuItems item : ClingApp.MenuItems.values()) {

                    if (tabId.equals(item.getTitle())) {
                        viewPager.setCurrentItem(item.getPosition(), true);
                    }
                }
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

    /*private void showHome() {

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
    }*/
}
