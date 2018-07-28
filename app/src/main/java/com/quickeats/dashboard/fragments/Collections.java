package com.quickeats.dashboard.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quickeats.R;

/**
 * Created by Rajesh kumar on 24-06-2018.
 */

public class Collections extends Fragment {
    View view;
    public static Collections newInstance() {
        Collections fragment = new Collections();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.offers,container,false);

        return view;
    }
}
