package com.quickeats.restaurantdetail;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.quickeats.MvpBaseActivity;
import com.quickeats.R;
import com.quickeats.dashboard.fragments.ProfileFragment;
import com.quickeats.restaurantdetail.LoadFragment.ImplItems;
import com.quickeats.utils.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rajesh kumar on 28-06-2018.
 */

public class RestaurentActivity extends MvpBaseActivity implements ImplItems {

    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    public static Activity instance;
    @BindView(R.id.lladdeditems)
    LinearLayout lladdeditems;
    @BindView(R.id.txtnumberitems)
    TextView txtnumberitems;

    @Override
    public int getLayout() {
        return R.layout.restaurentdetailfragment;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setupViewPager(mViewPager);
        instance = this;
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


    @Override
    public void getItemPosition(int position) {
        lladdeditems.setVisibility(View.VISIBLE);
        txtnumberitems.setText(position + " Items Added");
//        Toast.makeText(this,"Item Position"+position,Toast.LENGTH_LONG).show();
    }
}
