package com.quickeats.ordertype;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.quickeats.MvpBaseActivity;
import com.quickeats.R;

/**
 * Created by Rajesh kumar on 07-07-2018.
 */

public class OrderStatus extends MvpBaseActivity {
    @Override
    public int getLayout() {
        return R.layout.orderstatus;
    }

    @Override
    protected Object setupActivityComponent() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
}
