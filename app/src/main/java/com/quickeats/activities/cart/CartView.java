package com.quickeats.activities.cart;

import com.quickeats.shared.MvpView;

/**
 * Created by Rajesh kumar on 13-09-2018.
 */

public interface CartView extends MvpView<CartPresenter> {
    void showProgressBar();
    void hideProgerssBar();
    void showErrorMessage();
}
