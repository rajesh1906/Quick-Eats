package com.quickeats.activities.signup;

import dagger.Module;
import dagger.Provides;

@Module
public class SignUpModule {

    @Provides
    SignUpPresenter getPresenter(){
        return new SignUpPresneterImpl();
    }
}
