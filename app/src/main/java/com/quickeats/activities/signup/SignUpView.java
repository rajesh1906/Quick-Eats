package com.quickeats.activities.signup;

import android.support.annotation.StringRes;

import com.quickeats.shared.MvpView;

public interface SignUpView extends MvpView<SignUpPresenter> {
    void showErrorMessage(@StringRes final int errorMessage);
    void showEmailFieldError(boolean show);
    void showPhoneNumberError();
}
