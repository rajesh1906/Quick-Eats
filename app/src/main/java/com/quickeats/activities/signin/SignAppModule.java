package com.quickeats.activities.signin;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

@Module
public class SignAppModule {

    @Provides
    SigninPresenter getSigninPresenter(){
        return new SigninPresenterImpl();
    }
}
