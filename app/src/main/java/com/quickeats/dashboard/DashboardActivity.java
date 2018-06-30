package com.quickeats.dashboard;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.quickeats.MvpBaseActivity;
import com.quickeats.R;
import com.quickeats.dashboard.fragments.BookFragment;
import com.quickeats.dashboard.fragments.OffersFragment;
import com.quickeats.dashboard.fragments.OrderFragment;
import com.quickeats.dashboard.fragments.ProfileFragment;
import com.quickeats.dashboard.fragments.ScanFragment;
import com.quickeats.restaurantdetail.FoodBeverageFragment;
import com.quickeats.utils.DialogManage;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Rajesh kumar on 22-06-2018.
 */

public class DashboardActivity extends MvpBaseActivity implements DialogManage {

    DashboardPresenter presenter;
    @BindView(R.id.navigation)
    BottomNavigationView mbottomNavigationView;

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        presenter = new DashboardImp(this);
        presenter.initialSetUp();
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    public void initilizeViews(){
        mbottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_item1:
                                selectedFragment = BookFragment.newInstance();
                                break;
                            case R.id.action_item2:
                                selectedFragment = FoodBeverageFragment.newInstance();
                                break;
                            case R.id.action_item3:
                                selectedFragment = ScanFragment.newInstance();
                                break;
                            case R.id.action_item4:
                                selectedFragment = OffersFragment.newInstance();
                                break;
                            case R.id.action_item5:
                                selectedFragment = ProfileFragment.newInstance();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, BookFragment.newInstance());
        transaction.commit();
    }

    @OnClick(R.id.ll_booking)
     void bookingImpl(){
        mbottomNavigationView.setSelectedItemId(R.id.action_item1);
    }

    @SuppressLint("ResourceType")
    @OnClick(R.id.ll_order)
     void orderImpl(){
        mbottomNavigationView.setSelectedItemId(R.id.action_item2);
    }
    @SuppressLint("ResourceType")
    @OnClick(R.id.ll_offers)
     void offerImpl(){
        mbottomNavigationView.setSelectedItemId(R.id.action_item4);
    }
    @SuppressLint("ResourceType")
    @OnClick(R.id.rel_profile)
     void proflieImpl(){
        mbottomNavigationView.setSelectedItemId(R.id.action_item5);
    }
    @SuppressLint("ResourceType")
    @OnClick(R.id.rel_scan)
     void scaningImpl(){
        mbottomNavigationView.setSelectedItemId(R.id.action_item3);
    }
}
