package com.example.marioosh.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * @author marioosh
 */
public class TwinFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.twin_fragment, container, false);
        ButterKnife.inject(this, root);

        getChildFragmentManager().beginTransaction()
                .replace(R.id.top, TestFragment.newInstance("top1"), "top")
                .replace(R.id.bottom, TestFragment.newInstance("bottom1"), "bottom")
                //.addToBackStack(null)
                .commit();

        ((ActionBarActivity)getActivity()).setTitle(getArguments().getString("actionBarTitle"));
        return root;
    }

    public static TwinFragment newInstance(String actionBarTitle) {
        TwinFragment f = new TwinFragment();
        Bundle b = new Bundle();
        b.putString("actionBarTitle", actionBarTitle);
        f.setArguments(b);
        return f;
    }

}
