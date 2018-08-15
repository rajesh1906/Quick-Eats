package com.quickeats.activities.signin;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.quickeats.AuthenticateComponent;
import com.quickeats.MvpBaseActivity;
import com.quickeats.R;
import com.quickeats.shared.NetworkModule;
import com.quickeats.utils.CommonValidations;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class SignInActivity extends MvpBaseActivity<SigninPresenter, AuthenticateComponent> implements SigninView {


    @BindView(R.id.edt_email)
    EditText edt_email;
    @BindView(R.id.edt_password)
    EditText edt_password;

    @Inject
    CommonValidations validations;
    NetworkModule networkModule;

    @Override
    public int getLayout() {
        return R.layout.activity_singin;
    }

    @Override
    protected AuthenticateComponent setupActivityComponent() {
        return getApplicationComponent().plus(new SignAppModule());
    }

    @OnClick(R.id.tv_next)
    void submitDetails() {
        String eamil = edt_email.getText().toString();
        String password = edt_password.getText().toString();
        Log.e("networkModule", "<>><" + networkModule);
        getPresenter().handleLoginRequest(eamil, password, validations,networkModule);

//        Log.e("valiadtions", "<>><" + validations+" ");

    }

    @OnClick(R.id.txtsignup)
    void launchSignupScreen() {
        getPresenter().handleLaunchSignUpscreen();
    }

    @OnClick(R.id.txtforgetpassword)
    void launchForgetPasswordScreen() {
        getPresenter().handleLaunchForgetScreen();
    }

    @Override
    protected void onCreateAfterSetContentView(Bundle savedInstanceState) {
        super.onCreateAfterSetContentView(savedInstanceState);
    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void hideProgressDialog() {

    }

    @Override
    public void showErrorMessage(int errorMessage) {
        switch (errorMessage) {
            case 1:
                showToastMessage(getResources().getString(R.string.error_genric_valid_details));
                break;
            case 2:
                showToastMessage(getResources().getString(R.string.error_generic_valid_password));
                break;
        }
    }

    @Override
    public void showEmailFieldError(boolean show) {
        if (!show) {
            showToastMessage(getResources().getString(R.string.error_generic_valid_email));
        }

    }

    private void showToastMessage(String message) {
        Toast.makeText(SignInActivity.this, message, Toast.LENGTH_SHORT).show();
    }



}
