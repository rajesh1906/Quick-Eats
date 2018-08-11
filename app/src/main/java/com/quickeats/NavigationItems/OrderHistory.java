package com.quickeats.NavigationItems;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.quickeats.MvpBaseActivity;
import com.quickeats.R;
import com.quickeats.dashboard.fragments.Collections;
import com.quickeats.dashboard.fragments.EatsInFragment;
import com.quickeats.utils.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderHistory extends MvpBaseActivity {
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setupViewPager(viewpager);
        tabs.setupWithViewPager(viewpager);
    }


    @Override
    public int getLayout() {
        return R.layout.orderhistory;
    }

    @Override
    protected Object setupActivityComponent() {
        return null;
    }

    @OnClick(R.id.imgback)
    void backImpl(){
        finish();
    }

    @Override
    public Activity getActivityFromView() {
        return null;
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void setupPresenter(Object presenter) {

    }

    @Override
    public String getViewIdentity() {
        return null;
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(OrderHistoryDetails.getinstance(), "CURRENT ORDER");
        adapter.addFragment(OrderHistoryDetails.getinstance(), "PAST ORDER");
        adapter.addFragment(OrderHistoryDetails.getinstance(), "ISSUE THREAD");
        viewPager.setAdapter(adapter);
    }
}
