package com.example.marioosh.fragments;

import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import junit.framework.Test;


public class MainActivity extends ActionBarActivity implements TestFragment.ClickFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getSupportFragmentManager().findFragmentByTag("login") == null) { // to avoid replacing fragments on configuration change
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, TestFragment.newInstance("login", 1), "login")
                            //.addToBackStack("login")
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void onClick(Fragment fragment) {
        Log.i("click", fragment+"");
        Log.i("args", fragment.getArguments()+"");
        if(fragment != null && fragment instanceof TestFragment) {

            String title = fragment.getArguments().getString("title");
            int count = fragment.getArguments().getInt("count");

            if(title.equals("login")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, TwinFragment.newInstance("Twin1"))
                        .addToBackStack("twin1")
                        .commit();
            } else if(title.equals("top")) {
                fragment.getParentFragment().getChildFragmentManager()
                        .beginTransaction().replace(R.id.top, TestFragment.newInstance("top",++count))
                        .addToBackStack(null)
                        .commit();
            } else if(title.equals("bottom")) {
                fragment.getParentFragment().getChildFragmentManager()
                        .beginTransaction().replace(R.id.bottom, TestFragment.newInstance("bottom",++count))
                        .addToBackStack(null)
                        .commit();
            }
        }
    }

    @Override
    public void onBackPressed() {
        Fragment current = getSupportFragmentManager().findFragmentById(R.id.container);
        if(current.getChildFragmentManager().getBackStackEntryCount()>0){
            current.getChildFragmentManager().popBackStackImmediate();
            return;
        }
        super.onBackPressed();
    }
}
