package com.quickeats.Network;

import com.quickeats.shared.NetworkModule;
import com.quickeats.shared.NetworkModule_ProvideRetrofitFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Provides;

@Singleton
public class ConnectNetworkImpl implements ConnectNetwork {
    @Inject
    @Override
    public NetworkModule_ProvideRetrofitFactory bridge() {
        return (NetworkModule_ProvideRetrofitFactory) (NetworkModule_ProvideRetrofitFactory.create(new NetworkModule(APIS.BASEURL)));
    }
}
