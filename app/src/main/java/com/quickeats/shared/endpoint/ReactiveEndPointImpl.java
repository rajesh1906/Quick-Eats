package com.quickeats.shared.endpoint;

import android.util.Log;

import com.quickeats.shared.NetworkModule_ProvideRetrofitFactory;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

@Singleton
public class ReactiveEndPointImpl implements  ReactivePointService {

    String mAction;
    Map<String,String> mParams;
    @Inject
    public ReactiveEndPointImpl(String action, Map<String,String> params){
        this.mAction = action;
        this.mParams = params;
    }

    @Inject
    @Override
    public Observable<String> getEndPoint(NetworkModule_ProvideRetrofitFactory provideRetrofitFactory) {
        Log.e("provideRetrofitFactory","<><"+provideRetrofitFactory);
     return   io.reactivex.Observable.just(provideRetrofitFactory.get().getApiService())
                .subscribeOn(Schedulers.computation())
                .flatMap(s->{
                    return s.getData(mAction,mParams).subscribeOn(Schedulers.io());
                });
    }
}
