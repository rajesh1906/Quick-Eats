package com.quickeats.activities.forgotpassword;

import com.quickeats.shared.MvpPresenter;

public interface ForgotPresenter extends MvpPresenter<ForgotView> {
     void handleForgotPasswordRequest(String email);
     void setValidatoinInjection(Object obj);
}
