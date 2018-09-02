package com.quickeats.dashboard.fragments.booking;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Rajesh kumar on 02-09-2018.
 */

@Module
public class BookingModule {

    @Provides
    EatsInPresnter  getEatsPresenter(){
        return new EatsInPresenterImpl();
    }
}
