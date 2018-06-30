package com.quickeats.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.quickeats.R;
import com.quickeats.dashboard.DashboardActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singin);
        ButterKnife.bind(this);

    }
    @OnClick(R.id.tv_next)
    void submitDetails(){
       startActivity(new Intent(this, DashboardActivity.class));
       finish();
    }
    @OnClick(R.id.txtsignup)
    void launchSignupScreen(){
        startActivity(new Intent(this,SignUpActivity.class));
    }
    @OnClick(R.id.txtforgetpassword)
    void launchForgetPasswordScreen(){
        startActivity(new Intent(this,ForgotPasswordActivity.class));
    }
}
