package com.quickeats.dashboard.model;

import android.os.Bundle;

import com.quickeats.dashboard.DashboardActivity;
import com.quickeats.shared.MvpBasePresenter;
import com.quickeats.shared.MvpPresenter;

public class DashBoadImpl extends MvpBasePresenter<DashBoardView> implements DashBoardPresenter{

    DashboardActivity dashboardActivity;
    public DashBoadImpl(DashboardActivity dashboardActivity){
        this.dashboardActivity = dashboardActivity;
    }
    @Override
    public void initialSetUp() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onTakeView(DashBoardView view) {

    }

    @Override
    public void onDestroyView(DashBoardView view) {

    }
}
