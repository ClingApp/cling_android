package com.cling.cling;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.cling.cling.Adapters.InfoAdapter;


public class InfoActivity extends FragmentActivity {

    private ViewPager viewPager;
    SimplePageIndicator pageIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        if (getActionBar() != null) {
            getActionBar().hide();
        }
        setContentView(R.layout.activity_info);

        String[] descriptions = {"Семестровый проект по `Android`"};

        InfoAdapter adapter = new InfoAdapter(getSupportFragmentManager(), descriptions);
        viewPager = (ViewPager) findViewById(R.id.infoViewPager);
        pageIndicator = (SimplePageIndicator) findViewById(R.id.infoPageIndicator);
        pageIndicator.setHighlightIndex(descriptions.length);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {

            }

            @Override
            public void onPageSelected(int i) {
                pageIndicator.notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        pageIndicator.setViewPager(viewPager);
        pageIndicator.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
