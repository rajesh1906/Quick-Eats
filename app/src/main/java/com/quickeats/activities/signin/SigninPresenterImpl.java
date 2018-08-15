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
import com.quickeats.shared.CallbackService;
import com.quickeats.shared.MvpBasePresenter;
import com.quickeats.shared.NetworkModule;
import com.quickeats.shared.NetworkModule_ProvideRetrofitFactory;
import com.quickeats.utils.CommonValidations;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SigninPresenterImpl extends MvpBasePresenter<SigninView> implements SigninPresenter {


    CommonValidations validations;

    NetworkModule_ProvideRetrofitFactory networkModule_provideRetrofitFactory;

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
        this.validations = (CommonValidations) validation;
        Log.e("coming to handler", "<>><");
        if (checkValidation(email, password)) {
            apiCall(email, password);
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
                        ((CallbackService) getActivity()).callBackActivity();

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
}
