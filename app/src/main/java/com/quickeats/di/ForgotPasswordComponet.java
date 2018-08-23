package com.quickeats.di;

import com.quickeats.activities.forgotpassword.ForgotPasswordActivity;
import com.quickeats.activities.forgotpassword.ForgotPasswordModule;

import dagger.Subcomponent;

@Subcomponent(modules = ForgotPasswordModule.class)
public interface ForgotPasswordComponet {
    void inject(ForgotPasswordActivity forgotPasswordActivity);
}
