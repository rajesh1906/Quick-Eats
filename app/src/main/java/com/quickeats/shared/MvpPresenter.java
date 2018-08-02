package com.quickeats.shared;

import android.app.Activity;
import android.os.Bundle;

public interface MvpPresenter<V> {
    Activity getActivity();
    V getView();
    void setView(V view);
    void onCreate(Bundle savedInstanceState);

    void onTakeView(V view);
    void onDestroyView(V view);

}
