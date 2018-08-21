package com.quickeats.utils;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CommonValidationImpl implements CommonValidaton {
    private static String EMAIL_PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    public CommonValidationImpl(){

    }

    @Inject
    @Override
    public boolean emailValidation(String email) {
        boolean  validation = true;

        if (email.matches(EMAIL_PATTERN) && email.length() > 0)
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
