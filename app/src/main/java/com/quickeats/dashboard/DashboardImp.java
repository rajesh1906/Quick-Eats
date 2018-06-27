package com.quickeats.dashboard;

import com.quickeats.utils.DialogManage;

/**
 * Created by Rajesh kumar on 22-06-2018.
 */

public class DashboardImp implements DashboardPresenter {
    DialogManage dialogManage;

    public DashboardImp(DialogManage dialogManage){
        this.dialogManage = dialogManage;
    }

    @Override
    public void showProgressDialog() {
        dialogManage.showProgressDialog();
    }

    @Override
    public void hideProgressDialog() {
        dialogManage.hideProgressDialog();
    }

    @Override
    public void initialSetUp() {
        ((DashboardActivity)dialogManage).initilizeViews();
    }
}
