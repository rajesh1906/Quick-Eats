package com.quickeats.shared.endpoint;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.quickeats.Network.ConnectNetwork;
import com.quickeats.Network.ConnectNetworkImpl;
import com.quickeats.shared.CallbackService;
import com.quickeats.shared.NetworkModule_ProvideRetrofitFactory;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

@Singleton
public class ReactiveEndPoint  {
    Context context;
    String action;
    Map<String, String> mParams;
    ReactivePointService reactivePointService;
    boolean status=false;


    @Inject
    public ReactiveEndPoint(Context context, String action, Map<String, String> mParams) {
        this.context = context;
        this.action = action;
        this.mParams = mParams;
        reactivePointService = new ReactiveEndPointImpl(action, mParams);
    }

    public boolean getEndPoints(ConnectNetwork connectNetwork) {

        reactivePointService.getEndPoint(connectNetwork.bridge())
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e("subcribe is", "<><>");
                    }

                    @Override
                    public void onNext(String  value) {
                        status = true;
                        Log.e("onNext is", "<><>" + value);
                        ((CallbackService) context).callBackActivity(value);
//                        ((View) context).hi
                    }

                    @Override
                    public void onError(Throwable e) {
                        status = false;
                        Log.e("onError is", "<><>");
                    }

                    @Override
                    public void onComplete() {
                        status = true;
                        Log.e("onComplete is", "<><>");
                    }
                });

        return status;
    }



}
