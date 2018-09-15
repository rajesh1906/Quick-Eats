package com.quickeats.activities.cart;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.quickeats.MvpBaseActivity;
import com.quickeats.R;
import com.quickeats.activities.cart.model.CartDetailsRoot;
import com.quickeats.di.CartComponent;
import com.quickeats.shared.CallbackService;
import com.quickeats.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Rajesh kumar on 13-09-2018.
 */

public class CartActvity extends MvpBaseActivity<CartPresenter,CartComponent> implements CartView,CallbackService,PriceSettings {


    ProgressDialog progressDialog;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.txt_no_res)
    TextView txt_no_res;

    HashMap<Integer,String > total_values = new HashMap<>();
    CartAdapter cartAdapter;

    @Override
    public void showProgressBar() {
        progressDialog.show();
    }

    @Override
    public void hideProgerssBar() {
        progressDialog.dismiss();
    }

    @Override
    public void showErrorMessage() {

    }

    @Override
    public int getLayout() {
        return R.layout.cart_view;
    }

    @Override
    protected void onCreateAfterSetContentView(Bundle savedInstanceState) {
        super.onCreateAfterSetContentView(savedInstanceState);
        progressDialog = new ProgressDialog(this);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        getPresenter().loadItems();
    }

    @Override
    protected CartComponent setupActivityComponent() {
        return getApplicationComponent().plus(new CartModule() );
    }

    @OnClick(R.id.ll_back)
    void backButtonImpl(){
        finish();
    }

    @Override
    public void callBackActivity(String response) {
        Log.e("respone is ","<><"+response);
        try{
            CartDetailsRoot root = new Gson().fromJson(response,CartDetailsRoot.class);
            if(!root.getError()){
                txt_no_res.setVisibility(View.GONE);
                 cartAdapter = new CartAdapter(CartActvity.this,root.getGetingCartData().get(0).getRestaurantlist());
                recyclerview.setAdapter(cartAdapter);
            }else{
                txt_no_res.setText(getString(R.string.no_items_found));
                txt_no_res.setVisibility(View.VISIBLE);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }


    @Override
    public void setPrice(int pos, String price) {
//        Log.e("sum of price is ","<>pos<"+pos+" price "+price);
        total_values.put(pos,price);
    }

    @Override
    public String getPrice() {
        ArrayList<Integer> values = new ArrayList<>();
//        Log.e(" get sum value is ","<><>"+total_values.entrySet());
        for(Map.Entry entry:total_values.entrySet()){
            values.add(Integer.parseInt(entry.getValue().toString().replace(Constants.RUPEE,"").replace(",","").replace(".00","")));
        }
        int total=0;
        for(int i=0;i<values.size();i++){
            total = total+values.get(i);
        }
//        Log.e("array list total","<><>"+total);
        try {
            cartAdapter.updateResultent(total);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ""+total;
    }
}
