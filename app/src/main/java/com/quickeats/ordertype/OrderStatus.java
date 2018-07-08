package com.quickeats.ordertype;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.quickeats.MvpBaseActivity;
import com.quickeats.R;

/**
 * Created by Rajesh kumar on 07-07-2018.
 */

public class OrderStatus extends MvpBaseActivity {
    @Override
    public int getLayout() {
        return R.layout.orderstatus;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
