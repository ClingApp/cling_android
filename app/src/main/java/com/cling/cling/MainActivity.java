package com.cling.cling;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TabHost;

public class MainActivity extends FragmentActivity {

    private TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabHost = (TabHost) findViewById(R.id.mainTabHost);
        initTabHost();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
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
    }

    private class EmptyTabFactory implements TabHost.TabContentFactory {

        @Override
        public View createTabContent(String tag) {
            return new View(MainActivity.this);
        }
    }
}
