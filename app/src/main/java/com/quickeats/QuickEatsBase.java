package com.quickeats;

import android.app.Application;

import com.quickeats.di.QuickEatComponent;

public abstract class QuickEatsBase extends Application {
    private    QuickEatComponent quickEatComponent;
    private static QuickEatsBase sInstance;
    protected abstract QuickEatComponent createApplicationComponent();


    private static QuickEatsBase get(){
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        configureComponent();
    }

    public QuickEatComponent getQuickEatComponent() {
        return quickEatComponent;
    }

    private void configureComponent(){
        quickEatComponent = createApplicationComponent();
    }
}
