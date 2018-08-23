package com.quickeats.activities.forgotpassword;

import android.support.annotation.StringRes;

import com.quickeats.shared.MvpView;

public interface ForgotView extends MvpView<ForgotPresenter> {
    void showErrorMessage(@StringRes final int errorMessage);
    void showEmailFieldError();
}
