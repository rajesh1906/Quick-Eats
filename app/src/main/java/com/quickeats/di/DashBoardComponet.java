package com.quickeats.di;

import com.quickeats.BaseActivity;
import com.quickeats.dashboard.DashboardModule;
import com.quickeats.dashboard.fragments.booking.BookingModule;

import dagger.Subcomponent;

/**
 * Created by Rajesh kumar on 02-09-2018.
 */

@Subcomponent(modules = {DashboardModule.class, BookingModule.class})
public interface DashBoardComponet {
    void inject(BaseActivity dashboardActivity);
}
