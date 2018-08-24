package com.quickeats;

import com.quickeats.Network.APIS;
import com.quickeats.di.DaggerQuickEatComponent;
import com.quickeats.di.QuickEatComponent;
import com.quickeats.di.QuickEatsAppModule;
import com.quickeats.shared.NetworkModule;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Rajesh kumar on 17-07-2018.
 */

public class QuickEats extends QuickEatsBase {
    @Override
    public void onCreate() {
        super.onCreate();

        // initalize Calligraphy
        CalligraphyConfig.initDefault(
                new CalligraphyConfig.Builder()
                        .setDefaultFontPath("SourceSansPro-Regular.ttf")
                        .setFontAttrId(R.attr.fontPath)
                        .build()
        );
    }

    @Override
    protected QuickEatComponent createApplicationComponent() {
        return DaggerQuickEatComponent.builder()
                .quickEatsAppModule(new QuickEatsAppModule(this))
                .networkModule(new NetworkModule(APIS.BASEURL))
                .build();
    }
}
