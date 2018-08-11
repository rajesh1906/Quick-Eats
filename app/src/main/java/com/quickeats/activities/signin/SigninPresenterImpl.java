package com.quickeats.activities.signin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.quickeats.dashboard.DashboardActivity;
import com.quickeats.shared.MvpBasePresenter;

public class SigninPresenterImpl extends MvpBasePresenter<SigninView> implements SigninPresenter {

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
    public void handleLoginRequest(String email, String password) {
        Log.e("coming to handler","<>><");
//        getActivity().startActivity(new Intent(getActivity(), DashboardActivity.class));
//        getActivity().finish();
    }

    @Override
    public void handleLaunchSignUpscreen() {

    }

    @Override
    public void handleLaunchForgetScreen() {

    }
}
