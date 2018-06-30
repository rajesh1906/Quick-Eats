package com.quickeats.restaurantdetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.quickeats.MvpBaseActivity;
import com.quickeats.R;
import com.quickeats.dashboard.fragments.ProfileFragment;
import com.quickeats.utils.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rajesh kumar on 28-06-2018.
 */

public class RestaurentActivity extends MvpBaseActivity {

    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @Override
    public int getLayout() {

        return R.layout.restaurentdetailfragment;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setupViewPager(mViewPager);
        tabs.setupWithViewPager(mViewPager);


    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(this.getSupportFragmentManager());
        adapter.addFragment(new FoodBeverageFragment(), "Meat");
        adapter.addFragment(new FoodBeverageFragment(), "Veg/Vegan");
        adapter.addFragment(new FoodBeverageFragment(), "Drinks");
        adapter.addFragment(new FoodBeverageFragment(), "Desserts");
        viewPager.setAdapter(adapter);
    }
}
