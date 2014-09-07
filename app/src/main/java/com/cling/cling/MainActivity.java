package com.cling.cling;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TabHost;

import com.cling.cling.Fragments.HomeFragment;

public class MainActivity extends FragmentActivity {

    private TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = (TabHost) findViewById(R.id.mainTabHost);
        initTabHost();
        tabHost.setCurrentTab(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_search) {
            return true;
        } else if (id == R.id.action_settings) {

            Intent intent = new Intent(MainActivity.this, InfoActivity.class);
            startActivity(intent);
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

                    Log.i(tabId, tabId);

                    getSupportFragmentManager().beginTransaction().replace(R.id.mainFragmentContainer,
                            HomeFragment.newInstance(), ClingApp.MenuItems.HOME.getTitle()).commit();
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

}
