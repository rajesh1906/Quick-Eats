package com.quickeats.activities.signup;

import com.quickeats.shared.MvpPresenter;

public interface SignUpPresenter extends MvpPresenter<SignUpView> {
    void handleSignUpRequest(String fName,String lName,String phoneNumber,String email);
    void handleLaunchSignInscreen();
}
