package com.quickeats.shared;

import android.app.Activity;

public interface MvpPresenter<V> {
    Activity getActivity();
    V getView();
    void setView(V view);
}
