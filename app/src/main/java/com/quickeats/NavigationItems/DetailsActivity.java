package com.quickeats.NavigationItems;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.quickeats.MvpBaseActivity;
import com.quickeats.R;

public class DetailsActivity extends MvpBaseActivity {
    @Override
    public int getLayout() {
        return R.layout.details;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}
