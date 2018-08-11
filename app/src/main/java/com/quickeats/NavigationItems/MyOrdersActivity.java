package com.quickeats.NavigationItems;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.quickeats.MvpBaseActivity;
import com.quickeats.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyOrdersActivity extends MvpBaseActivity {
    @BindView(R.id.llitems)
    LinearLayout llitems;
    @Override
    public int getLayout() {
        return R.layout.myordersactivity;
    }

    @Override
    protected Object setupActivityComponent() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        for(int i=0;i<5;i++) {
            View view = getLayoutInflater().inflate(R.layout.ordersummaryitem, null);
            llitems.addView(view);
        }

    }
    @OnClick(R.id.imgback)
    void backButtonImpl(){
        finish();
    }

    @OnClick(R.id.txtordertracker)
    void ImplOrderTracker(){
        startActivity(new Intent(this,OrderHistory.class));
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
