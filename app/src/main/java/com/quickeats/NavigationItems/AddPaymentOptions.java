package com.quickeats.NavigationItems;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.quickeats.MvpBaseActivity;
import com.quickeats.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Rajesh kumar on 05-08-2018.
 */

public class AddPaymentOptions extends MvpBaseActivity {
    @Override
    public int getLayout() {
        return R.layout.addpaymentcard;
    }

    @Override
    protected Object setupActivityComponent() {
        return null;
    }

    @OnClick(R.id.imgback)
     void backButtonImpl() {
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
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
