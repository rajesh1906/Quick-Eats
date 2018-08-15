package com.quickeats.shared;

import com.quickeats.Network.RetrofitClient;

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

}
