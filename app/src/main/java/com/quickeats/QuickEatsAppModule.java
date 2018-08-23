package com.quickeats;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

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

    @Singleton
    @Provides
    public SharedPreferences provideSharedPreferences() {
        return PreferenceManager.getDefaultSharedPreferences(mApplication);
    }



}
