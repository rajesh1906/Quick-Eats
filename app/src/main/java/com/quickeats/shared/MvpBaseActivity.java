package com.quickeats.shared;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class MvpBaseActivity<P extends MvpPresenter, C> extends AppCompatActivity implements MvpView<P> {
    protected abstract int getActivityViewId();

    protected abstract C setupActivityComponent();
    private static AtomicInteger sAtomicInteger = new AtomicInteger();

    private String mViewIdentity = String.format("activity-%d", sAtomicInteger.incrementAndGet());

    private C mActivityComponent;
    private P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onResume() {
        Log.v(logTag(), "onResume()");
        super.onResume();

        if (mPresenter != null) {
            Log.v(logTag(), "Trigger presenter onTakeView()");
            mPresenter.onTakeView(this);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onPause() {
        Log.v(logTag(), "onPause()");
        super.onPause();

        if (mPresenter != null) {
            Log.v(logTag(), "Trigger presenter onDestroyView()");
            mPresenter.onDestroyView(this);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(logTag(), "onStart()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(logTag(), "onStop()");
    }
    protected String logTag() {
        return String.format("%s [%s]", getClass().getSimpleName(), mViewIdentity);
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public String getViewIdentity() {
        return mViewIdentity;
    }

    @Override
    public void setupPresenter(P presenter) {
        mPresenter = presenter;
        if (mPresenter != null) {
            mPresenter.setView(this);
        }
    }
}
