package com.quickeats.dashboard.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.quickeats.R;
import com.quickeats.dashboard.DashboardActivity;
import com.quickeats.dashboard.MenuCallback;
import com.quickeats.utils.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Rajesh kumar on 22-06-2018.
 */

public class BookFragment extends Fragment {
    View view;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    boolean shown;
    public static BookFragment newInstance() {
        BookFragment fragment = new BookFragment();
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.bookfragment,container,false);
        ButterKnife.bind(this,view);
        if(!shown) {
            shown = true;
            setupViewPager(viewpager);
            tabs.setupWithViewPager(viewpager);
        }

        return view;
    }
    @OnClick(R.id.imgmenu)
    public void menuClick(){
        MenuCallback callback = (MenuCallback) DashboardActivity.instance;
        callback.callNavigationDrawer();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){
            shown=false;
            setupViewPager(viewpager);
            tabs.setupWithViewPager(viewpager);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(EatsInFragment.newInstance("8"), "EAT IN");
        adapter.addFragment(new Collections(), "COLLECTION");
        viewPager.setAdapter(adapter);
    }

}
