package com.quickeats.checkout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quickeats.MvpBaseActivity;
import com.quickeats.R;

import java.lang.reflect.Array;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rajesh kumar on 30-06-2018.
 */

public class CheckoutActivity extends MvpBaseActivity{
    @BindView(R.id.llcheckoutitems)
    LinearLayout llcheckoutitems;
    String[] mCheckoutItems = {"Chhole Bhature","Veg Recipes"};
    @Override
    public int getLayout() {
        return R.layout.checkoutactivity;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        //static items adding
        for(String x:mCheckoutItems){
            View child = getLayoutInflater().inflate(R.layout.checkoutitemadd, null);
            TextView txtItemName = child.findViewById(R.id.txt);
            txtItemName.setText(x);
            llcheckoutitems.addView(child);
        }
    }
}
