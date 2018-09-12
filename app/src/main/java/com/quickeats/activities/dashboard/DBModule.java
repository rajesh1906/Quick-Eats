package com.quickeats.activities.dashboard;

import dagger.Module;
import dagger.Provides;

@Module
public class DBModule {
    @Provides
    DBPresenter getPresenter(){
        return new DBImpl();
    }
}
