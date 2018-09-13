package com.quickeats.activities.cart;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.quickeats.shared.MvpBasePresenter;
import com.quickeats.shared.MvpPresenter;

/**
 * Created by Rajesh kumar on 13-09-2018.
 */

public class CartPresenterImpl extends MvpBasePresenter<CartView> implements CartPresenter {
    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onTakeView(CartView view) {

    }

    @Override
    public void onDestroyView(CartView view) {

    }

    @Override
    public void loadItems() {
    Log.e("load items is ","<><>");
        Toast.makeText(getActivity(),"Under construction",Toast.LENGTH_LONG).show();
    }
}
