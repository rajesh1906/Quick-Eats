package com.quickeats.shared;

import android.app.Activity;

public interface MvpView<P> {
    P getPresenter();
    Activity getActivityFromView();
    void showProgressDialog();
    void hideProgressDialog();
    void setupPresenter(P presenter);
    String getViewIdentity();


}
