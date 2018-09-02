package com.quickeats.dashboard;

import com.quickeats.shared.MvpPresenter;
import com.quickeats.utils.DialogManage;

/**
 * Created by Rajesh kumar on 22-06-2018.
 */

public interface DashboardPresenter extends MvpPresenter<DashboardView>, DialogManage {
    void initialSetUp();
}
