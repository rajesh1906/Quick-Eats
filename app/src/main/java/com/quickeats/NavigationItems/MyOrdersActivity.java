package com.quickeats.NavigationItems;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.quickeats.MvpBaseActivity;
import com.quickeats.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyOrdersActivity extends MvpBaseActivity {
    @BindView(R.id.llitems)
    LinearLayout llitems;
    @Override
    public int getLayout() {
        return R.layout.myordersactivity;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        for(int i=0;i<5;i++) {
            View view = getLayoutInflater().inflate(R.layout.ordersummaryitem, null);
            llitems.addView(view);
        }

    }
}
