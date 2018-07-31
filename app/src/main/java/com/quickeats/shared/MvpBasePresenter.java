package com.quickeats.shared;

import android.app.Activity;

import java.lang.ref.WeakReference;

public abstract class MvpBasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private WeakReference<V> mView;
    @Override
    public V getView() {
        return (mView != null) ? mView.get() : null;
    }
    @Override
    public void setView(V view) {
        mView = new WeakReference<>(view);
    }

    @Override
    public Activity getActivity() {
        return getView().getActivityFromView();
    }
}
