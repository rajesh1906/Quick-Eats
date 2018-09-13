package com.quickeats.activities.cart;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Rajesh kumar on 13-09-2018.
 */

@Module
public class CartModule {

    @Provides
    CartPresenter getCart(){
        return new CartPresenterImpl();
    }
}
