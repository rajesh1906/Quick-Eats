package com.quickeats.di;

import com.quickeats.activities.dashboard.DBActivity;
import com.quickeats.activities.dashboard.DBModule;
import com.quickeats.dashboard.fragments.booking.BookingModule;
import com.quickeats.dashboard.fragments.booking.EatsInFragment;

import dagger.Subcomponent;

@Subcomponent(modules ={ DBModule.class, BookingModule.class})
public interface DBComponet {
//    void inject(BaseActivity dbActivity);
    void inject(DBActivity dbActivity);
    void inject(EatsInFragment eatsInFragment);
}
