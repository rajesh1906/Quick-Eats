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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rajesh kumar on 27-06-2018.
 */

public class FoodBeverageFragment extends Fragment {
    View mView;
    @BindView(R.id.recyclerview)
    RecyclerView mrecyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.commonrecyclerview,container,false);
        ButterKnife.bind(this,mView);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mrecyclerView.setAdapter(new RestaurentAdapter(FoodBeverageFragment.this));
        return mView;
    }
}
