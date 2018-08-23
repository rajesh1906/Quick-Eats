package com.quickeats.activities.forgotpassword;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.quickeats.BaseActivity;
import com.quickeats.shared.MvpBasePresenter;
import com.quickeats.shared.error.ErrorHandler;
import com.quickeats.shared.error.RetrofitException;
import com.quickeats.utils.CommonValidations;

public class ForgotPresenterImpl extends MvpBasePresenter<ForgotView> implements ForgotPresenter {

    Object object;
    @Override
    public void handleForgotPasswordRequest(String email) {

//        Toast.makeText(getActivity(),"handle content ",Toast.LENGTH_SHORT).show();
        if(BaseActivity.haveNetworkConnection(getActivity())) {
            if(checkValidation(email)){
                Toast.makeText(getActivity(),"Password sent to Email Successful",Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        }else{
            callback.onNetworkError();
        }
    }

    @Override
    public void setValidatoinInjection(Object obj) {
        object = obj;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onTakeView(ForgotView view) {

    }

    @Override
    public void onDestroyView(ForgotView view) {

    }


    private boolean checkValidation(String email){
        boolean  validation=true;
        if (!((CommonValidations)object).emailValidation(email)) {
            validation = false;
            getView().showEmailFieldError();
        }
        return validation;
    }
    @SuppressLint("ResourceType")
    ErrorHandler.ErrorHandlerCallback callback = new ErrorHandler.ErrorHandlerCallback() {
        @Override
        public void onHttpError(RetrofitException e) {

        }
        @Override
        public void onNetworkError() {
            getView().showErrorMessage(2);
        }

        @Override
        public void onServerError(String errorMessage) {
            getView().showErrorMessage(3);
        }

        @Override
        public void onUnrecoverableError(Throwable throwable) {


        }
    };
}
