package com.quickeats.activities.cart;

import android.os.Bundle;

import com.quickeats.MvpBaseActivity;
import com.quickeats.R;
import com.quickeats.di.CartComponent;

/**
 * Created by Rajesh kumar on 13-09-2018.
 */

public class CartActvity extends MvpBaseActivity<CartPresenter,CartComponent> implements CartView{
    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgerssBar() {

    }

    @Override
    public void showErrorMessage() {

    }

    @Override
    public int getLayout() {
        return R.layout.commonrecyclerview;
    }

    @Override
    protected void onCreateAfterSetContentView(Bundle savedInstanceState) {
        super.onCreateAfterSetContentView(savedInstanceState);
        getPresenter().loadItems();
    }

    @Override
    protected CartComponent setupActivityComponent() {
        return getApplicationComponent().plus(new CartModule() );
    }
}
