package com.quickeats.activities.signin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.quickeats.MvpBaseActivity;
import com.quickeats.R;
import com.quickeats.activities.ForgotPasswordActivity;
import com.quickeats.activities.SignUpActivity;
import com.quickeats.dashboard.DashboardActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends MvpBaseActivity<SigninPresenter,SignAppModule>{


    @Override
    public int getLayout() {
        return R.layout.activity_singin;
    }

    @Override
    protected SignAppModule setupActivityComponent() {
        return getApplicationComponent().inject(new SignAppModule());
    }

    @OnClick(R.id.tv_next)
    void submitDetails(){

    getPresenter().handleLoginRequest("","");
    }
    @OnClick(R.id.txtsignup)
    void launchSignupScreen(){
        startActivity(new Intent(this,SignUpActivity.class));
    }
    @OnClick(R.id.txtforgetpassword)
    void launchForgetPasswordScreen(){
        startActivity(new Intent(this,ForgotPasswordActivity.class));
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
}
