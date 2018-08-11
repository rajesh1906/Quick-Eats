package com.quickeats.NavigationItems;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.quickeats.MvpBaseActivity;
import com.quickeats.R;

public class SavedAddressActivity extends MvpBaseActivity {
    @Override
    public int getLayout() {
        return R.layout.savedadressactivity;
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
