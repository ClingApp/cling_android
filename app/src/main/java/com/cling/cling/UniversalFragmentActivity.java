package com.cling.cling;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.cling.cling.Fragments.AddProductFragment;
import com.cling.cling.Fragments.ProductFragment;
import com.cling.cling.Fragments.RegistrationFragment;
import com.cling.cling.Fragments.SearchFragment;

import static com.cling.cling.UniversalFragmentActivity.AppropriateFragments.*;

public class UniversalFragmentActivity extends FragmentActivity {


    public static final String ARG_FRAGMENT_ID = "argument_fragment_id";
    public static final String ARG_FRAGMENT_EXTRAS = "argument_fragment_extras";
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
                    .add(R.id.universalFragmentContainer, currentFragment)
                    .commit();
        }
    }

    private void getIntentObject() {

        if (getIntent() != null) {

            Bundle extras = getIntent().getExtras();

            if (!extras.isEmpty()) {

                String fragmentId = extras.getString(ARG_FRAGMENT_ID);
                Bundle fragmentExtras;
                if (extras.containsKey(ARG_FRAGMENT_EXTRAS)) {
                    fragmentExtras = extras.getBundle(ARG_FRAGMENT_EXTRAS);
                }

                if (fragmentId.equals(REGISTRATION.getId())) {

                    currentFragment = RegistrationFragment.newInstance();

                } else if (fragmentId.equals(SEARCH.getId())) {

                    currentFragment = SearchFragment.newInstance();

                } else if (fragmentId.equals(PRODUCT.getId())) {

                    currentFragment = ProductFragment.newInstance();

                } else if (fragmentId.equals(ADD_PRODUCT.getId())) {

                    currentFragment = AddProductFragment.newInstance();
                }

                /*try { escape reflection!
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
                }*/
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
        overridePendingTransition(R.anim.slide_enter_back, R.anim.slide_leave_back);
    }

    public enum AppropriateFragments {

        REGISTRATION("registration"),
        SEARCH("search"),
        PRODUCT("product"),
        ADD_PRODUCT("add_product");

        private String id;

        AppropriateFragments(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }
}
