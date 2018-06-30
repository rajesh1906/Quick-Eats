package com.quickeats.checkout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
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
    String[] mPaymentItems = {"Bill","Total Item","Tax","Delivery Charges","To Pay"};
    String[] mPaymentAmount = {"","100.00","10.00","5.00","115.00"};
    @BindView(R.id.llamount)
    LinearLayout mAmount;
    @BindView(R.id.llpaymenttype)
    LinearLayout llpaymenttype;
    Integer [] mPaymentTypes={R.drawable.creditcard,R.drawable.paytm,R.drawable.paypal,R.drawable.creditcard};
    String  [] mPaymentText={"XXX456","","","Add Credit or Debit Card"};

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

        for (int i=0;i<mPaymentItems.length;i++){
            View child = getLayoutInflater().inflate(R.layout.checkoutpaymentitem, null);
            TextView txtItemName = child.findViewById(R.id.txtitemname);
            TextView txtItemPrice = child.findViewById(R.id.txtitemprice);
            View line = child.findViewById(R.id.viewline);
            txtItemName.setText(mPaymentItems[i]);
            txtItemPrice.setText(mPaymentAmount[i]);
            if(i==0||i==mPaymentItems.length-1){
                line.setVisibility(View.INVISIBLE);
            }else{
                line.setVisibility(View.VISIBLE);
            }

            mAmount.addView(child);
            }

        for(int i=0;i<mPaymentTypes.length;i++){
            View child = getLayoutInflater().inflate(R.layout.paymentoptionsitem, null);
            ImageView img = child.findViewById(R.id.img);
            TextView txtoption = child.findViewById(R.id.txtoption);
            ImageView imgtick = child.findViewById(R.id.imgtick);
            img.setBackgroundResource(mPaymentTypes[i]);
            txtoption.setText(mPaymentText[i]);
            if (i==0){
                imgtick.setVisibility(View.VISIBLE);
            }else{
                imgtick.setVisibility(View.GONE);
            }

            llpaymenttype.addView(child);
        }


    }
}
