package com.quickeats.activities.signin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.quickeats.BaseActivity;
import com.quickeats.Network.APIS;
import com.quickeats.Network.ApiService;
import com.quickeats.Network.ConnectNetwork;
import com.quickeats.activities.forgotpassword.ForgotPasswordActivity;
import com.quickeats.activities.signup.SignUpActivity;
import com.quickeats.shared.CallbackService;
import com.quickeats.shared.MvpBasePresenter;
import com.quickeats.shared.NetworkModule;
import com.quickeats.shared.NetworkModule_ProvideRetrofitFactory;
import com.quickeats.shared.endpoint.ReactiveEndPoint;
import com.quickeats.shared.error.ErrorHandler;
import com.quickeats.shared.error.RetrofitException;
import com.quickeats.utils.CommonValidations;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SigninPresenterImpl extends MvpBasePresenter<SigninView> implements SigninPresenter {


    Object validations;

    NetworkModule_ProvideRetrofitFactory networkModule_provideRetrofitFactory;
//    Object sharedPreferences;
    ApiService apiService;
    ConnectNetwork getConnectNetwork;

    public SigninPresenterImpl(ConnectNetwork connectNetwork){
        this.getConnectNetwork = connectNetwork;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onTakeView(SigninView view) {

    }

    @Override
    public void onDestroyView(SigninView view) {

    }

    @Override
    public void handleLoginRequest(String email, String password, Object validation) {
        this.validations = validation;
        Log.e("coming to handler", "<>><"+getConnectNetwork);
//        Log.e("shared preference is", "<>><"+sharedPreferences);

        if(BaseActivity.haveNetworkConnection(getActivity())) {
            if (checkValidation(email, password)) {
//                apiCall(email, password);
                getView().showProgressDialog();
              if( new ReactiveEndPoint(getActivity(),APIS.SIGNIN,getParams(email,password)).getEndPoints(getConnectNetwork)){
                  getView().hideProgressDialog();
              }else{
                  getView().hideProgressDialog();
//                  ErrorHandler.handle();
              }

//                subcrition(email,password);
            }
        }else{
            callback.onNetworkError();
        }
    }

    @Override
    public void handleLaunchSignUpscreen() {
        getActivity().startActivity(new Intent(getActivity(), SignUpActivity.class));
    }

    @Override
    public void handleLaunchForgetScreen() {
        getActivity().startActivity(new Intent(getActivity(), ForgotPasswordActivity.class));
    }

    @Override
    public void setInjection(HashMap<String,Object> objlist) {
    }

    @SuppressLint("ResourceType")
    private boolean checkValidation(String email, String password) {
        boolean validation = true;

        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
            validation = false;
            getView().showErrorMessage(1);
        }else if(email.contains("@")){
            if (!((CommonValidations)validations).emailValidation(email)) {
                validation = false;
                getView().showEmailFieldError(false);
            }else  if (TextUtils.isEmpty(password)) {
                validation = false;
                getView().showErrorMessage(2);
            }
        }
        else if (TextUtils.isEmpty(password)) {
            validation = false;
            getView().showErrorMessage(2);
        }
        return validation;
    }

    private void apiCall(String email, String password) {

        getView().showProgressDialog();
        networkModule_provideRetrofitFactory = (NetworkModule_ProvideRetrofitFactory) (NetworkModule_ProvideRetrofitFactory.create(new NetworkModule(APIS.BASEURL)));
        (networkModule_provideRetrofitFactory
                .get()
                .getApiService()
                .getApiResultCity(getParams(email, password)
                        .get("action"), getParams(email, password))).
                enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        getView().hideProgressDialog();
                        Log.e("success", "<><" + response.body());
                        ((CallbackService) getActivity()).callBackActivity(response.body());

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.e("fail", "<><" + t.getMessage());
                    }
                });
    }

    private Map<String, String> getParams(String email, String password) {
        Map<String, String> params = new HashMap<>();
        params.put("username", email);
        params.put("password", password);
        params.put("action", APIS.SIGNIN);

        return params;
    }
    @SuppressLint("ResourceType")
    ErrorHandler.ErrorHandlerCallback callback = new ErrorHandler.ErrorHandlerCallback() {
        @Override
        public void onHttpError(RetrofitException e) {

        }
        @Override
        public void onNetworkError() {
            getView().showErrorMessage(3);
        }

        @Override
        public void onServerError(String errorMessage) {
            getView().showErrorMessage(4);
        }

        @Override
        public void onUnrecoverableError(Throwable throwable) {


        }
    };

//    @SuppressLint("CheckResult")
//    private void subcrition(String email, String password){
//        apiService.getDataNew(APIS.SIGNIN,getParams(email,password))
//                .compose(DefaultRxSchedulersApplier.applySchedulers())
//                .subscribe(result->{
//                   getView().hideProgressDialog();
//                    handlingResult(result);
//                },throwable -> ErrorHandler.handle(throwable,callback));
//    }

    private void handlingResult(Object result) {
        Log.e("result is ","<><>"+result);
    }


}
