package com.quickeats.activities.signin;

import com.quickeats.utils.CommonValidations;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

@Module
public class SignAppModule {

    @Provides
    SigninPresenter getSigninPresenter() {
        return new SigninPresenterImpl();
    }

    @Provides
    CommonValidations getValiadtion() {
        return new CommonValidations();
    }
}
