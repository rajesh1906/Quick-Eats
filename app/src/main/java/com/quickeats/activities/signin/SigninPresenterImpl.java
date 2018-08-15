package com.quickeats.activities.signin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.google.gson.Gson;
import com.quickeats.Network.APIResponse;
import com.quickeats.Network.APIS;
import com.quickeats.Network.ApiService;
import com.quickeats.Network.RetrofitClient;
import com.quickeats.QuickEatsBase;
import com.quickeats.R;
import com.quickeats.activities.ForgotPasswordActivity;
import com.quickeats.activities.SignUpActivity;
import com.quickeats.dashboard.DashboardActivity;
import com.quickeats.dashboard.model.Cities;
import com.quickeats.shared.MvpBasePresenter;
import com.quickeats.shared.NetworkModule;
import com.quickeats.utils.CommonValidations;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class SigninPresenterImpl extends MvpBasePresenter<SigninView> implements SigninPresenter {


    CommonValidations validations;


    NetworkModule networkModule;

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
    public void handleLoginRequest(String email, String password, Object validation,Object networkModule) {
        this.validations = (CommonValidations) validation;
        this.networkModule = (NetworkModule) networkModule;
        Log.e("coming to handler", "<>><");
        if (checkValidation(email, password)) {
            getActivity().startActivity(new Intent(getActivity(), DashboardActivity.class));
            getActivity().finish();
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

    @SuppressLint("ResourceType")
    private boolean checkValidation(String email, String password) {
        boolean validation = true;
        apiCall();
        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
            validation = false;
            getView().showErrorMessage(1);
        } else if (!validations.emailValidation(email)) {
            validation = false;
            getView().showEmailFieldError(false);
        } else if (TextUtils.isEmpty(password)) {
            validation = false;
            getView().showErrorMessage(2);
        }
        return validation;
    }
    private void apiCall(){
        ApiService apiService = (ApiService)RetrofitClient.getInstance();
        apiService.getApiResult(getParams("cities"));

        RetrofitClient.getInstance().getEndPoint(getActivity(), "").getResult(getParams("cities"), new APIResponse() {
            @Override
            public void onSuccess(String res) {
                Log.e("resultent cities is ", "<><>" + res);

            }

            @Override
            public void onFailure(String res) {

            }
        });
    }
    private Map<String, String> getParams(String coming_from)   {
        Map<String, String> params = new HashMap<>();
        switch (coming_from) {
            case "cities":
                params.put("Text", "Hyderabad");
                params.put("FlagSlNo", "0");
//                params.put("action", getResources().getString(R.string.getCities));
                params.put("action", APIS.CITIES);

                break;
        }

        return params;
    }
}
