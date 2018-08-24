package com.quickeats.di;


import com.quickeats.activities.signin.SignAppModule;
import com.quickeats.activities.signin.SignInActivity;

import dagger.Subcomponent;

@Subcomponent(modules = SignAppModule.class)
public interface AuthenticateComponent {
    void inject(SignInActivity activity);

}
