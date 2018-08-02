package com.quickeats.shared;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.concurrent.atomic.AtomicInteger;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class MvpBaseFragment<P extends MvpPresenter> extends Fragment implements MvpView<P>  {

    private static AtomicInteger sAtomicInteger = new AtomicInteger();

    private String mViewIdentity = String.format("fragment-%d", sAtomicInteger.incrementAndGet());
    private Unbinder mButterknifeUnbinder;
    private P mPresenter;
    protected abstract int getFragmentViewId();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        try {

            int viewId = getFragmentViewId();
            if (viewId != 0) {

                if (mPresenter != null) {
                    Log.v(logTag(), String.format("%s", getPresenter().toString()));
                    Log.v(logTag(), "Trigger presenter onCreate()");
                    mPresenter.onCreate(savedInstanceState);
                }

                Log.v(logTag(), "Inflating fragment layout");
                View view = inflater.inflate(viewId, container, false);

                Log.v(logTag(), "Binding fragment view");
                mButterknifeUnbinder = ButterKnife.bind(this, view);

                Log.v(logTag(), "Calling onFragmentViewCreated()");
                onFragmentViewCreated(view, savedInstanceState);

                if (mPresenter != null) {
                    Log.v(logTag(), "Trigger presenter onTakeView()");
                    mPresenter.onTakeView(this);
                }

                return view;
            } else {
                Log.e(logTag(), "Invalid view for fragment");
                throw new RuntimeException("Invalid content view for activity");
            }
        } catch (Exception e) {
            throw new RuntimeException("Invalid content view for activity");
        }

    }
    @Override
    public final void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Do not allow overriding this methods. Use onFragmentViewCreated() instead
    }

    protected void onFragmentViewCreated(View view, Bundle savedInstanceState) {
        // Do nothing by default
    }
    @Override
    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public Activity getActivityFromView() {
        return getActivity();
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    public String logTag() {
        return String.format("%s [%s]", getClass().getSimpleName(), mViewIdentity);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onDestroyView() {
        Log.v(logTag(), "onDestroyView()");
        Log.v(logTag(), "Unbinding fragment view");
        if (mButterknifeUnbinder != null) {
            mButterknifeUnbinder.unbind();
        }

        if (mPresenter != null) {
            Log.v(logTag(), "Trigger presenter onDestroyView()");
            mPresenter.onDestroyView(this);
        }

        hideProgressDialog();

        Log.v(logTag(), "super.onDestroyView()");
        super.onDestroyView();
    }


}
