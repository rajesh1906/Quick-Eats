package com.quickeats.di;

import com.quickeats.activities.cart.CartActvity;
import com.quickeats.activities.cart.CartModule;

import dagger.Subcomponent;

/**
 * Created by Rajesh kumar on 13-09-2018.
 */
@Subcomponent(modules = CartModule.class)
public interface CartComponent {
    void inject(CartActvity cartActvity);
}
