package com.quickeats.menu;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.quickeats.MvpBaseActivity;
import com.quickeats.R;

public class PaymentActivity extends MvpBaseActivity {
    @Override
    public int getLayout() {
        return R.layout.activitypayment;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
