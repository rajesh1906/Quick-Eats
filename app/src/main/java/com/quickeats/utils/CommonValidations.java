package com.quickeats.utils;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Provides;

@Singleton
public class CommonValidations {
    CommonValidaton validaton;

    @Inject
    public CommonValidations() {
        validaton = new CommonValidationImpl();
    }
    @Inject
    public boolean emailValidation(String text) {
        return validaton.emailValidation(text);
    }
}
