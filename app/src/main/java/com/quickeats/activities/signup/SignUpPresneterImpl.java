package com.quickeats.activities.signup;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.quickeats.Network.APIS;
import com.quickeats.activities.signin.SignInActivity;
import com.quickeats.dashboard.DashboardActivity;
import com.quickeats.shared.CallbackService;
import com.quickeats.shared.MvpBasePresenter;
import com.quickeats.shared.MvpPresenter;
import com.quickeats.shared.NetworkModule;
import com.quickeats.shared.NetworkModule_ProvideRetrofitFactory;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpPresneterImpl extends MvpBasePresenter<SignUpView> implements SignUpPresenter  {



    NetworkModule_ProvideRetrofitFactory networkModule_provideRetrofitFactory;
    String mFName,mLName,mPhoneNumber,mEmail;
    @Override
    public void handleSignUpRequest(String fName, String lName, String phoneNumber, String email) {
        this.mFName = fName;
        this.mLName = lName;
        this.mPhoneNumber = phoneNumber;
        this.mEmail = email;
        if(checkValidation(fName,lName,phoneNumber,email)){
            apiCalling();

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
    private boolean checkValidation(String fName, String lName, String phoneNumber, String email){
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
                            ((CallbackService) getActivity()).callBackActivity();
                        }catch (Exception e){
//                            RetrofitE
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.e("fail", "<><" + t.getMessage());
                    }
                });
    }


    private HashMap<String,String> getParams(){
        HashMap<String ,String > params = new HashMap<>();
        params.put("username",mFName);
        params.put("password",mLName);
        params.put("adress","");
        params.put("adress","");
        params.put("mobilenumber",mPhoneNumber);
        params.put("landmark","");
        params.put("Name","rajesh");
        params.put("action",APIS.SIGNUP);
        return params;
    }
}
