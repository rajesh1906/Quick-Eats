package com.quickeats.NavigationItems;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.quickeats.MvpBaseActivity;
import com.quickeats.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FavoretiesActivity extends MvpBaseActivity {



    @BindView(R.id.imgback)
    ImageView imgback;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;

    @Override
    public int getLayout() {
        return R.layout.favoretiesacctivity;
    }

    @Override
    protected Object setupActivityComponent() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setAdapter(new FavoretiesItem());
    }



    @OnClick(R.id.imgback)
    public void backButtonImpl(){
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
}
