package com.quickeats.restaurantdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quickeats.R;
import com.quickeats.dashboard.adapter.RestaurentAdapter;

/**
 * Created by Rajesh kumar on 26-06-2018.
 */

public class RestaurentDetailFragment extends Fragment implements LoadFragment {
    View mView;
    RecyclerView mrecyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.restaurentdetailfragment,container,false);
        mrecyclerView = mView.findViewById(R.id.recyclerview);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mrecyclerView.setAdapter(new RestaurentAdapter(RestaurentDetailFragment.this));
        return mView;
    }


    @Override
    public void load() {

    }
}
