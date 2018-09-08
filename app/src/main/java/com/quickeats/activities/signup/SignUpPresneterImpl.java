package com.quickeats.activities.signup;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.quickeats.BaseActivity;
import com.quickeats.Network.APIS;
import com.quickeats.R;
import com.quickeats.activities.signin.SignInActivity;
import com.quickeats.dashboard.DashboardActivity;
import com.quickeats.shared.CallbackService;
import com.quickeats.shared.MvpBasePresenter;
import com.quickeats.shared.MvpPresenter;
import com.quickeats.shared.NetworkModule;
import com.quickeats.shared.NetworkModule_ProvideRetrofitFactory;
import com.quickeats.shared.error.ErrorHandler;
import com.quickeats.shared.error.RetrofitException;

import java.io.IOException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpPresneterImpl extends MvpBasePresenter<SignUpView> implements SignUpPresenter  {



    NetworkModule_ProvideRetrofitFactory networkModule_provideRetrofitFactory;
    String mFName,mLName,mPhoneNumber,mEmail,password;
    @Override
    public void handleSignUpRequest(String fName, String lName, String phoneNumber, String email,String password) {
        this.mFName = fName;
        this.mLName = lName;
        this.mPhoneNumber = phoneNumber;
        this.mEmail = email;
        this.password = password;
        if(BaseActivity.haveNetworkConnection(getActivity())) {
            if (checkValidation(fName, lName, phoneNumber, email,password)) {
                apiCalling();

            }
        }else{
            callback.onNetworkError();
        }
    }

    @Override
    public void handleLaunchSignInscreen() {

       getActivity(). startActivity(new Intent(getActivity(),SignInActivity.class));
        getActivity().finish();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onTakeView(SignUpView view) {

    }

    @Override
    public void onDestroyView(SignUpView view) {

    }

    @SuppressLint("ResourceType")
    private boolean checkValidation(String fName, String lName, String phoneNumber, String email,String password){
        boolean validation= true;
        if(TextUtils.isEmpty(fName)){
            validation = false;
            getView().showErrorMessage(1);
        } else if (TextUtils.isEmpty(lName)) {
                validation = false;
            getView().showErrorMessage(2);
        }else if(TextUtils.isEmpty(phoneNumber)){
            validation = false;
            getView().showErrorMessage(3);
        }else if(TextUtils.isEmpty(email)) {
            validation = false;
            getView().showErrorMessage(4);
        }else if(TextUtils.isEmpty(password)){
            validation = false;
            getView().showErrorMessage(5);
        }
        return validation;
    }
    private void apiCalling(){
        getView().showProgressDialog();
        networkModule_provideRetrofitFactory = (NetworkModule_ProvideRetrofitFactory) (NetworkModule_ProvideRetrofitFactory.create(new NetworkModule(APIS.BASEURL)));
        (networkModule_provideRetrofitFactory
                .get()
                .getApiService()
                .getApiResultCity(getParams()
                        .get("action"), getParams())).
                enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        getView().hideProgressDialog();
                        Log.e("success", "<><" + response.body());
                        try{
                            ((CallbackService) getActivity()).callBackActivity(response.body());

                        }catch (Exception e){
                            e.printStackTrace();

                        }
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.e("fail", "<><" + t.getMessage());
                        ErrorHandler.handle(t, callback);
                    }
                });
    }


    private HashMap<String,String> getParams(){
        HashMap<String ,String > params = new HashMap<>();
//        params.put("username",mFName);
//        params.put("password",password);
//        params.put("adress","");
//        params.put("mobilenumber",mPhoneNumber);
//        params.put("landmark","");
//        params.put("Name","rajesh");
        params.put("action",APIS.SIGNUP);

        params.put("Email",mEmail);
        params.put("password",password);
        params.put("adress","fff");
        params.put("mobilenumber",mPhoneNumber);
        params.put("landmark","sada");
        params.put("FirstName",mFName);
        params.put("LastName",mLName);
        return params;
    }

    ErrorHandler.ErrorHandlerCallback callback = new ErrorHandler.ErrorHandlerCallback() {
        @Override
        public void onHttpError(RetrofitException e) {

        }

        @Override
        public void onNetworkError() {
            getView().showErrorMessage(getActivity().getResources().getString(R.string.error_generic_network_connection));
        }

        @Override
        public void onServerError(String errorMessage) {
            getView().showErrorMessage(getActivity().getResources().getString(R.string.error_generic_internal_server_error));
        }

        @Override
        public void onUnrecoverableError(Throwable throwable) {

        }
    };
}
