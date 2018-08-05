package com.quickeats.NavigationItems;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.quickeats.MvpBaseActivity;
import com.quickeats.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaymentOptionsActivity extends MvpBaseActivity {




    @Override
    public int getLayout() {
        return  R.layout.activitypayment;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.imgback)
    void backButtonImpl(){
        finish();
    }
    @OnClick(R.id.reladdpayment)
    void AddPaymentOptions(){

    }
}
