package com.quickeats;

import com.quickeats.Network.RetrofitClient;
import com.quickeats.activities.forgotpassword.ForgotPasswordModule;
import com.quickeats.activities.signin.SignAppModule;
import com.quickeats.activities.signup.SignUpModule;
import com.quickeats.di.ForgotPasswordComponet;
import com.quickeats.shared.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {QuickEatsAppModule.class, NetworkModule.class})
public interface QuickEatComponent {
    AuthenticateComponent plus(SignAppModule signAppModule);
    SignupComponent plus(SignUpModule signUpModule);
    ForgotPasswordComponet plus(ForgotPasswordModule forgotPasswordModule);
//    AuthenticateComponent plus(SignUpModule signUpModule);
}
