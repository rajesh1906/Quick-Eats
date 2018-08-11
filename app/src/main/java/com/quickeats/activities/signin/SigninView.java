package com.quickeats.activities.signin;

import android.support.annotation.StringRes;

import com.quickeats.shared.MvpView;

public interface SigninView extends MvpView<SigninPresenter> {
    void showErrorMessage(@StringRes final int errorMessage);
    void showEmailFieldError(boolean show);
}
