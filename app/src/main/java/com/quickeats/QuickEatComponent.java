package com.quickeats;

import com.quickeats.activities.signin.SignAppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {QuickEatsAppModule.class, SignAppModule.class})
public interface QuickEatComponent {
    SignAppModule inject(SignAppModule signInActivity);


}
