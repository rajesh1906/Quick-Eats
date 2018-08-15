package com.quickeats;

import com.quickeats.activities.signin.SignAppModule;
import com.quickeats.shared.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {QuickEatsAppModule.class, NetworkModule.class})
public interface QuickEatComponent {
    AuthenticateComponent plus(SignAppModule signAppModule);
}
