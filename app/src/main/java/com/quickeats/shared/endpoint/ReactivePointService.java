package com.quickeats.shared.endpoint;

import com.quickeats.shared.NetworkModule_ProvideRetrofitFactory;

import io.reactivex.Observable;

public interface ReactivePointService {
    Observable<String> getEndPoint(NetworkModule_ProvideRetrofitFactory provideRetrofitFactory);
}
