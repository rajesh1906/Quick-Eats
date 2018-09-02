package com.quickeats.dashboard.fragments.booking;

import android.os.Bundle;
import android.util.Log;

import com.quickeats.shared.MvpBasePresenter;

/**
 * Created by Rajesh kumar on 02-09-2018.
 */

public class EatsInPresenterImpl extends MvpBasePresenter<EatsInView> implements EatsInPresnter {
    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onTakeView(EatsInView view) {

    }

    @Override
    public void onDestroyView(EatsInView view) {

    }

    @Override
    public void loadDefalutItems() {
        Log.e("load items is ","<><><");
    }
}
