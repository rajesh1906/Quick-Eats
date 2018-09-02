package com.quickeats.dashboard;

import android.app.Activity;
import android.os.Bundle;

import com.quickeats.shared.MvpBasePresenter;
import com.quickeats.shared.MvpPresenter;
import com.quickeats.utils.DialogManage;

/**
 * Created by Rajesh kumar on 22-06-2018.
 */

public class DashboardImp extends MvpBasePresenter<DashboardView> implements DashboardPresenter {


//    @Override
//    public void showProgressDialog() {
//        dialogManage.showProgressDialog();
//    }
//
//    @Override
//    public void hideProgressDialog() {
//        dialogManage.hideProgressDialog();
//    }
//
//    @Override
//    public void initialSetUp() {
//        ((DashboardActivity)dialogManage).initilizeViews();
//    }

    @Override
    public Activity getActivity() {
        return null;
    }

    @Override
    public DashboardView getView() {
        return null;
    }

    @Override
    public void setView(DashboardView view) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onTakeView(DashboardView view) {

    }

    @Override
    public void onDestroyView(DashboardView view) {

    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void initialSetUp() {

    }
}
