package com.cling.cling;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class UniversalFragmentActivity extends FragmentActivity {


    public static final String ARG_FRAGMENT = "argument_fragment";
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universal);

        getIntentObject();

        if (getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (savedInstanceState == null && currentFragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.registrationFragmentContainer, currentFragment)
                    .commit();
        }
    }

    private void getIntentObject() {

        if (getIntent() != null) {

            Bundle extras = getIntent().getExtras();

            if (!extras.isEmpty()) {

                try {
                    Class<?> c = Class.forName(extras.getString(ARG_FRAGMENT));
                    Constructor<?> cons = c.getConstructors()[0];
                    currentFragment = (Fragment) cons.newInstance();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    Log.i("HUETA class", "HUETA CLASS");
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.registration, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fade_out_back, R.anim.fade_in_back);
    }
}
