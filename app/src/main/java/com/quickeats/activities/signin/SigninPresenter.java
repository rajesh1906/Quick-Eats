package com.quickeats.activities.signin;

import com.quickeats.shared.MvpPresenter;

public interface SigninPresenter extends MvpPresenter<SigninView> {

    void handleLoginRequest(String email, String password);
    void handleLaunchSignUpscreen();
    void handleLaunchForgetScreen();
}
