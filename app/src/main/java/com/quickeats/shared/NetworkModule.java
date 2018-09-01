package com.quickeats.shared;

import android.content.Context;

import com.quickeats.Network.ConnectNetwork;
import com.quickeats.Network.ConnectNetworkImpl;
import com.quickeats.Network.RetrofitClient;
import com.quickeats.shared.endpoint.ReactiveEndPoint;

import java.util.Map;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class NetworkModule {

    String bseApiurl;
    public NetworkModule(String bseApiurl){
        this.bseApiurl = bseApiurl;
    }

    @Provides
    @Singleton
    public RetrofitClient provideRetrofit() {
        return RetrofitClient.getInstance();
    }


    @Provides
    @Singleton
    public ConnectNetwork networkConnection(){
        return new ConnectNetworkImpl();
    }


}
