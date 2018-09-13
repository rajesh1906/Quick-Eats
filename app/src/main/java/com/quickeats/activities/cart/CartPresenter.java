package com.quickeats.activities.cart;

import com.quickeats.shared.MvpBasePresenter;
import com.quickeats.shared.MvpPresenter;

/**
 * Created by Rajesh kumar on 13-09-2018.
 */

public interface CartPresenter extends MvpPresenter<CartView> {
    void loadItems();
}
