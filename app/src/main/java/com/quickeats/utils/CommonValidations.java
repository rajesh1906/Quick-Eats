package com.quickeats.utils;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Provides;

@Singleton
public class CommonValidations {

    private static String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Inject
    public boolean emailValidation(String text){
    boolean  validation = true;

        if (text.matches(EMAIL_PATTERN) && text.length() > 0)
        {
            validation = true;
        }
        else
        {
            validation = false;
        }

    return validation;
    }
}
