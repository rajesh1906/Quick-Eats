package com.quickeats.dashboard;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Rajesh kumar on 02-09-2018.
 */
@Module
public class DashboardModule {
    @Provides
    DashboardPresenter getDashboard()
    {
        return new DashboardImp();
    }
}
