package com.quickeats;

import android.app.Application;

import com.quickeats.utils.CommonValidations;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class QuickEatsAppModule {
    private final Application mApplication;
    public QuickEatsAppModule(Application application){
        mApplication = application;
    }

    @Provides
    @Singleton
    public Application provideApplication(){
        return mApplication;
    }





}
