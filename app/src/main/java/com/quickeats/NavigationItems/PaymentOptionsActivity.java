package com.quickeats.NavigationItems;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.quickeats.MvpBaseActivity;
import com.quickeats.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaymentOptionsActivity extends MvpBaseActivity {




    @Override
    public int getLayout() {
        return  R.layout.activitypayment;
    }

    @Override
    protected Object setupActivityComponent() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.imgback)
    void backButtonImpl(){
        finish();
    }
    @OnClick(R.id.reladdpayment)
    void AddPaymentOptions(){
        startActivity(new Intent(this,AddPaymentOptions.class));
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
