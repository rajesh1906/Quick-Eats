package com.quickeats.di;

import com.quickeats.activities.signup.SignUpActivity;
import com.quickeats.activities.signup.SignUpModule;

import dagger.Subcomponent;

@Subcomponent(modules = SignUpModule.class)
public interface SignupComponent {
    void inject(SignUpActivity activity);
}
