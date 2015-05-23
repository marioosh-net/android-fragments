package com.example.marioosh.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * @author marioosh
 */
public class TestFragment extends Fragment {

    interface ClickFragmentListener {
        void onClick(Fragment fragment);
    }

    ClickFragmentListener listener;
    @InjectView(R.id.text) TextView text;
    @InjectView(R.id.ll) View ll;

    public static TestFragment newInstance(String text) {
        Bundle b = new Bundle();
        b.putString("title", text);
        TestFragment f = new TestFragment();
        f.setArguments(b);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.test_fragment, container, false);
        ButterKnife.inject(this, root);
        text.setText(getArguments().getString("title"));
        return root;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = (ClickFragmentListener)activity;
    }

    @OnClick(R.id.ll)
    public void click() {
        if(listener!=null) {
            listener.onClick(this);
        }
    }
}
