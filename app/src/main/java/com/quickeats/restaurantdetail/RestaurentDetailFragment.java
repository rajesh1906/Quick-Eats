package com.quickeats.restaurantdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quickeats.R;
import com.quickeats.dashboard.adapter.RestaurentAdapter;
import com.quickeats.dashboard.fragments.Collections;
import com.quickeats.dashboard.fragments.EatsInFragment;
import com.quickeats.utils.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rajesh kumar on 26-06-2018.
 */

public class RestaurentDetailFragment extends Fragment implements LoadFragment {
    View mView;
    @BindView(R.id.recyclerview)
    RecyclerView mrecyclerView;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mView = inflater.inflate(R.layout.restaurentdetailfragment,container,false);
        ButterKnife.bind(this,mView);
//        mrecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mrecyclerView.setAdapter(new RestaurentAdapter(RestaurentDetailFragment.this));
        setupViewPager(viewpager);
        tabs.setupWithViewPager(viewpager);
        return mView;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new FoodBeverageFragment(), "Meat");
        adapter.addFragment(new FoodBeverageFragment(), "Veg/Vegan");
        adapter.addFragment(new FoodBeverageFragment(), "Drinks");
        adapter.addFragment(new FoodBeverageFragment(), "Desserts");
        viewPager.setAdapter(adapter);
    }
    @Override
    public void load() {

    }
}
