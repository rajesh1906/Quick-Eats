package com.quickeats.activities.cart;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.quickeats.Network.APIS;
import com.quickeats.Network.ConnectNetwork;
import com.quickeats.shared.MvpBasePresenter;
import com.quickeats.shared.MvpPresenter;
import com.quickeats.shared.endpoint.ReactiveEndPoint;

import java.util.HashMap;

/**
 * Created by Rajesh kumar on 13-09-2018.
 */

public class CartPresenterImpl extends MvpBasePresenter<CartView> implements CartPresenter {

    ConnectNetwork connectNetwork;

    CartPresenterImpl(ConnectNetwork connectNetwork){
        this.connectNetwork = connectNetwork;
    }



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
//        Toast.makeText(getActivity(),"Under construction",Toast.LENGTH_LONG).show();
        fetchData();
    }




    private void fetchData(){
        getView().showProgressBar();
        if( new ReactiveEndPoint(getActivity(),APIS.GETCART_DETAILS,getParams()).getEndPoints(connectNetwork)){
            getView().hideProgerssBar();
        }else{
            getView().hideProgerssBar();
//                  ErrorHandler.handle();
        }
    }
    private HashMap<String ,String > getParams(){
        HashMap<String ,String > params = new HashMap<>();
        params.put("loginId","1");
        params.put("InputType","M");
        return params;
    }
}
