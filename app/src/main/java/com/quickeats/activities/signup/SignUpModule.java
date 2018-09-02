package com.quickeats.activities.signup;

import com.quickeats.Network.ConnectNetwork;

import dagger.Module;
import dagger.Provides;

@Module
public class SignUpModule {

    @Provides
    SignUpPresenter getPresenter(ConnectNetwork connectNetwork){
        return new SignUpPresneterImpl(connectNetwork);
    }
}
